import java.util.ArrayList;

/**
            * Implementing rules for the game of BlackJack.
            *
            * @author Rus Adamovics and Ryan Moore
            * @version Program7 Blackjack Blackjack.java
            */


public class Blackjack 
{

   public static final double BLACKJACK_PAY = 1.5;

   public boolean hitSoft_17;
   public int balance;
   public int maxPlayers;
   public int numDecks;
   public ArrayList<BlackjackPlayer> players;
   public Dealer dealer;
   public RandomAI randomai;
   public GoodAI goodai;
   public int numRounds;
   public int numPlayers;
   public Deck deck;
   public IOHandler io;

/**
 * Constructor that declares and intializes the number of decks, min and max
 * bets, balance, and max players.
 */
   public Blackjack() 
   {
      numDecks = 1;
      maxPlayers = 4;
      hitSoft_17 = false;
      io = new IOHandler();
      numRounds = 0;
      balance = 500;
   }

/**
 * Play Blackjack and setting up all the rules.
 */
   public void play() 
   {
      boolean roundStart = false;
      /*
       * Set up environment
       */
      init();
      while (true) 
      {
         deck = new Deck(numDecks);
         System.out.println(players);
         /*
          * Use current deck of cards until it is too few
          */
         while (deck.canDeal(players.size())) 
         {
            io.newRound(players.size(), numRounds + 1);
            placeBets();
            roundStart = true;
            dealRound();
            if (!dealer.getHand().isBlackjack())
            {
               playRound();
            }
            settleRound();
            if (players.size() == 0) 
            {
               io.displayResults(numRounds);
               return;
            }
            numRounds += 1;
            roundStart = false;
         }

         if (!io.playAnotherDeck())
            break;
         }
      if (deck != null) 
      {
         if (roundStart)
            settleRound();
         io.displayResults(numRounds);
      }
   }

/**
 * Start game and initialize all variables
 */
   private void init() 
   {
      io.startGame();
      numDecks = io.getNumDecks();
      numPlayers = io.getNumPlayers();
      players = new ArrayList<BlackjackPlayer>(numPlayers);
      String name = "";
      for (int i = 0; i < numPlayers; i++) 
      {
         name = io.getPlayerName(i + 1);
         players.add(new BlackjackPlayer(name, balance));
      }
      dealer = new Dealer(hitSoft_17);
   }

/**
 * Get bets from all players.
 */
   private void placeBets() 
   {
      if (numRounds == 0) 
      {
         io.getPlayerBets(players);
      }
      for (BlackjackPlayer player : players) 
      {
         player.playHand();
      }
      dealer.playHand();
   }

/**
 * Deal the cards of a single round.
 */
   private void dealRound() 
   {
      for (int i = 0; i < 2; i++) 
      {
         for (int j = 0; j < players.size(); j++)
         {
            if (deck.canDeal(numPlayers - j))
            {
               players.get(j).getHands().get(0).hit(deck.dealCard());
            }
         }   
         dealer.getHand().hit(deck.dealCard());
      }
   }


/**
 * Step-by-step moves or players and dealer an order to play out a single
 * round.
 */
   private void playRound() 
   {
      for (BlackjackPlayer player : players) 
      {
         for (PlayerHand hand : player.getHands()) 
         {
            while (hand.canHit() && !hand.isDone()) 
            {
               char nextMove = io.nextMove(dealer, player, hand);
               switch (nextMove) 
               {
                  case 'h':
                     hitHand(hand);
                     break;
                  case 'd':
                     hitHand(hand);
                     player.doubleBet(hand);
                     hand.stand();
                     break;
                  case 's':
                     hand.stand();
                     break;
                  case 'p':
                     player.split(hand);
                     io.showHands(player);
                     hitSplitHand(player, hand);
                     break;
                  default:
                     io.tryAgain();
                     break;
               }
            }
            io.showHand(player, hand);
         }
         dealer.getHand().play(deck);
         io.showHand(dealer, dealer.getHand());
      }
   }

/**
 * Hit a hand with a card.
 * 
 * @param hand
 */
   private void hitHand(Hand hand) 
   {
      hand.hit(deck.dealCard());
   }

/**
 * Hit a split hand of a player.
 * 
 * @param player
 * @param hand
 */
   private void hitSplitHand(BlackjackPlayer player, PlayerHand hand) 
   {
      hitHand(hand);
      if (hand.isSplitAces() && player.hasmaxHands())
         hand.stand();
      else if (hand.isSplitAces() && !hand.hasAce())
         hand.stand();
   }

/**
 * Determine, winners, losers and pushers
 */
   private void settleRound() 
   {
      io.genResults();
      for (BlackjackPlayer player : players) 
      {
         for (PlayerHand hand : player.getHands()) 
         {
            if (dealer.getHand().calculateScore() < 17)
               dealer.getHand().play(deck);
            if ((hand.higherTotal() > dealer.getHand().calculateScore() && !hand.isBust())
            || (dealer.getHand().isBust() && !hand.isBust())) 
            {
               if (hand.isBlackjack()) 
               {
                  player.winBet((int) (hand.getBet() + (hand.getBet() * BLACKJACK_PAY)));
                  dealer.pay((int) (hand.getBet() * BLACKJACK_PAY));
               } 
               else 
               {
                  player.winBet(hand.getBet() * 2);
                  dealer.pay(hand.getBet());
               }
               io.playerWins(player, hand);
            } 
            else if (hand.higherTotal() == dealer.getHand().calculateScore()) 
            {
               if (hand.isBlackjack()) 
               {
                  player.winBet(hand.getBet() * 2);
                  dealer.pay(hand.getBet());
                  io.playerWins(player, hand);
               } 
               else 
               {
                  player.winBet(hand.getBet());
                  io.playerPush(player, hand);
               }
            } 
            else 
            {
               dealer.winBet(hand.getBet());
               io.playerLoses(player, hand);
            }
         }
         player.finishHands();
      }
      io.showHand(dealer, dealer.getHand());
      dealer.finishHand();
      System.out.println(players);

      for (BlackjackPlayer player : players) 
      {
         if (player.getBalance() == 0) 
         {
            io.playerBankrupt(player);
            players.remove(player);
         }
      }
   }


   public static void main(String[] args) 
   {
      Blackjack BJGame = new Blackjack();
      BJGame.play();
   }
}


