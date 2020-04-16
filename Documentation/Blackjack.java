/**
            * Implementing a driver for the game of BlackJack.
            * 
            * @author Rus Adamovics and Ryan Moore
            * @version Program7 Blackjack Blackjack.java
            */

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack 
{
   private static ArrayList<Player> players = new ArrayList<Player>();
   private static int numOfDecks=0;
   private static int numPlayers=0;
   private static Scanner keyboard = new Scanner(System.in);
   private static Deck deck;       
   private static NumberFormat formatter = NumberFormat.getCurrencyInstance();
   private static Player dealer;
   private static boolean gameOver;
        
   public static void newGame() 
   {
      System.out.println("Welcome to Blackjack!");
                
      //Create Deck
      System.out.println("How many decks of cards would you like to play with?");
      numOfDecks = keyboard.nextInt();
      keyboard.nextLine();
      while (numOfDecks<1 || numOfDecks>5)
      {
         System.out.println("Please enter a number of decks between 1 and 5.");
         numOfDecks = keyboard.nextInt();
         keyboard.nextLine();
      }
      deck = new Deck(numOfDecks);
                
      //Get Num of Players
      System.out.println("Please enter the number players?");
      numPlayers = keyboard.nextInt();
      keyboard.nextLine();
      while (numPlayers<1 || numPlayers>5)
      {
         System.out.println("Please enter a number of decks between 1 and 5.");
         numPlayers = keyboard.nextInt();
         keyboard.nextLine();
      }
                
      // Create Players
      for (int i=0; i<numPlayers; i++)
      {
         System.out.print("Please give a name for player "+(i+1)+": ");
         System.out.println("");
         String pName = keyboard.nextLine();
         if (pName.toLowerCase().equals("dealer"))
         {
            System.out.print("That name is reserved, please enter a new name: ");
            pName = keyboard.nextLine();
         }
         Player newPlayer = new Player(pName,i);
         players.add(newPlayer);
      }
   }
        
   public static void deal()
   {
      //Deal 2 cards to everyone who bet in the correct order 
      for (int j=0; j<2; j++)
      {
         for (int i=0; i<players.size(); i++)
         {
            if (deck.cards().size()==0)
            {
               deck = new Deck(numOfDecks);
            }
            if (players.get(i).hands().get(0).getBet()!=0)
            {
               deck.dealCard(players.get(i));
            }
         }
         deck.dealCard(dealer);
      }
   }
        
   public static void placeBets()
   {
      for (int i=0; i<players.size(); i++)
      {
         Player p = players.get(i);
         //Get bet from each player, more than 0 and less than their bank.
         boolean confirmNoBet=false;
         if (p.getBank()>0)
         {
            System.out.println("Player "+(i+1)+": "+p.getName()+" bank: "+formatter.format(p.getBank()));
            System.out.println("How much would you like to bet?");
            double bet = keyboard.nextDouble();
            keyboard.nextLine();
            while((bet<=0 || bet>p.getBank()))
            {
               if (confirmNoBet)
               {
                  break;
               }
               else if (bet==0)
               {
                  System.out.println("Are you sure you want to place no bet and pass this round? (yes/no)");
                  if (keyboard.nextLine().toLowerCase().equals("yes"))
                  {
                     confirmNoBet = true;
                  }
                  else 
                  {
                     bet=-1;
                  }
               }
               else 
               {
                  System.out.println("Please enter a bet more than 0 and less than or equal to your bank?");
                  bet = keyboard.nextDouble();                                            
                  keyboard.nextLine();
               }
            }
            p.setBank(p.getBank()-bet);
            p.hands().get(0).placeBet(bet);
         }
      }
   }
        
   public static boolean anyBets()
   {
      boolean anyBets = false;
      for (int i=0;i<players.size();i++)
      {
         if (players.get(i).hands().get(0).getBet()>0)
         {
            anyBets = true;
         }
      }
      return anyBets;
   }
        
        
   public static void main(String args[]) 
   {
      newGame();
      dealer = new Player("Dealer",players.size());
      do 
      {
         Hand dHand = dealer.hands().get(0);
         gameOver = true;
         placeBets();
         if (anyBets())
         {
            System.out.println("Let the games begin!");
            deal();
            //TODO Check for insurance
            //TODO only play if there are bets
            //Commence Play, loop through players until reach Dealer
            for (int i=0; i<players.size();i++)
            {
               Player p = players.get(i);
               for (int j=0;j<p.hands().size();j++)
               {
                  Hand h = p.hands().get(j);
                  while(!h.turnOver() && h.getBet()>0)
                  {
                     //Print Status before asking for action
                     System.out.println("");                                         
                     System.out.println("Player "+(i+1)+": "+p.getName()+" - Hand "+(j+1));
                     System.out.println("Bet: "+formatter.format(h.getBet()));
                     for (int cardNum=0; cardNum<h.cards().size(); cardNum++)
                     {
                        System.out.println("  "+h.cards().get(cardNum).toString());
                     }
                     System.out.println("Total Value: "+h.getValue());
                     System.out.println("");
                     System.out.println("Dealer Face-up Card:");
                     System.out.println("  "+dHand.cards().get(1).toString());
                     System.out.println("");
                     int validAction = 2;
                     System.out.println("Please type the number for the action you would like to do:");
                     System.out.println("1 - Hit");
                     System.out.println("2 - Stay");
                     if (h.cards().size()==2)
                     {
                        System.out.println("3 - Double Down");
                        validAction++;
                     }
                     if (h.splitable())
                     {
                        System.out.println("4 - Split");
                        validAction++;
                     }
                     int action = keyboard.nextInt();
                     keyboard.nextLine();
                     //Catch invalid action entry
                     while (action<1 || action>validAction)
                     {
                        System.out.println("Please enter a valid action:");
                        System.out.println("Please type the number for the action you would like to do:");
                        System.out.println("1 - Hit");
                        System.out.println("2 - Stay");
                        if (h.cards().size()==2 && p.getBank()>h.getBet())
                        {
                           System.out.println("3 - Double Down");
                        }
                        if (h.splitable() && p.getBank()>h.getBet())
                        {
                           System.out.println("4 - Split");
                        }
                        action = keyboard.nextInt();
                        keyboard.nextLine();
                     }
                     //Execute Actions
                     switch(action)
                     {
                        case 1: //Hit
                           h.hit(deck.pop());
                           break;
                        case 2: //Stay
                           h.stay();
                           break;
                        case 3: //Double Down
                           if (h.cards().size()==2 && p.getBank()>=h.getBet())
                           {
                              p.setBank(p.getBank()-h.getBet());
                              h.doubledown(deck.pop());
                           }
                           else if (h.cards().size()==2)
                           {
                              System.out.println("You can not afford to Double Down");
                           }
                           else 
                           {
                              System.out.println("You can only Double Down with two cards");
                           }
                           break;
                        case 4: //Split
                           if (h.splitable() && p.getBank()>=h.getBet())
                           {
                              p.setBank(p.getBank()-h.getBet());
                              p.hands().add(h.split());
                              h.hit(deck.pop());
                              p.hands().get(j+1).hit(deck.pop());
                           }
                           else if (h.splitable())
                           {
                              System.out.println("You can not afford to Split");                                                                      
                           }
                           else 
                           {
                              System.out.println("Your cards are not splitable");
                           }
                           break;
                     }
                     // 21 or bust
                     if (h.getValue()>20)
                     {
                        if (h.getValue()==21)
                           {
                              System.out.println("Blackjack!");
                              System.out.println();
                           } 
                        else 
                        {
                           System.out.println("Bust");
                           System.out.println();
                        }
                     }
                  }
                  //Print Status after finish turn
                  System.out.println("");                                         
                  System.out.println("Player "+(i+1)+": "+p.getName()+" - Hand "+(j+1));
                  System.out.println("Bet: "+formatter.format(h.getBet()));
                  for (int cardNum=0; cardNum<h.cards().size(); cardNum++)
                  {
                     System.out.println(""+h.cards().get(cardNum).toString());
                  }
                  System.out.println("Total Value: "+h.getValue());
               }
               players.set(i,p);
            }
            //DEALER ACTION
            System.out.println("");
            System.out.println("Dealer: ");
            while (dHand.getValue()<17 || (dHand.getValue()==17 && dHand.getHighAceCount()>0))
            {
               for (int cardNum=0; cardNum<dHand.cards().size(); cardNum++)
               {
                  System.out.println(""+dHand.cards().get(cardNum).toString());                                                   
               }
               System.out.println("Total Value: "+dHand.getValue());
               System.out.println("");
               try 
               {
                  Thread.sleep(1000);
               } 
               catch(InterruptedException ex) 
               {
                  Thread.currentThread().interrupt();
               }
               dHand.hit(deck.pop());
               System.out.println("Dealer hits");
            }
            for (int cardNum=0; cardNum<dHand.cards().size(); cardNum++)
            {
               System.out.println(""+dHand.cards().get(cardNum).toString());                                                   
            }
            System.out.println("Total Value: "+dHand.getValue());
            System.out.println("");
            System.out.println("Action Over");
            System.out.println("");
            //pay players
            //reset player hands
            //see if games over
            int dScore = dHand.getValue();
            for (int i=0; i<players.size();i++)
            {
               Player p = players.get(i);
               for (int j=0; j<p.hands().size(); j++)
               {
                  Hand h = p.hands().get(j);
                  int score = h.getValue();
                  // Pay Players
                  // Player Blackjack
                  if (score==21)
                  {
                     if (dScore==21)
                     {
                        //push
                        p.setBank(p.getBank()+h.getBet());
                        p.addDraw();
                        System.out.println("Player "+(i+1)+" "+p.getName());
                        System.out.println("Hand "+(j+1)+" - "+"Bet: "+h.getBet());
                        System.out.println("("+h.getValue()+"/"+dHand.getValue()+")"+": Push");
                        System.out.println("");
                     } 
                     else 
                     {
                        // Blackjack win
                        p.setBank(p.getBank()+h.getBet()*2.5);
                        p.addWin();
                        System.out.println("Player "+(i+1)+": "+p.getName());
                        System.out.println("Hand "+(j+1)+" - "+"Bet: "+h.getBet());
                        System.out.println("("+h.getValue()+"/"+dHand.getValue()+")"+": BlackJack!");
                        System.out.println("Winnings: "+h.getBet()*1.5);
                        System.out.println("");
                     }
                  }
                  // Dealer Bust or beat dealer by score
                  else if ((dScore>21) || (score<21 && score>dScore))
                  {
                     p.setBank(p.getBank()+h.getBet()*2);
                     p.addWin();
                     System.out.println("Player "+(i+1)+": "+p.getName());
                     System.out.println("Hand "+(j+1)+" - "+"Bet: "+h.getBet());
                     System.out.println("("+h.getValue()+"/"+dHand.getValue()+")"+": Win!");
                     System.out.println("Winnings: "+h.getBet());
                     System.out.println("");
                  }
                  //Push
                  else if (score == dScore)
                  {
                     p.setBank(p.getBank()+h.getBet());
                     p.addDraw();
                     System.out.println("Player "+(i+1)+": "+p.getName());
                     System.out.println("Hand "+(j+1)+" - "+"Bet: "+h.getBet());
                     System.out.println("("+h.getValue()+"/"+dHand.getValue()+")"+": Push");
                     System.out.println("");
                  }
                  //Lose
                  else if (score>21)
                  {
                     p.addLose();
                     System.out.println("Player "+(i+1)+": "+p.getName());
                     System.out.println("Hand "+(j+1)+" - "+"Bet: "+h.getBet());
                     System.out.println("BUST"+": Lose");
                     System.out.println("");                                         
                  }
                  else 
                  {
                     p.addLose();
                     System.out.println("Player "+(i+1)+": "+p.getName());
                     System.out.println("Hand "+(j+1)+" - "+"Bet: "+h.getBet());
                     System.out.println("("+h.getValue()+"/"+dHand.getValue()+")"+": Lose");
                     System.out.println("");
                  }
               }
               // Reset Hands                          
               p.clearHand();
               // Check if gameOver
               if (p.getBank()>0)
               {
                  gameOver = false;
               }
            }                       
            dealer.clearHand();
            System.out.println("DEALER CLEARED");
         } 
         else 
         {
            gameOver = false;
         }
      } 
      while (!gameOver);
      {
         System.out.println("GAME OVER");
      }
   }
}

