import java.util.Random;
import java.util.Stack;

/**
            * Implementing a class to represent a deck of cards and writing jav$
            *
            * @author Rus Adamovics and Ryan Moore
            * @version Program7 Blackjack Deck.java
            */

public class Deck 
{
   private Stack<Card> cards = new Stack<Card>();

/**
 * Constructor that declares and intializes the number of decks to 1.
 */
        
   //CONSTRUCTORS
   //SingleDeck
   public Deck()
   {
      this(1);
   }

/**
 * Creates or constructs a deck of cards by pushing name of each 
 * card and its value into a stack of cards and then shuffles the cards.
 *
 * @param numberOfDecks 
 *
 */
   
   public Deck(int numberOfDecks)
   {
      for (int k=0; k<numberOfDecks; k++)
      {                    
         // 4 Suites
         for (int i=1; i<=4;i++)
         {
            // Faces and Aces
            cards.push(new Card("Ace", i));
            cards.push(new Card("King", i));
            cards.push(new Card("Queen", i));
            cards.push(new Card("Jack", i));
            // Number Cards
            for (int j=2;j<=10;j++)
            {
               cards.push(new Card(String.valueOf(j), i));
            }
         }
      }
      this.shuffle();
   }

/**
 * Shuffles a deck of cards. 
 *
 * @return The same deck of cards but shuffled.
 *
 */

   public Deck shuffle()
   {
      Random r = new Random();
      for (int i=0;i<cards.size();i++)
      {
         int randomCard = r.nextInt(cards.size());
         cards.setElementAt(cards.set(randomCard, cards.elementAt(i)), i);
      }
      return this;
   }

/**
 * Removes and returns the top card in the deck of cards.
 *
 * @return Picks up or removes the top card from the deck and returns that card. 
 *
 */

   public Card pop()
   {
      return cards.pop();
   }

/**
 * Deals the top card of the deck to the player.        
 *  
 * @param p
 *  
 */

   public void dealCard(Player p)
   {
      p.hands().get(0).hit(cards.pop());
   }
        
/**
 * Returns a deck of cards. 
 *  
 * @return the stack of cards.
 *  
 */

   public Stack<Card> cards() 
   {
      return cards;
   }
}

