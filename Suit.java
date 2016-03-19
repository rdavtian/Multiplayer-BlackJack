/**
            * Implementing an enum class to represent card suits and writing java docs.
            *
            * @author Rus Adamovics and Ryan Moore
            * @version Program7 Blackjack Suit.java
            */
public enum Suit 
{
   SPADE("\u2660", 'S'), 
   CLUB("\u2663", 'C'),
   HEART("\u2665", 'H'), 
   DIAMOND("\u2666", 'D');
   //SPADE("Spades"), CLUB("Clubs"), HEART("Hearts"), DIAMOND("Diamonds");
   private final String icon;
   private final char name;

   Suit(String ico, char n) 
   {
      icon = ico;
      name = n;
   }

/**
 * Overrides toString for name of suit
 *
 * @return String representation of name  
 */
   public String toString() 
   {
      return name + "";
   }

/**
 * Returns the icon of the suit.      
 *
 * @return icon of the suit   
 */
   public String getIcon() 
   {
      return icon;
   }

/**
 * Returns or gets the name of the suit.      
 *
 * @return the name of the suit   
 */
   public char getName() 
   {
      return name;
   }
}

