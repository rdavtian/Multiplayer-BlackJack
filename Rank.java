/**
            * Implementing an enum class to represent the ranks of cards and writing java docs
            *
            * @author Rus Adamovics and Ryan Moore
            * @version Program7 Blackjack Rank.java
            */
public enum Rank 
{
   ACE("A"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT(
   "8"), NINE("9"), TEN("10"), JACK("J"), QUEEN("Q"), KING("K");

   private final String name;
   private RankValue value; 

/**
 * COnstructor for the Rank that initializes name and value of the card.
 */
   Rank(String n) 
   {
      name = n;
      value = new RankValue();
   }

/**
 * Returns or gets the name of the rank.
 * 
 * @return the name of the Rank
 */
   public String getName() 
   {
      return name;
   }
        
/**
 * Overrides the toString method and returns name.
 * 
 * @ returns the name as a String representation
 */
   public String toString() 
   {
      return name;
   }
    
/**
 * Returns the value of the rank of the card. 
 * 
 * @return the value of the card
 */
   public int getValue() 
   {
      return value.getValue(this);
   }
}
