import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
            * Implementing a driver for the game of BlackJack.
            *
            * @author Rus Adamovics and Ryan Moore
            * @version Program7 Blackjack Blackjack.java
            */

public class Blackjack 
{
   private static ArrayList<Player> players = new ArrayList<Player>();
   private static ArrayList<AI> ais = new ArrayList<AI>();
   private static List<Player1> all = new ArrayList<Player1>();
   private static int numOfDecks=0;
   private static int numPlayers=0;
   private static int numAIs = 0;
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
      System.out.println("Enter number of human players");
      numPlayers = keyboard.nextInt();
      keyboard.nextLine();
      while (numPlayers<0|| numPlayers>5)
      {
         System.out.println("Please enter a number of players between 0 and 5.");
         numPlayers = keyboard.nextInt();
         keyboard.nextLine();
      }
      //Get Num of AIs
      System.out.println("Enter number of ai players");
      numAIs = keyboard.nextInt();
      keyboard.nextLine();
      while (numAIs<0 || numAIs>5)
      {
	      System.out.println("Please enter a number of AI's between 0 and 5.");
         numAIs = keyboard.nextInt();
         keyboard.nextLine();
      }
     int total = numAIs + numPlayers;
     if (total == 0) 
     {
        throw new IllegalArgumentException("*crickets....no one is here, so I'm outta here.");
	//System.out.println("*crickets... no one is here, see ya next time!");
     }

                
      // Create Players
      for (int i=0; i<numPlayers; i++)
      {
         System.out.print("Please give a name for player "+(i+1)+": ");
         System.out.println("");
         String pName = keyboard.nextLine();
         if (pName.toLowerCase().equals("dealer"))
         {
            System.out.print("Stop trying to be a cool kid, use a different name ");
            pName = keyboard.nextLine();
         }
         Player newPlayer = new Player(pName,i);
         players.add(newPlayer);
	 all.add(newPlayer);
      }

      // Create AIs
      for (int i=0; i<numAIs; i++)
      {
         System.out.print("Please give a name for AI "+(i+1)+": ");
         System.out.println("");
         String pName = keyboard.nextLine();
         if (pName.toLowerCase().equals("dealer"))
         {
            System.out.print("Stop trying to be a cool kid, use a different name ");
            pName = keyboard.nextLine();
         }
         AI newPlayer = new AI(pName,i);
         ais.add(newPlayer);
	 all.add(newPlayer);
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

      for (int r=0; r <ais.size(); r++) {
	      AI p = ais.get(r);
	      System.out.println("AI "+(r+1)+": "+p.getName()+" bank: "+ formatter.format(p.getBank()));
	      System.out.println("Since you're an AI, you're betting $20, for now.");
	      AI a = ais.get(r);
	      boolean confirmNoBet=false;
	      if (a.getBank() > 20) {
		      a.setBank(a.getBank()-20);
		      a.hands().get(0).placeBet(20);
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
      dealer = new Player("Dealer",all.size());
      do 
      {
         Hand dHand = dealer.hands().get(0);
         gameOver = true;
         placeBets();
         if (anyBets())
         {
            System.out.println("Let the games begin!");
            deal();
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
	    //Commence Play, loop through ais until reach Dealer
            for (int i=0; i<ais.size();i++)
            {
               AI q = ais.get(i);
               for (int j=0;j<q.hands().size();j++)
               {
                  Hand h = q.hands().get(j);
                  while(!h.turnOver() && h.getBet()>0)
                  {
                     //Print Status before asking for action
                     System.out.println("");                                         
                     System.out.println("AI "+(i+1)+": "+q.getName()+" - Hand "+(j+1));
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
                     //Execute Actions
		     if (h.getValue() <17) {
			     h.hit(deck.pop());
		     }
		     else if (h.getValue() >=17) {
			     h.stay();
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
                  System.out.println("AI "+(i+1)+": "+q.getName()+" - Hand "+(j+1));
                  System.out.println("Bet: "+formatter.format(h.getBet()));
                  for (int cardNum=0; cardNum<h.cards().size(); cardNum++)
                  {
                     System.out.println(""+h.cards().get(cardNum).toString());
                  }
                  System.out.println("Total Value: "+h.getValue());
               }
               ais.set(i,q);
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
	       //now for the AIs
	        for (int i=0; i<ais.size();i++)
            {
               AI p = ais.get(i);
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
                        System.out.println("AI "+(i+1)+" "+p.getName());
                        System.out.println("Hand "+(j+1)+" - "+"Bet: "+h.getBet());
                        System.out.println("("+h.getValue()+"/"+dHand.getValue()+")"+": Push");
                        System.out.println("");
                     } 
                     else 
                     {
                        // Blackjack win
                        p.setBank(p.getBank()+h.getBet()*2.5);
                        p.addWin();
                        System.out.println("AI "+(i+1)+": "+p.getName());
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
                     System.out.println("AI "+(i+1)+": "+p.getName());
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
                     System.out.println("AI "+(i+1)+": "+p.getName());
                     System.out.println("Hand "+(j+1)+" - "+"Bet: "+h.getBet());
                     System.out.println("("+h.getValue()+"/"+dHand.getValue()+")"+": Push");
                     System.out.println("");
                  }
                  //Lose
                  else if (score>21)
                  {
                     p.addLose();
                     System.out.println("AI "+(i+1)+": "+p.getName());
                     System.out.println("Hand "+(j+1)+" - "+"Bet: "+h.getBet());
                     System.out.println("BUST"+": Lose");
                     System.out.println("");                                         
                  }
                  else 
                  {
                     p.addLose();
                     System.out.println("AI "+(i+1)+": "+p.getName());
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

