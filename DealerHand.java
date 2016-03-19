import java.util.Scanner;
/**
            * Implementing a class to represent the dealer's hand 
            * which extends hand and writing java docs.
            *
            * @author Rus Adamovics and Ryan Moore
            * @version Program7 Blackjack DealerHand.java
            */
public class DealerHand extends Hand 
{

   private boolean hitSoft_17;
   private int handScore;

/**
 * Constructor that calls Hand class initializes hitting at 17 to false
 * and the dealer's hand score at 0.
 */
   public DealerHand() 
   {
      super();
      hitSoft_17 = false;
      handScore = 0;
   }

/**
 * Overrides the toString method.
 */
   public String toString() 
   {
      calculateScore();
      StringBuffer scanner = new StringBuffer();
      scanner.append(cards.toString() + " | \n");
      scanner.append(handScore);
      if (isBust())
      {
         scanner.append(" BUST!");
      }
      else if (isBlackjack())
      {                  
         scanner.append(" BLACKJACK!");
      }
      return scanner.toString();
   }

/**
 * Calculate score considering cases when ACE is 1 or 11 In case of the
 * dealer hit soft 17 os set to true the dealer has to hit on a soft 17 as
 * well.
 * 
 * @return
 */
   public int calculateScore() 
   {
      int res = 0;
      for (Card card : cards) 
      {
         res += card.getValue(false);
      }
      int res2 = 0;
      if (hasAce() && res < 12) 
      {
         res2 = res + 10;
         if (res2 > 17)
         {
            return res2;
         }
         else if (res2 == 17 && !hitSoft_17)
         {
            return res2;
         }
      }
      return res;
   }

/**
 * The dealer's hand is a Bust > 21
 * 
 * @return
 */
   public boolean isBust() 
   {
      handScore = calculateScore();
      return handScore > 21;
   }

/**
 * The dealer's hand is a Blackjack which is 2 cards with value 21 combined.
 * 
 * @return
 */
   public boolean isBlackjack() 
   {
      if (cards.size() == 2 && hasAce() && 
         (cards.get(0).getValue(false) == 10 || cards.get(1).getValue(false) == 10))
      {                  
         return true;
      }
      return false;
   }

/**
 * The turned-up card of the Dealer.
 * 
 * @return
 */
   public Card visibleCard() 
   {
      return cards.get(0);
   }

/**
 * Add a single card to the card list.
 */
   public void hit(Card card) 
   {
      cards.add(card);
   }

/**
 * Deal until 17 is reached, then stand.
 * 
 * @param deck
 */ 
   public void play(Deck deck) 
   {
      while (calculateScore() < 17) 
      {
         hit(deck.dealCard());
      }
      stand();
   }

/**
 * Returns false when dealer hits at 17 or more, true  otherwise.
 *
 * @return true when dealer hits at 17 or greater.
 */
   public boolean isHitSoft_17() 
   {
      return hitSoft_17;
   }

/**
 * Sets hitting at 17.
 *
 * @param histSoft_17
 */
   public void setHitSoft_17(boolean hitSoft_17) 
   {
      this.hitSoft_17 = hitSoft_17;
   }

/**
 * Returns the dealer's hand score.
 *
 * @return The dealer's hand score.
 */
   public int getHandScore() 
   {
      return handScore;
   }

/**
 * Sets the hand score for the dealer.
 *
 * @param handScore
 */
   public void setHandScore(int handScore) 
   {
      this.handScore = handScore;
   }
}


