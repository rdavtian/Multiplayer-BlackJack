import java.util.Scanner;
/**
            * Implementing a class to represent playing cards and writing jav$
            *
            * @author Rus Adamovics and Ryan Moore
            * @version Program7 Blackjack Card.java
            */
public class Card 
{
   private Suit suit;
   private Rank rank;

/**
 * Constructor initializes the suit and rank of a card.
 */   
   public Card(Suit suit, Rank rank) 
   {
      this.suit = suit;
      this.rank = rank;
   }

/**
 * Get value of card, if card is ACE, then return 1 or 11 depending on value
 * of soft hand.
 * 
 * @param soft
 * @return
 */
   public int getValue(boolean soft)
   {
      if (soft && this.isAce())
      {
         return 11;
      }  
      return this.rank.getValue();
   }

/**
 * Current card is an ACE
 * @return
 */
   private boolean isAce() 
   {
      return rank == Rank.ACE;
   }

/**
 * Overrides the toString method. 
 */
   public String toString() 
   {
      StringBuffer scanner = new StringBuffer();
      scanner.append("_____\n");
      scanner.append("| " + this.getRank() + " |\n");
      scanner.append("| " + this.getSuit() + " |\n");
      scanner.append("-----\n");
      return scanner.toString();
   }

/**
 * Returns the String representing the name of the rank and suit and the cards value.
 * 
 * @return The card's value, rank, and suit.
 */
   public String getValueString() 
   {
      return "a" + rank.getName() + " " + suit.getName() + ":"
      + rank.getValue() + "| ";
   }

/**
 * Returns the rank of the card drawn.
 * 
 * @return The rank of the card.
 */
   public Rank getRank() 
   {
      return rank;
   }

/**
 * Sets the rank of the card drawn.
 */
   public void setRank(Rank rank) 
   {
      this.rank = rank;
   }

/**
 * Returns the suit of the card.
 * 
 * @return The suit of the card.
 */
   public Suit getSuit() 
   {
      return suit;
   }

/**
 * Sets the suit of the card drawn.
 */
   public void setSuit(Suit suit) 
   {
      this.suit = suit;
   }
}


