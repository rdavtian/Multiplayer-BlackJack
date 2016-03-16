import java.util.ArrayList;

/**
           * Implementing a class AI to represent a computer playing Blackjack.
           *
           * @author Rus Adamovics and Ryan Moore
           * @version Program7 Blackjack AI.java
           *
           */

public class AI_Player implements Player1 
{
   private String name;
   private ArrayList<Hand> hands;
   private int winCount;
   private int drawCount;
   private int loseCount;
   private double bank;
   private int boardPosition;

/**
 * Constructor that intializes the declared instance variables of class AI.
 */

   public AI_Player(String name, int position)
   {		
      this.setName(name);
      this.setBoardPosition(position);
      this.hands = new ArrayList<Hand>();
      hands.add(new Hand());
      this.winCount = 0;
      this.loseCount = 0;
      this.setBank(500);
   }

/**
 * Returns the list of hands of the players.
 *
 * @return The hands of the players.
 *
 */

   //GETTERS AND SETTERS
   public ArrayList<Hand> hands() 
   {
      return hands;
   }

/**
 * Clears the current hand of a player and draws a new hand.
 *
 */

	
   public void clearHand()
   {
      hands.clear();
      hands.add(new Hand());
   }

/**
 * Returns or gets the name of the AI.
 *
 * @return The name of the AI.
 *
 */

   public String getName() 
   {
      return name;
   }

/**
 * Sets the name of the AI.
 *
 */

   public void setName(String name) 
   {
      this.name = name;
   }

/**
 * Returns or gets the win count.
 *
 */

   public int getWinCount() 
   {
      return winCount;
   }

/**
 * Increments the win count by 1.
 *
 */

   public void addWin() 
   {
      this.winCount++;
   }

/**
 * Returns or gets the number of losses count.
 *
 */

   public int getLostCount() 
   {
      return loseCount;
   }

/**
 * Increments the loss count by 1.
 *
 */

   public void addLose() 
   {
      this.loseCount++;
   }

/**
 * Returns the count of how many cards drawn.
 *
 * @return or gets the number of drawn cards.
 *
 */
	
   public int getDrawCount() 
   {
      return drawCount;
   }

/**
 * Increments the drawn cards count by 1.
 *
 */

   public void addDraw() 
   {
      this.drawCount++;
   }

/**
 * Resets the win and loss count to 0.
 */

   public void resetStats()
   {
      this.winCount = 0;
      this.loseCount = 0;
   }

/**
 * Returns or gets the money available in the bank for a AI.
 *
 * @return The current amount of money in left in an AI's bank. 
 */

   public double getBank() 
   {
      return bank;
   }

/**
 * Returns or sets the bank value for each player to $500.
 *
 */

   public void setBank(double bank) 
   {
      this.bank = bank;
   }

/**
 * Returns the current board position.
 *
 * @return The board position.
 *
 */

   public int getBoardPosition() 
   {
      return boardPosition;
   }

/**
 * Sets the board position.
 *
 * @param  boardPosition
 */

   public void setBoardPosition(int boardPosition) 
   {
      this.boardPosition = boardPosition;
   }
}


