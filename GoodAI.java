/**
            * Implementing a class to represent the GoodAI 
            * which extends the Player class and writing java docs.
            *
            * @author Rus Adamovics and Ryan Moore
            * @version Program7 Blackjack GoodAI.java
            */
public class GoodAI extends Player 
{

   private GoodAIHand hand;
   private boolean hitSoft_17;

/**
 * Default constructor initializes the name of the GoodAI and 
 * setting hitting on 17 to false.
 */

   public GoodAI() 
   {
      super("GoodAI", 100);
      this.hitSoft_17 = false;
   }

/**
 * Constructor initializes hitting on 17.
 */

   public GoodAI(boolean hitSoft_17) 
   {
      this();
      this.hitSoft_17 = hitSoft_17;
   }


/**
 * Initiate a new GoodAI Hand
 */
   public Hand playHand() 
   {
      hand = new GoodAIHand();
      hand.setHitSoft_17(hitSoft_17);
      return hand;
   }

/**
 * A GoodAI can't split their hand, yet
 */
   public boolean canSplitHand(Hand hand) 
   {
      return false;
   }

/**
 * Clear card list
 */
   public void finishHand() 
   {
      hand = new GoodAIHand();
   }

/**
 * Overrides toString method and returns name and balance of GoodAI.
 */
 
   public String toString() 
   {
      return getName() + " Balance: $" + getBalance();
   }

/**
 * Returns the GoodAI's hand.
 *
 * @return The GoodAI's hand.
 */
   public GoodAIHand getHand() 
   {
      return hand;
   }

/**
 * Sets the hand of the GoodAI.
 */
   public void setHand(GoodAIHand hand) 
   {
      this.hand = hand;
   }

/**
 * Returns true GoodAI can hit, false if GoodAI cannot.
 */
   public boolean isHitSoft_17() 
   {
      return hitSoft_17;
   }

/**
 * Sets the GoodAI not hitting at 17 or over.
 */
   public void setHitSoft_17(boolean hitSoft_17) 
   {
      this.hitSoft_17 = hitSoft_17;
   }
}
