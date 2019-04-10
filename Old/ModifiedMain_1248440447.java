/*
 * Ben Brougher moutansos@eagles.ewu.edu
 * Alex Corak alex.corak@eagles.ewu.edu
 * Jacob Berger jberger8@eagles.ewu.edu
 * Jacob Blazina jblazina@eagles.ewu.edu
 * 
 * pgabriel39@ewu.edu
 */


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ModifiedMain {
	
	public static void main(String[] args) {
		String[] hand = new String[10];
		
		Scanner kb = new Scanner(System.in);
		
		System.out.print("Enter Cards: ");
		for(int i = 0; i < hand.length; i++) {
			hand[i] = kb.next();
		}
		
		for(int i = 1; i < hand.length; i++) {

			String newValue = hand[i];
			int pos = i;
            while(pos > 0 && compareCards(newValue, hand[pos - 1]) < 0) {
				hand[pos] = hand[pos - 1];
				pos--;
			}

            hand[pos] = newValue;
        }
        
        System.out.print("\nThe sorted Cards: ");
        for(String card : hand)
            System.out.print(card+" ");
	}
	
	private static final Map<Character, Integer> suits;
	private static final Map<Character, Integer> number;
	static {
		Map<Character, Integer> asuits = new HashMap<Character, Integer>();
		asuits.put('S', 1);//edited:9.23/jb
		asuits.put('H', 2);
		asuits.put('C', 3);
		asuits.put('D', 4);
		suits = Collections.unmodifiableMap(asuits);
		
		Map<Character, Integer> anumber = new HashMap<Character, Integer>();
		anumber.put('A', 1);
		for(int i = 2; i < 10; i++)
			anumber.put((char)(i + '0'), i);
		anumber.put('T', 10);
		anumber.put('J', 11);
		anumber.put('Q', 12);
		anumber.put('K', 13);
		number = Collections.unmodifiableMap(anumber);
	}
	public static int compareCards(String card1, String card2) {
		char card1Suit = card1.charAt(1);
		char card1Value = card1.charAt(0);
		
		char card2Suit = card2.charAt(1);
		char card2Value = card2.charAt(0);
		
		if(card1Suit == card2Suit)
		{
			if(card1Value == card2Value)
				return 0;
			else if(number.get(card1Value) < number.get(card2Value)) {
				return -1;
			}
			else {
				return 1;
			}
		}
		else if(suits.get(card1Suit) < suits.get(card2Suit)) {
			return -1;
		}
		else {
			return 1;
		}
	}

}
