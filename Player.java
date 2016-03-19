/**
            * Implementing a class to represent a blackjack player and writing jav$
            *
            * @author Rus Adamovics and Ryan Moore
            * @version Program7 Blackjack Player.java
            */
public abstract class Player 
{
   private String name;
   private int balance;

/**
 * Default constructor that initialize a player with a starting bet balance.
 */
   public Player()
   {
      this.name = "Player " + System.currentTimeMillis();
      this.balance = 1000;
   }
   
/**
 * Constructor that initialize a player with a balance.
 */
   public Player(String name, int balance)
   {
      this.name = name;
      this.balance = balance;
   }

/**
 * Returns name of the player.
 *
 * @return Name of a player                     
 */
   public String getName() 
   {
      return name;
   }

/**
 * Sets the name of a player.
 *
 * @param name                         
 */
   public void setName(String name) 
   {
      this.name = name;
   }

/**
 * Returns or gets the current balance of a player.
 *
 * @return Balance of a player                         
 */
   public int getBalance() 
   {
      return balance;
   }

/**
 * Sets the balance of a player.
 *
 * @param balance                         
 */
   public void setBalance(int balance) 
   {
      this.balance = balance;
   }
        
/**
 * Returns true if players can split, false otherwise.
 *
 * @param hand                         
 */
   public abstract boolean canSplitHand(Hand hand);
        
   public abstract Hand playHand();
}



