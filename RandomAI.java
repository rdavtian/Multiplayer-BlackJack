/**
            * Implementing a class to represent a random AI 
            * which extends the Player class and writing java docs.
            *
            * @author Rus Adamovics and Ryan Moore
            * @version Program7 Blackjack RandomAI.java
            */
public class RandomAI extends Player 
{

   private AIHand hand;
   private boolean hitSoft_17;

/**
 * Default constructor initializes the name of the dealer and 
 * setting hitting on 17 to false.
 */

   public RandomAI() 
   {
      super("RandomAI", 0);
      this.hitSoft_17 = false;
   }

/**
 * Constructor initializes hitting on 17.
 */

   public RandomAI(boolean hitSoft_17) 
   {
      this();
      this.hitSoft_17 = hitSoft_17;
   }


/**
 * Initiate a new RandomAI Hand
 */
   public Hand playHand() 
   {
      hand = new AIHand();
      hand.setHitSoft_17(hitSoft_17);
      return hand;
   }

/**
 * An AI can't split their hand, yet.
 */
   public boolean canSplitHand(Hand hand) 
   {
      return false;
   }

/**
 * Overrides toString method and returns name and balance of RandomAI.
 */
 
   public String toString() 
   {
      return getName() + " Balance: $" + getBalance();
   }

/**
 * Returns the RandomAI's hand.
 *
 * @return The RandomAI's hand.
 */
   public AIHand  getHand() 
   {
      return hand;
   }

/**
 * Sets the hand of the dealer.
 */
   public void setHand(AIHand hand) 
   {
      this.hand = hand;
   }

/**
 * Returns true if AI can hit, false if AI cannot.
 */
   public boolean isHitSoft_17() 
   {
      return hitSoft_17;
   }

/**
 * Sets the RandomAI not hitting at 17 or over.
 */
   public void setHitSoft_17(boolean hitSoft_17) 
   {
      this.hitSoft_17 = hitSoft_17;
   }
}

