import java.util.HashMap;

/**
            * Implementing a class to represent a human player's hand and writing java docs
            *
            * @author Rus Adamovics and Ryan Moore
            * @version Program7 Blackjack PlayerHand.java
            */
public class PlayerHand extends Hand 
{
   private int bet;
   private boolean isSplit;
   private boolean splitAces;
   private HashMap<String, Integer> handScore;

   public PlayerHand() 
   {
      super();
      bet = 0;
      isSplit = false;
      splitAces = false;
      stand = false;
      handScore = new HashMap<String, Integer>();
   }

/**
 * Player hand is Bust > 21
 * 
 * @return
 */
   public boolean isBust() 
   {
      calculateScore();
      if (handScore.get("soft") == -1 && handScore.get("hard") > 21)
      {
         return true;
      }
      return false;
   }

/**
 * Player's scores allow them to hit again.
 * 
 * @return
 */
   public boolean canHit() 
   {
      calculateScore();
      return handScore.get("hard") < 21 || handScore.get("soft") > -1;
   }

/**
 * Add a single card to the cars list.
 */
   public void hit(Card card) 
   {
      cards.add(card);
   }

/**
 * Calculate score of cards in hand both hard and soft in case Aces exist.
 */
   public void calculateScore() 
   {
      int hardScore = 0;
      for (Card card : cards) 
      {
         hardScore += card.getValue(false);
      }
      int softScore = -1;
      if (hasAce() && hardScore < 12)
      {
         softScore = hardScore + 10;
      }
      handScore.put("hard", hardScore);
      handScore.put("soft", softScore);
   }

/**
 * Player hand is Blackjack card values sum to 21
 * 
 * @return
 */
   public boolean isBlackjack() 
   {
      if (cards.size() == 2 && hasAce() && !isSplit && 
      (cards.get(0).getValue(false) == 10 || cards.get(1).getValue(false) == 10))
      {
         return true;
      }
      return false;
   }

/**
 * Player can split their hand since two cards with identical ranks are
 * there.
 * 
 * @return
 */
   public boolean canSplit() 
   {
      if (cards.size() == 2 && cards.get(0).getRank() == cards.get(1).getRank())
      {                  
         return true;
      }          
      return false;
   }

/**
 * Splitting of the player's hand
 * 
 * @return
 */
   public Hand split() 
   {
      isSplit = true;
      boolean aces = doubleAces();
      splitAces = aces;
      PlayerHand hand = new PlayerHand();
      hand.isSplit = true;
      hand.splitAces = aces;
      hand.hit(cards.pop());
      return hand;
   }

   public String toString() 
   {
      calculateScore();
      StringBuffer sbuffer = new StringBuffer();
      for (Card card : cards)
      {
         sbuffer.append(card.toString());
      }
      sbuffer.append("\nScore: ");
      sbuffer.append(handScore.get("hard"));
      if (handScore.get("soft") > -1)
      {   
         sbuffer.append(" or " + handScore.get("soft"));
      }
      if (isBust())
      {
         sbuffer.append(" BUST!");
      }
      else if (isBlackjack())
      {
         sbuffer.append(" BLACKJACK!");
      }
      else if (higherTotal() == 21)
      {
         sbuffer.append(" WIN!");
      }
      return sbuffer.toString();
   }

/**
 * Consider the higher total from the soft and hard scores.
 * 
 * @return
 */
   public int higherTotal() 
   {
      try 
      {
         int soft = handScore.get("soft");
         int hard = handScore.get("hard");
         return soft > hard ? soft : hard;
      } 
      catch (Exception ex) 
      {
         return 0;
      }
   }

/**
 * Returns or gets the players' bet.
 *
 * @return bet
 */
   public int getBet() 
   {
      return bet;
   }

/**
 * Sets the bet.
 *
 * @param bet
 */
   public void setBet(int bet) 
   {
      this.bet = bet;
   }

/**
 * Returns true if players can split.    
 *
 * @return true if players can split, false otherwise
 */ 
   public boolean isSplit() 
   {
      return isSplit;
   }

/**
 * Sets the split if true.      
 *
 * @param isSplit   
 */
   public void setSplit(boolean isSplit) 
   {
      this.isSplit = isSplit;
   }

/**
 * Returns true if players can split with aces, false otherwise.    
 *
 * @return true if aces can split    
 */
   public boolean isSplitAces() 
   {
      return splitAces;
   }

/**
 * Sets the splitting with the aces .      
 *
 * @param splitAces
 */
   public void setSplitAces(boolean splitAces) 
   {
      this.splitAces = splitAces;
   }

/**
 * Returns true if player's can stand, false otherwise.      
 *
 * @return true if players can stand or stay.   
 */
   public boolean isStand() 
   {
      return stand;
   }

/**
 * If a player chooses to increase their bet, then the additional bet
 * gets added to the current bet.
 */
   public void increaseBet(int addedBet) 
   {
      this.bet += addedBet;
   }

/**
 * Sets the stand if player chooses to stand.      
 *
 * @param stand   
 */
   public void setStand(boolean stand) 
   {
      this.stand = stand;
   }

   public HashMap<String, Integer> getHandScore() 
   {
      return handScore;
   }

   public void setHandScore(HashMap<String, Integer> handScore) 
   {
      this.handScore = handScore;
   }
}


