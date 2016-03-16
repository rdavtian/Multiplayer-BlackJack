import java.util.Stack;

/**
            * Implementing a class Hand to represent a player's hand of cards
            * and writing java docs.
            *
            * @author Rus Adamovics and Ryan Moore
            * @version Program7 Blackjack Hand.java
            */

public class Hand 
{
   private Stack<Card> cards;
   private int value;
   private int highAceCount;
   private double bet;
   private double insuranceBet;
   private boolean insured=false;
   private boolean splitable=false;
   private boolean doubledDown=false;
   private boolean turnOver=false;

/**
 * Default Constructor that intializes the hand (a card) of a player along with no bet.
 */
        
   //CONSTRUCTORS
   public Hand()
   {
      this(new Stack<Card>(), 0);
   }

/**
 * Constructor that intializes the hand (a card) of a player with some betting value.
 *
 * @param bet                  
 */

   public Hand(double bet)
   {
      this(new Stack<Card>(), bet);
   }
    
/**
 * Constructor that intializes the declared instance variables of class Hand.                  
 */
    
   public Hand(Stack<Card> cards, double bet) 
   {
      this.cards = cards;
      this.placeBet(bet);
      this.highAceCount=0;
      this.value = 0;
      recalculateValue();
      this.insuranceBet = 0;
   }

/**
 * Returns or gets the value of the drawn card.
 *
 * @return The value of the card.
 *
 */
   
   //HELPERS
   public int getValue()
   {
      return value;
   }

/**
 * When an ace is drawn, method recalculates value on whether or not the 
 * ace should be a value of 1 or a value of 11 based on the current 
 * value state of the player and sets that new value to the ace. 
 *
 */
        
   public void recalculateValue()
   {
      value=0;
      for (Card c: cards)
      {
         if (c.isAce()) highAceCount++;
         value+=c.getValue();
         if (value>21 && highAceCount>0)
         {
            value-=10;
            highAceCount--;
         }
      }
   }

/**
 * Allows or does not allow splitting of the hand 
 * depending on if the game situation allows it. 
 *
 */
        
   private void isSplitable() 
   {
      splitable = false;
      if (cards.size()==2)
      {
         if (cards.get(0).getValue()==cards.elementAt(1).getValue())
         {
            splitable = true;
         }
      }
   }

/**
 * Allows a player to choose to stay and yield the turn.
 *
 */
        
   //BLACKJACK ACTIONS
   public void stay()
   {
      turnOver = true;
   }

/**
 * If a player hits, then a new card is drawn and the values are recalculated,
 * and sets the new updated value.
 *
 * @param  newCard
 *
 */
        
   public void hit(Card newCard)
   {
      cards.add(newCard);
      if (newCard.isAce())
      {
         highAceCount++;
      }
      if (value+newCard.getValue()>21 && highAceCount>0)
      {
         value-=10;
         highAceCount--;
      }
      value += newCard.getValue();
      if (cards.size()==2)
      {
         isSplitable(); //toggles splitable if splitable                 
      }
      if (doubledDown || value>=21)
      {
         turnOver = true;
      }
   }

/**
 * Allows a player to split a hand into two new hands 
 * depending if the situation can call for it and two 
 * new cards ae drawen from the top of the deck. 
 *
 * @return The new hand
 *
 */
        
   public Hand split()
   {
      splitable = false;
      Stack<Card> newCard = new Stack<Card>();
      newCard.push(this.cards.pop());
      recalculateValue();
      Hand newHand = new Hand(newCard, bet);
      return newHand;
   }

/**
 * If a player chooses to double down, then their bet is doubled
 * and must hit.
 *
 * @param newCard
 *
 */
        
   public void doubledown(Card newCard)
   {
      bet+=bet;
      doubledDown = true;
      hit(newCard);
   }

/**
 * Throws an exception if a player buys an insurance that is larger 
 *than (1/2) of the bet. Othewise, player can purcase betting insurance
 *of upt to 1/2 of the bet if needed. 
 *
 * @param insurance
 *
 */

   //TODO put this exception in the super
   public void purchaseInsurance(double insurance) throws Exception 
   {
      if (insurance/bet > 0.5) 
      {
         throw new Exception("You can only purchase insurance up to half of the bet per hand.");
      }
      insured = true;
      setInsuranceBet(insurance);
   }

/**
 * Returns The stack of cards. 
 *
 * @return The stack of the cards.
 *
 */
        
   //GETTERS AND SETTERS
   public Stack<Card> cards() 
   {
      return cards;
   }

/**
 * Returns true if the player is insured. 
 *
 * @return True if the player is insured, false otherwise.
 *
 */

   public boolean isInsured() 
   {
      return insured;
   }

/**
 * Returns or gets the bet of the player. 
 *
 * @return The bet made by the player.
 *
 */

   public double getBet() 
   {
      return bet;
   }

/**
 * Sets the bet made. 
 *
 * @param bet
 *
 */

   public void placeBet(double bet) 
   {
      this.bet = bet;
   }

/**
 * Returns true or false on whether or not a player is able to split. 
 *
 * @return True if the player can split their hand into two, false otherwise.
 *
 */
                
   public boolean splitable()
   {
      return splitable;
   }

/**
 * Returns true or false if the player can double down on the bet. 
 *
 * @return True if the player can double down on bet, false otherwise.
 *
 */
        
   public boolean hasDoubledDown()
   {
      return doubledDown;
   }

/**
 * Returns the amount of insurance bet the player bought. . 
 *
 * @return The amount of insurance bet that player purchased.
 *
 */

   public double getInsuranceBet() 
   {
      return insuranceBet;
   }

/**
 * Sets the insurance bet made by a player. 
 *
 * @param insuranceBet
 *
 */

   public void setInsuranceBet(double insuranceBet) 
   {
      this.insuranceBet = insuranceBet;
   }

/**
 * Returns true or false on whether or not a player chooses to stay. 
 *
 * @return True if the player stays, false otherwise.
 *
 */

   public boolean turnOver() 
   {
      return turnOver;
   }

/**
 * Returns or sets the ace count (1 or 11). 
 *
 * @return The ace count (1 or 11).
 *
 */
        
   public int getHighAceCount() 
   {
      return highAceCount;
   }
}
