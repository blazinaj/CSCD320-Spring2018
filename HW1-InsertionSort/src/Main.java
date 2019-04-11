import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		List<String> hand = new ArrayList<String>();
		Scanner scan = new Scanner(System.in);
		
		String card = "";
		
		do{
			for (int i = 0; i < 5; i++) {
            card = scan.next();
            if (card == "00"){
               System.exit(0);
            }
				hand.add(card);
			}
			
			System.out.println(InsertionSort(hand));
      
		}while(card != "00");
      
		scan.close();
	}
	
	public static String InsertionSort(List<String> hand){
		String res = "";
		
		for(int i = 1; i < 5; i++) {
			String key = hand.get(i);
			int j = i - 1;
			
			while ( j >= 0 && compareCards(key, hand.get(j)) < 0) {
				hand.set(j + 1, hand.get(j));
				j--;
			}
			hand.set(j + 1, key);
		}
		
		for (int i = 0; i < 5; i++) {
			res += hand.get(i);
			if (i != 4) {
				res += " ";
			}
		}
		
		
		return res;
	}
	
	
	public static int compareCards(String firstCard, String secondCard) {
		char firstCardValue = firstCard.charAt(0);
		char firstCardSuit = firstCard.charAt(1);
		char secondCardValue = secondCard.charAt(0);
		char secondCardSuit = secondCard.charAt(1);
		
		Map<Character, Integer> suit = new HashMap<Character, Integer>();
		suit.put('S', 1);
		suit.put('H', 2);
		suit.put('C', 3);
		suit.put('D', 4);
		
		Map<Character, Integer> faceCard = new HashMap<Character, Integer>();
      faceCard.put('A', 1);
      faceCard.put('2', 2);
      faceCard.put('3', 3);
      faceCard.put('4', 4);
      faceCard.put('5', 5);
      faceCard.put('6', 6);
      faceCard.put('7', 7);
      faceCard.put('8', 8);
      faceCard.put('9', 9);
		faceCard.put('T', 10);
		faceCard.put('J', 11);
		faceCard.put('Q', 12);
		faceCard.put('K', 13);
		
		// If Suits are the same, compare values
		if (firstCardSuit == secondCardSuit) {
			if (firstCardValue == secondCardValue) {
				return 0;
			}
			else if (faceCard.get(firstCardValue) < faceCard.get(secondCardValue)) {
				return -1;
			}
			else {
				return 1;
			}
		}
		
		// Compare Suits
		if (suit.get(firstCardSuit) < suit.get(secondCardSuit)) {
			return -1;
		}
		else {
			return 1;
		}
	}
}
