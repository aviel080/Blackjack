package model;

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
			addPerSign("CLUBS");
			addPerSign("DIAMONDS");
			addPerSign("HEARTS");
			addPerSign("SPADES");
		}
		Collections.shuffle(deckCards);
	}
	private void addPerSign(String sign)
	{
		for (int i=1;i<=13;i++)
		{
			if (i >= 2 && i<= 9)
				continue;
			deckCards.add(new Card(i,sign));
		}
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
