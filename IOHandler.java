import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
            * Implementing a class to represent and handle input, 
            * output statements and writing java docs.
            *
            * @author Rus Adamovics and Ryan Moore
            * @version Program7 Blackjack IOHandler.java
            */
public class IOHandler 
{

   BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        public void startGame() {
                System.out.println("Welcome To BlackJack! You will be playing with 2 AI's, a good one, and a Random one (until we implement more. Enjoy!");
        }

        /**
         * Reads input from user corresponding to number of decks.
         * 
         * @return number of decks to play with
         */
        public int getNumDecks() {
                System.out.println("Enter number of decks from 1-8");
                String inp = "";
                try {
                        while ((inp = stdin.readLine()) != null) {
                                int res = 1;
                                try {
                                        res = Integer.parseInt(inp);
                                } catch (NumberFormatException numEx) {
                                        System.out.println("Default number of decks chosen: 1");
                                }
                                if (res >= 1 && res <= 8)
                                        return res;
                                tryAgain();
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return 0;
        }

        /**
         * 
         * @return number of players in game
         */
        public int getNumPlayers() {
                System.out.println("Enter number of players from 1-4");
                String inp = "";
                try {
                        while ((inp = stdin.readLine()) != null) {
                                int res = 2;
                                try {
                                        res = Integer.parseInt(inp);
                                } catch (NumberFormatException numEx) {
                                        System.out.println("Default number of players chosen: 2");
                                }
                                if (res >= 1 && res <= 4)
                                        return res;
                                tryAgain();
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return 0;
        }

        /**
         * Get name of player.
         * 
         * @param i
         * @return
         */
        public String getPlayerName(int i) {
                System.out.println("Enter name of Player " + i);
                String inp = "";
                try {
                        while ((inp = stdin.readLine()) != null) {
                                if (!inp.equalsIgnoreCase(""))
                                        return inp;
                                tryAgain();
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return "";
        }

        public void showDealerCard(Dealer dealer) {
                System.out.println("Dealers visible card is \n"
                                + dealer.getHand().visibleCard());
        }

        public void showHand(BlackjackPlayer player, PlayerHand hand) {
                for (PlayerHand pHand : player.getHands())
                        for (Card card : pHand.getCards())
                                System.out.println(card);
        }

        public void showHand(Dealer dealer, DealerHand hand) {
                System.out.println("Dealer's Hand :");
                for (Card card : hand.getCards())
                        System.out.println(card);
        }

        public char nextMove(Dealer dealer, BlackjackPlayer player, PlayerHand hand) {
                String inp = "";
                StringBuffer scanner = new StringBuffer();
                scanner.append("===================================================\n");
                scanner.append(player.getName() + "'s turn\n");

                scanner.append("Hit [h]");
                if (player.canSplitHand(hand) && player.canDoubleBet())
                        scanner.append(", Stand [s], Double [d], Split [p]?");
                else if (player.canDoubleBet())
                        scanner.append(", Stand [s], Double [d]?");
                else
                        scanner.append(" or Stand [s]?");
                System.out.println(scanner.toString());
                System.out.println("Dealer's visible card "
                                + dealer.getHand().visibleCard());
                System.out.println("Your hand \n" + hand.toString());
                scanner.append("===================================================\n");

                try {
                        while ((inp = stdin.readLine()) != null) {
                                if (inp.equalsIgnoreCase("h"))
                                        return 'h';
                                if (inp.equalsIgnoreCase("s"))
                                        return 's';
                                if (inp.equalsIgnoreCase("d"))
                                        if (player.canDoubleBet())
                                                return 'd';
                                if (inp.equalsIgnoreCase("p"))
                                        if (player.canSplitHand(hand) && player.canDoubleBet())
                                                return 'p';

                                tryAgain();
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }

                return 0;
        }

        public void showHands(BlackjackPlayer player) {
                for (PlayerHand hand : player.getHands()) {
                        System.out.println(hand.toString());
                }
        }

        public void genResults() {
                System.out
                                .println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        }
        /**
         * Start a new round.
         * 
         * @param players
         * @param roundNum
         */
        public void newRound(int players, int roundNum) {
                System.out.println("Round #" + roundNum);
                System.out.println("There are " + players + " players in this round");
                System.out
                                .println("#############################################################");
        }

        /**
         * Asks if player wants to player with a new deck since the old one ran out
         * of cards.
         * 
         * @return
         */
        public boolean playAnotherDeck() {
                System.out.println("Would you like to play with another deck [y/n]");
                String inp = "";
                try {
                        while ((inp = stdin.readLine()) != null) {
                                if (inp.equalsIgnoreCase("y"))
                                        return true;
                                else if (inp.equalsIgnoreCase("n"))
                                        return false;

                                tryAgain();
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return false;
        }

        /**
         * Display results at the end of the game
         * 
         * @param num
         */
        public void displayResults(int num) {
                System.out.println("Total rounds played " + num);

        }
     /**
         * Get bets from players.
         * 
         * @param players
         * @param bet
         */
        public void getPlayerBets(ArrayList<BlackjackPlayer> players) {
                String inp = "";
                for (BlackjackPlayer player : players) {
                 System.out.println(player.getName() + ", choose a bet size " );
                        try {
                                while ((inp = stdin.readLine()) != null) {
                                        int bet = 10;
                                        try {
                                                bet = Integer.parseInt(inp);
                                        } catch (NumberFormatException numEx) {
                                                System.out.println("Default bet size chosen: 10");
                                        }
                                        if (bet > 0) {
                                                player.setBet(bet);
                                                break;
                                        }
                                        tryAgain();
                                }
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }

        }

        /**
         * Invalid input
         */
        public void tryAgain() {
                System.out.println("Invalid input, try again!");
        }

                /**
         * Player wins game.
         * 
         * @param player
         * @param hand
         */
        public void playerWins(BlackjackPlayer player, PlayerHand hand) {
                System.out.println(player.getName() + " WINS!");
        }

        /**
         * Player pushes cards
         * 
         * @param player
         * @param hand
         */
        public void playerPush(BlackjackPlayer player, PlayerHand hand) {
                System.out.println(player.getName() + " PUSHES!");
        }

        /**
         * Player loses game
         * 
         * @param player
         * @param hand
         */
        public void playerLoses(BlackjackPlayer player, PlayerHand hand) {
                System.out.println(player.getName() + " LOSES!");
        }

        /**
         * Player is bankrupt
         * 
         * @param player
         */
        public void playerBankrupt(BlackjackPlayer player) {
                System.out.println(player.getName() + " GET MORE MONEY!");
        }

}


