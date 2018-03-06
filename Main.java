import java.util.Scanner;

public class Main {

    static void clearField () {

    }

    static String[][] createFrontTable() {
        String[][] frontTable = new String [6][7];
        for (int i = 0; i < frontTable.length; i++) {
            for (int j = 0; j < frontTable[0].length; j++) {
                frontTable[i][j] = "::";
            }
        }

        return frontTable;
    }

    static String[][] createBackTable() {
        String[][] backTable = new String [6][7];

        return backTable;
    }

    static void printTable(String[][] table){
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                if (j == 0){
                    System.out.print("| ");
                }
                System.out.print(table[i][j] + " | ");
            }
            System.out.print("\n\n");
        }
    }

    static int chooseColumn() {
        Scanner reader = new Scanner(System.in);
        reader.close();
        int move = 0;
        return move;
    }

    static void displayCircleForPlayerOne() {
    }

    static void displayCircleForPlayerTwo() {
    }

    static boolean winCheck() {
        boolean isTrue = false;
        return isTrue;
    }

    public static void main(String[] args) {
        String[][] frontTable = createFrontTable();
        String[][] backTable = createBackTable();
        printTable(frontTable);
        System.out.print("\n");
        printTable(backTable);
    }
}