import java.util.ArrayList;

/**
            * Implementing a class to represent a blackjack human player 
            * which extends Playe and writing java
            *
            * @author Rus Adamovics and Ryan Moore
            * @version Program7 Blackjack BlackjackPlayer.java
            */


public class BlackjackPlayer extends Player 
{

   private static int totalHands = 0;
   private final int MAX_HANDS = 4;
   private int numHands;
   private int bet;
   private ArrayList<PlayerHand> hands;

/**
 * Default Constructor that calls default constructor from Player 
 * class and set number of hands to 0.
 */

   public BlackjackPlayer() 
   {
      super();
      numHands = 0;
      hands = new ArrayList<PlayerHand>();
   }

/**
 * Constructor that class the Player constructor and 
 * initializes name, balance, and number of hands to 0.
 */

   public BlackjackPlayer(String name, int balance) 
   {
      super(name, balance);
      numHands = 0;
      hands = new ArrayList<PlayerHand>();
   }

/**
 * Add a new Player hand, either at the beginning of a round or when
 * splitting.
 */
   public Hand playHand() 
   {
      this.numHands += 1;
      PlayerHand hand = new PlayerHand();
      hands.add(hand);
      totalHands += 1;
      betOnHand(hand);
      return hand;
   }

/**
 * Add bet on a hand.
 * 
 * @param hand
 */
   public void betOnHand(PlayerHand hand) 
   {
      hand.setBet(bet);
      removeFromBalance(bet);
   }

/**
 * Returns true if player's balance is greater than their bet.
 *
 * @return true if the player can double down, meaning if their balance is greater than the bet.
 */

   public boolean canDoubleBet() 
   {
      return getBalance() > bet;
   }

/**
 * Double down on the bet.
 * 
 * @param hand
 */
   public void doubleBet(PlayerHand hand) 
   {
      hand.increaseBet(bet);
      removeFromBalance(bet);
   }

/**
 * Collect winnings from dealer.
 * 
 * @param profit
 */
   public void winBet(int profit) 
   {
      addToBalance(profit);
   }

/**
 * returns true if the amount of cards in a player's hand is the max number of cards allowed.
 *
 * @return tru if the player's number of cards is equal to the max cards a player can have.
 */

   public boolean hasmaxHands() 
   {
      return hands.size() == MAX_HANDS;
   }

/**
 * Depending on the current hand checks whether a player can perform a
 * split. If a hand has two cards only and they are identical with respect
 * to their Rank, a split can be done.
 */
   public boolean canSplitHand(Hand hand) 
   {
      return ((PlayerHand) hand).canSplit() && !this.hasmaxHands();
   }

/**
 * Perform the split of the hands.
 * 
 * @param hand
 *
 * @return
 */
   public PlayerHand split(PlayerHand hand) 
   {
      if (canSplitHand(hand)) 
      {
         numHands += 1;
         totalHands += 1;
         hands.add((PlayerHand) hand.split());
         betOnHand(hands.get(hands.size() - 1));
         return hands.get(hands.size() - 1);
      }
      return null;
   }

/**
 * Clear cards list at the end of a round.
 */
   public void finishHands() 
   {
      hands.clear();
   }

/**
 * Adds profit gained to player's balance.
 */
   public void addToBalance(int profit) 
   {
      setBalance(getBalance() + profit);
   }

/**
 * Subtracts player's losses from their balance balance.                           
 */
   public void removeFromBalance(int profit) 
   {
      setBalance(getBalance() - profit);
   }

/**
 * Overrides toString method.                           
 */
   public String toString() 
   {
      return getName() + " Bet: $" + bet + " Balance: $" + getBalance();
   }

/**
 * Returns or gets the number of total hands.
 *
 * @return Number of total hands                           
 */

   public static int getTotalHands() 
   {
      return totalHands;
   }

/**
 * Sets the number of total hands.                           
 */
   public static void setTotalHands(int totalHands) 
   {
      BlackjackPlayer.totalHands = totalHands;
   }

/**
 * Returns the number of hands for a player.
 *
 * @return The number of hands for a player.                           
 */
   public int getNumHands() 
   {
      return numHands;
   }

/**
 * Sets the number of hands for a player.
 */
   public void setNumHands(int numHands) 
   {
      this.numHands = numHands;
   }

/**
 * Returns the hands of players.
 *
 * @return The hands of players.
 */
   public ArrayList<PlayerHand> getHands() 
   {
      return hands;
   }

/**
 * Sets the hand of a player.   
 */
   public void setHands(ArrayList<PlayerHand> hands) 
   {
      this.hands = hands;
   }

/**
 * Return the max number of hands for a player.
 *
 * @return The max number of hands for a player.   
 */
   public int getMAX_HANDS() 
   {
      return MAX_HANDS;
   }

/**
 * Return the bet made.
 *
 * @return The bet made.
 */ 
   public int getBet() 
   {
      return bet;
   }

/**
 * Sets the bet made.
 */ 
   public void setBet(int bet) 
   {
      this.bet = bet;
   }
}


