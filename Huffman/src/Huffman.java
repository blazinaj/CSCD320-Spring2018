import java.net.Inet4Address;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

class Node {
    int data;
    char c;

    Node left;
    Node right;
}

class NodeComparator implements Comparator<Node>{
    public int compare(Node a, Node b){
        return a.data - b.data;
    }
}

public class Huffman {
    public String alphabet; //integer position and char letter, separated by spaces
    public String encoded; //String representing the encoded data as it would appear in binary

    /* EXAMPLE
    Huffman huf = new Huffman("TEST"); //results in (for example):
    huf.alphabet => "2 T 6 E 7 S"
    huf.encoded => "010110"
     */
    public Huffman(String input) { //Constructor takes the input and populates alphabet and encoded

        HashMap<Character, Integer> alphabetDictionary = new HashMap<Character, Integer>();

        for (char c = 'A'; c <= 'Z'; c++){
            alphabetDictionary.put(c, 0);
        }

        for (int i = 0; i < input.length(); i++){
            alphabetDictionary.replace(input.charAt(i).toUpper(), alphabetDictionary.get(input.charAt(i).toUpper()) + 1);
        }

        System.out.println(alphabetDictionary);
    }
}

