package War;

/*
 * James McCracken
 * CS 110
 * 21/01/14
 * 
 * Creates a card consisting of a suit and rank
 */
public class Card 
{
	public final static int SPADES = 1;
	public final static int CLUBS = 2;
	public final static int HEARTS = 3;
	public final static int DIAMONDS = 4;
	public final static int JACK = 11;
	public final static int QUEEN = 12;
	public final static int KING = 13;
	public final static int ACE = 14;
	private int rank;
	private int suit;
	
	/**
	 * Card default constructor initializes rank and suit
	 * @param s is the user input suit 
	 * @param r the user input rank
	 */
	public Card(int r, int s)
	{
		rank = r;
		suit = s;
	}
	
	/**
	 * getSuit returns the suit integer value
	 * @return suit
	 */
	public int getSuit()
	{
		return suit;
	}
	
	/**
	 * getRank returns the ran integer value
	 * @return rank
	 */
	public int getRank()
	{
		return rank;
	}

	//makes suit from num value to string
	/**
	 * stringSuit makes suit from int value to string
	 * @return name of suit
	 */
	public String stringSuit()
	{
		if (suit == 1)
		{
			return "Spades";
		}
		else if (suit == 2)
		{
			return "Clubs";
		}
		else if (suit == 3)
		{
			return "Hearts";
		}
		else
			return "Diamonds";
				
	}
	
	/**
	 * stringRank takes the rank int value and makes it into string for appropriate cards.
	 * @return name of card
	 */
	public String stringRank()
	{
		
		if(rank == 11)
		{
			return "Jack";
		}
		else if (rank == 12)
		{
			return "Queen";
		}
		else if (rank == 13)
		{
			return "King";
		}
		else if (rank == 14)
		{
			return "Ace";
		}
		else
			return "invalid";
	}
	
	/**
	 * toString overrides the default toString method
	 */
	public String toString()
	{
		String s;
		if(rank >= 11)
		{
			s = stringRank() + " of " + stringSuit();
		}
		else
		{
			s = getRank() + " of " + stringSuit();
		}

		return s;
	}
	/**
	 *  equals compares two cards to each other seeing if they are equal.
	 * @param other is another card 
	 * @return true or false
	 */
	public boolean equals(Card other)
	{
		if(rank == other.getRank() && suit == other.getSuit())
		{
			return true;
		}
		else
			return false;
	}
}
