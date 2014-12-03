package War;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
public class GameEngineGUI extends Deck
{
	public ArrayList<Card> deck1;
	public ArrayList<Card> deck2;
	
	public ArrayList<Card> warDeck1;
	public ArrayList<Card> warDeck2;
	public String image1;
	public String image2;
	
	/**
	 * GameEngineGUI is a constructor.
	 * makes a full deck, shuffles that deck, and then deals the cards to two smaller decks
	 * to compete against eachother
	 */
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
	
	/**
	 * flip gets string values for the top cards allowing for the GUI to show that card when played
	 */
	public void flip()
	{
		image1 = "CardPics//"+ deck1.get(0).toString() +".jpg";
		image2 = "CardPics//"+ deck2.get(0).toString() +".jpg";
	}
			//compare cards

	//war
	/**
	 * war is the war component in the game
	 */
	public void war()    /////////write code for when deck has war but not enough cards
	{
		int hand = compare4thCard(deck1, deck2);
		warDeck1.add(deck1.remove(0)); //remove top card that has already been played and add it to the temp array
		//remove 3 cards that are used in the war
		warDeck1.add(deck1.remove(0));
		warDeck1.add(deck1.remove(0));
		warDeck1.add(deck1.remove(0));

		warDeck2.add(deck2.remove(0));//remove top card that has already been played and add it to the temp array
		//remove 3 cards that are used in the war
		warDeck2.add(deck2.remove(0)); 
		warDeck2.add(deck2.remove(0));
		warDeck2.add(deck2.remove(0));

		while(hand != 1 && hand != 2)
		{
			compare4thCard(deck1, deck2);
		}
		
		//deck1 wins hand
		if(hand == 1)
		{
			//return deck 1's war cards to the deck
			for(Card i: warDeck1)
			{
				deck1.add(deck1.size(), i);
			}
			//add deck 2's war cards to deck 1

			for(Card i : warDeck2)
			{
				//System.out.println(i);
				deck1.add(deck1.size(), i);
			}
			addToDeck(deck1, deck2);//remove the first war card
			//addToDeck(deck1, deck2);//remove the second war card
		}
		//deck2 wins hand1
		
		else 
		{
			//return deck 2's war cards to the deck
			for(Card i: warDeck2)
			{
				deck2.add(deck2.size(), i);
			}
			//add deck 1's war cards to deck 2
			
			for(Card i : warDeck1)
			{
				//System.out.println(i);
				deck2.add(deck2.size(), i);
			}
			addToDeck(deck2, deck1);
			//addToDeck(deck2, deck1);
		}

	}//ends war
	/**
	 * compareFinalHand compares the remaining cards in each hand to determine a winner
	 */
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
	 * @param deck1 deck1
	 * @param deck2	deck2
	 * @return ints values, if 1, deck1 is greater, if 2, deck 2 is greater, else, war
	 */
	public int compare(ArrayList<Card> deck1, ArrayList<Card> deck2)
	{
		if (deck1.get(0).getRank() > deck2.get(0).getRank())
		{
			//System.out.println("Deck 1 won that hand.");
			return 1;
		}
		else if(deck1.get(0).getRank() < deck2.get(0).getRank())
		{
			//System.out.println("Deck 2 won that hand.");
			return 2;
		}
		else
			return 3;
	}
	
	/**
	 * addToDeck adds the cards in the play field to the bottom of the winning deck 
	 * @param winDeck the deck that winds the deal
	 * @param looseDeck the deck that looses the deal
	 * @return the new deck to the winning deck
	 */
	public ArrayList<Card> addToDeck(ArrayList<Card> winDeck, ArrayList<Card> looseDeck)
	{
		winDeck.add(winDeck.size()-1, winDeck.remove(0));
		winDeck.add(winDeck.size(), looseDeck.get(0));
		return winDeck;
	}
	
	/**
	 * removes the top card from a deck
	 * @param deck loosing deck
	 * @return the new deck 
	 */
	public ArrayList<Card> removeFromDeck(ArrayList<Card> deck)
	{
		deck.remove(0);
		return deck;
	}
	
	
	/**
	 * compare4thCard compares the 4th card in the deck for a war, allowing for another war if they are the same
	 * else moving on
	 * @param deck1 deck1
	 * @param deck2 deck2
	 * @return int value, if 1, deck one is greater, if 2, deck2 is greater
	 */
	public int compare4thCard(ArrayList<Card> deck1, ArrayList<Card> deck2)
	{
		if (deck1.get(4).getRank() > deck2.get(4).getRank())
		{
			//System.out.println("Deck 1 won that hand.");
			return 1;
		}
		else
		{
			//System.out.println("Deck 2 won that hand.");
			return 2;
		}
	}
	

}
