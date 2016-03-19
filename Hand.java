import java.util.LinkedList;

/**
            * Implementing a class to represent card hand and writing java docs
            *
            * @author Rus Adamovics and Ryan Moore
            * @version Program7 Blackjack Hand.java
            */
public abstract class Hand 
{
   protected LinkedList<Card> cards;
   protected boolean stand;

   public Hand() 
   {
      this.cards = new LinkedList<Card>();
   }

/**
 * The hand has an ACE.
 * 
 * @return
 */
   public boolean hasAce() 
   {
      for (Card card : cards) 
      {
         if (card.getRank() == Rank.ACE)
            return true;
      }
      return false;
   }

/**
 * The hand has double Aces and can be split.
 * 
 * @return
 */
   public boolean doubleAces() 
   {
      return cards.size() == 2 && cards.get(0).getRank() == Rank.ACE
      && cards.get(1).getRank() == Rank.ACE;
   }

/**
 * Gets the cards of a player.
 *
 * @return The cards.
 */
   public LinkedList<Card> getCards() 
   {
      return cards;
   }

/**
 * Sets the cards of a player.
 */
   public void setCards(LinkedList<Card> cards) 
   {
      this.cards = cards;
   }

/**
 * returns true if a player is done and chooses to stand.
 *
 * @return True is the player stays. false otherwise. 
 */ 
   public boolean isDone() 
   {
      return this.stand;
   }

/**
 * Sets stand to true if player chooses to stand.
 */ 
   public void stand() 
   {
      stand = true;
   }

/**
 * Add a single card to the card list in the hand.
 * 
 * @param card
 */
   public abstract void hit(Card card);
}


