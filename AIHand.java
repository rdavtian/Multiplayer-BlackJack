import java.util.Random;

/**
            * Implementing a class to represent the RandomAI's hand 
            * which extends hand and writing java docs.
            *
            * @author Rus Adamovics and Ryan Moore
            * @version Program7 Blackjack AIHand.java
            */
public class AIHand extends Hand 
{

   private boolean hitSoft_17;
   private int handScore;

   public AIHand() 
   {
      super();
      hitSoft_17 = false;
      handScore = 0;
   }

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
 * RandomAI hit soft 17 is set to true the dealer has to hit on a soft 17 as
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
 * The AI's hand is a Bust > 21
 * 
 * @return
 */
   public boolean isBust() 
   {
      handScore = calculateScore();
      return handScore > 21;
   }

/**
 * The AI's hand is a Blackjack which is 2 cards with value 21 combined.
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
 * Add a single card to the card list.
 */
   public void hit(Card card) 
   {
      cards.add(card);
   }

/**
 * Generate a random number, hit or stay depending on number.
 * 
 * @param deck
 */ 
   public void play(Deck deck) 
   {
      while (calculateScore() < 10) {
          hit(deck.dealCard());
      }
      while (calculateScore() < 17 && calculateScore() > 10) 
      {
         double r = Math.random();
         if (r < .5) {
             hit(deck.dealCard());
       }
             
      }
      stand();
   }

   public boolean isHitSoft_17() 
   {
      return hitSoft_17;
   }

   public void setHitSoft_17(boolean hitSoft_17) 
   {
      this.hitSoft_17 = hitSoft_17;
   }

   public int getHandScore() 
   {
      return handScore;
   }

   public void setHandScore(int handScore) 
   {
      this.handScore = handScore;
   }
}



