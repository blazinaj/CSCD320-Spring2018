/*
 * Ben Brougher moutansos@eagles.ewu.edu
 * Alex Corak alex.corak@eagles.ewu.edu
 * Jacob Berger jberger8@eagles.ewu.edu
 * Jacob Blazina jblazina@eagles.ewu.edu
 * 
 * pgabriel39@ewu.edu
 */


import java.util.HashMap;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		String[] hand = new String[10];
		
		Scanner kb = new Scanner(System.in);
		
		for(int i = 0; i < hand.length; i++) {
			System.out.println("Enter Card #" + (i + 1));
			hand[i] = kb.nextLine();
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
        
        System.out.println("\n The sorted Cards:");
        for(String card : hand)
            System.out.println(card);
	}
	
	
	public static int compareCards(String card1, String card2) {
		HashMap<Character, Integer> suits = new HashMap<Character, Integer>();
		suits.put('S', 1);//edited:9.23/jb
		suits.put('H', 2);
		suits.put('C', 3);
		suits.put('D', 4);
		
		HashMap<Character, Integer> number = new HashMap<Character, Integer>();
		number.put('A', 1);
		for(int i = 2; i < 10; i++)
			number.put((char)(i + '0'), i);
		number.put('T', 10);
		number.put('J', 11);
		number.put('Q', 12);
		number.put('K', 13);
		
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
