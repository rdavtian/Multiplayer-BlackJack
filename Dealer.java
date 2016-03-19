/**
            * Implementing a class to represent the dealer 
            * which extends the Player class and writing java docs.
            *
            * @author Rus Adamovics and Ryan Moore
            * @version Program7 Blackjack Dealer.java
            */
public class Dealer extends Player 
{

   private DealerHand hand;
   private boolean hitSoft_17;

/**
 * Default constructor initializes the name of the dealer and 
 * setting hitting on 17 to false.
 */

   public Dealer() 
   {
      super("Dealer", 0);
      this.hitSoft_17 = false;
   }

/**
 * Constructor initializes hitting on 17.
 */

   public Dealer(boolean hitSoft_17) 
   {
      this();
      this.hitSoft_17 = hitSoft_17;
   }

/**
 * Pay the bets to the players.
 * 
 * @param loss
 */
   public void pay(int loss) 
   {
      setBalance(getBalance() - loss);
   }

/**
 * Initiate a new Dealer Hand
 */
   public Hand playHand() 
   {
      hand = new DealerHand();
      hand.setHitSoft_17(hitSoft_17);
      return hand;
   }

/**
 * Dealer can't split their hand
 */
   public boolean canSplitHand(Hand hand) 
   {
      return false;
   }

/**
 * Collect bets from losers.
 * 
 * @param profit
 */
   public void winBet(int profit) 
   {
      addToBalance(profit);
   }

/**
 * Clear card list
 */
   public void finishHand() 
   {
      hand = new DealerHand();
   }

/**
 * Overrides toString method and returns name and balance of dealer.
 */
 
   public String toString() 
   {
      return getName() + " Balance: $" + getBalance();
   }

/**
 * Returns the dealer's hand.
 *
 * @return The dealer's hand.
 */
   public DealerHand getHand() 
   {
      return hand;
   }

/**
 * Sets the hand of the dealer.
 */
   public void setHand(DealerHand hand) 
   {
      this.hand = hand;
   }

/**
 * Returns true dealer can hit, false if dealer cannot.
 */
   public boolean isHitSoft_17() 
   {
      return hitSoft_17;
   }

/**
 * Sets the dealer not hitting at 17 or over.
 */
   public void setHitSoft_17(boolean hitSoft_17) 
   {
      this.hitSoft_17 = hitSoft_17;
   }

/**
 * Adds the dealer's profits to their balance.
 */
   public void addToBalance(int profit) 
   {
      setBalance(getBalance() + profit);
   }

/**
 * Adds the dealer's losses to their profit..
 */
   public void removeFromBalance(int profit) 
   {
      setBalance(getBalance() - profit);
   }
}


