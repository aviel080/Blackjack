package bj;

import java.util.*;

public class Deck {
	private ArrayList<Card> deckCards = new ArrayList<Card>();
	Deck()
	{
		buildDeck();
	}
	private void buildDeck()
	{
		for (int j=0;j<4;j++)
		{
			for (int i=1;i<=13;i++)
			{
				deckCards.add(new Card(i,"CLUBS"));
			}
		}
		for (int j=0;j<4;j++)
		{
			for (int i=1;i<=13;i++)
			{
				deckCards.add(new Card(i,"DIAMONDS"));
			}
		}
		for (int j=0;j<4;j++)
		{
			for (int i=1;i<=13;i++)
			{
				deckCards.add(new Card(i,"HEARTS"));
			}
		}
		for (int j=0;j<4;j++)
		{
			for (int i=1;i<=13;i++)
			{
				deckCards.add(new Card(i,"SPADES"));
			}
		}
		Collections.shuffle(deckCards);
	}
	public Card pull()
	{
		if (deckCards.size() == 0)
		{
			buildDeck();
		}
		return deckCards.remove(0);
	}
	public int getDeckSize()
	{
		return deckCards.size();
	}
}
