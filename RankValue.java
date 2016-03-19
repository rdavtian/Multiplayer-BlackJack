/**
            * Implementing a class to represent the values of the card ranks and writing java docs
            *
            * @author Rus Adamovics and Ryan Moore
            * @version Program7 Blackjack RankValue.java
            */
public class RankValue 
{
   public int getValue(Rank rank) 
   {
      switch (rank) 
      {
         case ACE:
            return 1;
         case TWO:
            return 2;
         case THREE:
            return 3;
         case FOUR:
            return 4;
         case FIVE:
            return 5;
         case SIX:
            return 6;
         case SEVEN:
            return 7;
         case EIGHT:
            return 8;
         case NINE:
            return 9;
         case TEN:
            return 10;
         case JACK:
            return 10;
         case QUEEN:
            return 10;
         case KING:
            return 10;
      }
      return 0;
   }
}


