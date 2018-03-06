import java.util.Scanner;

public class Main {

    static void clearField () {

    }

    static void printField() {
        String[][] field = new String [7][6];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                System.out.print(field[i][j] + "  ");
            }
            System.out.print("\n");
        }
    }

    static int chooseColumn() {
        Scanner reader = new Scanner(System.in);
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
        printField();
    }
}