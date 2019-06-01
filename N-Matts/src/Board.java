import java.util.Scanner;

public class Board {

    public String input = "Init";

    public void readInput() {
        input = "";
        int sizeOfBoard;
        int linesOfTerritory;

        Scanner scan = new Scanner(System.in);

        String line;

        do {
            input = scan.nextLine();

            Scanner innerScan = new Scanner(input);
            sizeOfBoard = Integer.parseInt(innerScan.next());
            linesOfTerritory = Integer.parseInt(innerScan.next());


        } while (!input.equals("0"));
    }

    public void displayOutput() {

    }
}
