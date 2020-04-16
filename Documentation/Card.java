/**
            * Implementing a class Card and writing Java Docs.
            * 
            * @author Rus Adamovics and Ryan Moore
            * @version Program7 Blackjack Card.java
            */

public class Card 
{
   private boolean face;
   private boolean ace;
   private String name;
   private int value;
   private int suite; //1=Spades, 2=Heart, 3=Clubs, 4=Diamonds
        
/**
 * Constructor that intializes the declared instance variables name, suite, ace, 
 * face, and value.
 */   
   
   public Card(String name, int suite) 
   {
      this.name = name;
      this.suite = suite;
      this.ace = false;
      this.face = false;
      if (this.name.equals("Ace")) 
      {
         this.ace = true;
         this.setValue(11);
      }
      else if (this.name.equals("Jack") || this.name.equals("Queen") || this.name.equals("King")) 
      {
         this.face = true;
         this.setValue(10);
      }
      else 
      {
         this.setValue(Integer.valueOf(name));
      }
   }

/**
 * Returns the face of the card if a face card is drawn. 
 *
 * @return The face of the card drawn. 
 *
 */   
   
   public boolean isFace() 
   {
      return face;
   }

/**
 * Returns the ace card if an ace is drawn.
 *
 * @return The ace card. 
 *
 */

   public boolean isAce() 
   {
      return ace;
   }

/**
 * Returns the name of the card that is drawn.                 
 *
 * @return The name of the card (2 through 10, jack, king, queen, ace).         
 *               
 */

   public String getName() 
   {
      return name;
   }

/**
 * Returns the suit of the card that is drawn.                 
 *
 * @return The suit of the card (diamonds, spades, clubs, and hearts).         
 *               
 */

   public int getSuite() 
   {
      return suite;
   }

/**
 * Returns the number value of the card drawn.                 
 *
 * @return The value of the card (2, 3, 4, 5, 6, 7, 8, 9, 10, or 11).         
 *               
 */

   public int getValue() 
   {
      return value;
   }

/**
 * Sets the value of the card drawn.                 
 *
 * @param value
 *               
 */

   public void setValue(int value) 
   {
      this.value = value;
   }

/**
 * Returns the string conversion of the name and suit type of the card drawn.                 
 *
 * @return A string representation of the name of the card and its suit.         
 *               
 */

   public String toString()
   {
      String suiteString="";
      switch(suite)
      {
         case 1:
            suiteString = "Spades";
            break;
         case 2:
            suiteString = "Hearts";
            break;
         case 3:
            suiteString = "Clubs";
            break;
         case 4:
            suiteString = "Diamonds";
            break;
      }               
      return (name+" of "+suiteString);
   }
}


