public class Main {
    public static void main(String[] args){
        Huffman huf = new Huffman("TEST");

        System.out.println(huf.encoded);
        System.out.println(huf.alphabet);
    }
}