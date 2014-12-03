package War;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
public class GameEngineGUI extends Deck
{
	private ArrayList<Card> deck1;
	private ArrayList<Card> deck2;
	
	private ArrayList<Card> warDeck1;
	private ArrayList<Card> warDeck2;
	public String image1;
	public String image2;
	
	public GameEngineGUI()
	{
		//create deck
		Deck mainDeck = new Deck();
		
		//shuffle deck
		mainDeck.shuffle();
		
		//create two decks to be played
		
//		Deck deck1 = new deck;
//		Deck deck2;
		deck1 = new ArrayList<Card>();
		deck2 = new ArrayList<Card>();
		
		warDeck1 = new ArrayList<Card>();
		warDeck2 = new ArrayList<Card>();
		
		//deal cards into deck1 and deck2

		for(int i = 1; i <= CARDS_IN_DECK; i++)
		{
			if((i % 2) == 0)
			{
				deck1.add(mainDeck.dealCard());
			}
			else
			{
				deck2.add(mainDeck.dealCard());
			}
		}
	}
		//testing for dealing card from deck to deck1 and deck2.
//		for(Card i : deck1)
//		{
//			System.out.println(i);
//		}
//		
//		System.out.println("_______________");
//		for(Card i : deck2)
//		{
//			System.out.println(i);
//		}
//		//System.out.println(deck1.get(0));
		
	public void flip()
	{
		image1 = "CardPics//"+ deck1.get(0).toString() +".jpg";
		image2 = "CardPics//"+ deck2.get(0).toString() +".jpg";
	}
			//compare cards

	public void compare()
	{
			if(deck1.get(0).getRank() != deck2.get(0).getRank())
			{
				boolean handWinner = compare(deck1, deck2);
				
				//if deck 1 is greater
				if (handWinner == true)
				{
					deck1.add(deck1.size()-1, deck1.remove(0));
					deck1.add(deck1.size(), deck2.remove(0));
					//deck2 = removeFromDeck(deck2);
				}
				//if deck2 is greater
				else
				{
					deck2.add(deck2.size()-1, deck2.remove(0));
					deck2.add(deck2.size(), deck1.remove(0));
					//deck1 = removeFromDeck(deck1);
				}
			}
			//war
			else    /////////write code for when deck has war but not enough cards
			{
				boolean war = true; //checks to make sure we get out of war loop
				while (war == true)
				{
					//System.out.println("WAR!!");
					//System.out.println("----------------------------------");
					//System.out.println("Deck 1 places 3 cards");
					
					
					warDeck1.add(deck1.remove(0)); //remove top card that has already been played and add it to the temp array
					//remove 3 cards that are used in the war
					warDeck1.add(deck1.remove(0));
					warDeck1.add(deck1.remove(0));
					warDeck1.add(deck1.remove(0));
					
					//System.out.println("Deck 2 places 3 cards");
					
					
					warDeck2.add(deck2.remove(0));//remove top card that has already been played and add it to the temp array
					//remove 3 cards that are used in the war
					warDeck2.add(deck2.remove(0)); 
					warDeck2.add(deck2.remove(0));
					warDeck2.add(deck2.remove(0));
					
					//display new top cards
					//System.out.println("Deck 1 now pulled: " + deck1.get(0));
					//System.out.println("Deck 2 now pulled: " + deck2.get(0));
					
					
					//compare new cards
					if(deck1.get(0) == deck2.get(0)) //if another war
					{
						war = true;
					}
					else //if one is greater than the other
					{
						boolean warWinner = compare(deck1, deck2);
						//add cards to war decks
						warDeck1.add(warDeck1.size(), deck1.remove(0));
						warDeck2.add(warDeck2.size(), deck2.remove(0));
						
						//deck1 wins hand
						if(warWinner == true)
						{
							
							//return deck 1's war cards to the deck
							for(Card i: warDeck1)
							{
								deck1.add(deck1.size(), i);
							}
							//add deck 2's war cards to deck 1
							//System.out.println("Deck 1 got: ");
							//System.out.println("----------------------------------");
							for(Card i : warDeck2)
							{
								//System.out.println(i);
								deck1.add(deck1.size(), i);
							}
						}
						//deck2 wins hand1
						
						else if(warWinner == false) 
						{
							//return deck 2's war cards to the deck
							for(Card i: warDeck2)
							{
								deck2.add(deck2.size(), i);
							}
							//add deck 1's war cards to deck 2
							//System.out.println("Deck 2 got: ");
							//System.out.println("----------------------------------");
							for(Card i : warDeck1)
							{
								//System.out.println(i);
								deck2.add(deck2.size(), i);
							}
						}
						war = false;
					}
					
				}
//				
			}
			flip();
	}//ends compare loop
	
	public void compareFinalHand()
	{
		if(deck1.size() > deck2.size())
		{
			//System.out.print("Deck 1 Wins With: " + deck1.size() + " Cards!");
		}
		else if(deck1.size() < deck2.size())
		{
			//System.out.print("Deck 2 Wins With:" + deck2.size() + " Cards!");
		}
		else
		{
			//System.out.print("It's a Tie!");
		}
	}
	
	
	/**
	 * compares the top cards of each deck to see if there is a winner
	 * @param deck1
	 * @param deck2
	 * @return
	 */
	private static boolean compare(ArrayList<Card> deck1, ArrayList<Card> deck2)
	{
		if (deck1.get(0).getRank() > deck2.get(0).getRank())
		{
			//System.out.println("Deck 1 won that hand.");
			return true;
		}
		else
		{
			//System.out.println("Deck 2 won that hand.");
			return false;
		}
	}
	
	private static ArrayList<Card> addToDeck(ArrayList<Card> winDeck, ArrayList<Card> looseDeck)
	{
		winDeck.add(winDeck.size(), looseDeck.get(0));
		return winDeck;
	}
	
	private static ArrayList<Card> removeFromDeck(ArrayList<Card> deck)
	{
		deck.remove(0);
		return deck;
	}
	

}
