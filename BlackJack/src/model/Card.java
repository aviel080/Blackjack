package model;

public class Card {
	private int number;
	private String sign;
	private int value;
	public Card(int number, String sign)
	{
		this.number = number;
		this.sign = sign;
		value = calcValue();
	}
	private int calcValue()
	{
		if (number >= 10)
			return 10;
		return number;
	}
	public String getSign() {
		return sign;
	}
	public int getNumber() {
		return number;
	}
	public int getValue(){
		return value;
	}
}
