import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
            * Implementing a class to represent a deck of cards and writing jav$
            *
            * @author Rus Adamovics and Ryan Moore
            * @version Program7 Blackjack Deck.java
            */
public class Deck 
{

   private ArrayList<Card> cards;
   private static int AVG_HAND_SIZE = 5;

   public Deck() 
   {
      cards = new ArrayList<Card>(52);
      generateDeck();
      this.shuffle();
   }

/**
 * Constructor that initialize a card Deck with multiple decks.
 * 
 * @param numDecks
 */
   public Deck(int numDecks) 
   {
      cards = new ArrayList<Card>(52 * numDecks);
      for (int i = 0; i < numDecks; i++) 
      {
         generateDeck();
      }
      this.shuffle();
   }

/**
 * Fill deck with cards from all suits and ranks
 */
   public void generateDeck() 
   {
      for (Suit suit : Suit.values()) 
      {
         for (Rank symbol : Rank.values()) 
         {
            cards.add(new Card(suit, symbol));
         }
      }
   }

/**
 * Randomly shuffle the deck, easy way to shuffle!.
 */
   public void shuffle() 
   {
      Collections.shuffle(this.cards);
   }

/**
 * Deal a single card from the deck, the card is removed from the end to
 * avoid shifting of elements.
 * 
 * @return
 */
   public Card dealCard() 
   {
      if (cards.size() > 0) 
      {
         Card toDeal = cards.get(cards.size() - 1);
         cards.remove(cards.size() - 1);
         return toDeal;
      }
      return null;
   }

/**
 * Whether or not a deck can deal a specific number of players is determined
 * here, by multiplying the number of players by the average player hand
 * size.
 * 
 * @param playerCount
 * @return
 */
   public boolean canDeal(int playerCount) 
   {
      return cards.size() >= playerCount * AVG_HAND_SIZE;
   }

/**
 * Overrides the toString method.
 */
   public String toString() 
   {
      StringBuffer scanner = new StringBuffer();
      for (Card card : this.cards) 
      {
         scanner.append(card);
      }
      return scanner.toString();
   }

/**
 * Returns the cards.
 *
 * @return The cards.
 */
   public ArrayList<Card> getCards() 
   {
      return cards;
   }

/**
 * Sets the cards of the player.
 *
 * @param cards
 */
   public void setCards(ArrayList<Card> cards) 
   {
      this.cards = cards;
   }
}


