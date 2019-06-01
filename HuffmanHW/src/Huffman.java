import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

class Node {
    int freq;
    char theChar;

    Node left;
    Node right;
}

class NodeComparator implements Comparator<Node>{
    public int compare(Node a, Node b){
        return a.freq - b.freq;
    }
}

public class Huffman {
    public String alphabet = ""; //integer position and char letter, separated by spaces
    public String encoded = ""; //String representing the encoded data as it would appear in binary

    /* EXAMPLE
    Huffman huf = new Huffman("TEST"); //results in (for example):
    huf.alphabet => "2 T 6 E 7 S"
    huf.encoded => "010110"
     */
    public void createCode(Node root, String s){
        if (root.left == null && root.right == null && Character.isLetter(root.theChar)){
            this.encoded += s;
            this.alphabet += root.freq + " " + root.theChar +  " ";
            return;
        }

        createCode(root.left, s + "0");
        createCode(root.right, s + "1");
    }

    public Huffman(String input) { //Constructor takes the input and populates alphabet and encoded

        HashMap<Character, Integer> alphabetDictionary = new HashMap<Character, Integer>();

        for (char c = 'A'; c <= 'Z'; c++){
            alphabetDictionary.put(c, 0);
        }

        for (int i = 0; i < input.length(); i++){
            alphabetDictionary.replace(input.toUpperCase().charAt(i), alphabetDictionary.get(input.toUpperCase().charAt(i)) + 1);
        }

        //System.out.println(alphabetDictionary);

        int numberOfChars = 0;

        for (int v : alphabetDictionary.values()){
            if (v > 0) numberOfChars++;
        }

        PriorityQueue<Node> queue = new PriorityQueue<Node>(numberOfChars, new NodeComparator());

        for (char c = 'A'; c <= 'Z'; c++) {
            if (alphabetDictionary.get(c) > 0) {
                Node node = new Node();
                node.theChar = c;
                node.freq = alphabetDictionary.get(c);
                queue.add(node);
            }
        }

        Node root = null;

        while (queue.size() > 1){
            Node first = queue.peek();
            queue.poll();

            Node second = queue.peek();
            queue.poll();

            Node combined = new Node();

            combined.freq = first.freq + second.freq;
            combined.theChar = '-';

            combined.left = first;
            combined.right = second;

            root = combined;

            queue.add(combined);
        }

        createCode(root, "");
    }


}



