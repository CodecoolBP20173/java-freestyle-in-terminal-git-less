import java.util.Scanner;

public class Main {

    static void clearField() {

    }

    static String[][] createFrontTable() {
        String[][] frontTable = new String[6][7];
        for (int i = 0; i < frontTable.length; i++) {
            for (int j = 0; j < frontTable[0].length; j++) {
                frontTable[i][j] = "::";
            }
        }

        return frontTable;
    }

    static String[][] createBackTable() {
        String[][] backTable = new String[6][7];

        return backTable;
    }

    static void printTable(String[][] table) {
        System.out.println("  1    2    3    4    5    6    7");
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                if (j == 0) {
                    System.out.print("| ");
                }
                System.out.print(table[i][j] + " | ");
            }
            System.out.print("\n\n");
        }
    }

    static int displayCircleForPlayerOne(String[][] frontTable, String[][] backTable, int step) {
        System.out.println("Red Player's turn!");
        Scanner reader = new Scanner(System.in);
        int columnToDrop;
        do {
            System.out.println("Choose a column (1-7):");
            while (!reader.hasNextInt()) {
                System.out.println("That's not a number!");
                System.out.println("Choose a column (1-7):");
                reader.next();
            }
            columnToDrop = reader.nextInt();
        } while (columnToDrop <= 0 || columnToDrop >= 8);
        --columnToDrop;
        for (int i = 5; i >= 0; i--) {
            if (backTable[i][columnToDrop] == null) {
                backTable[i][columnToDrop] = "R";
                frontTable[i][columnToDrop] = "R ";
                step++;
                break;
            }
            if (i == 0 && backTable[i][columnToDrop] != null) {
                System.out.println("This column is full choose another one!");
            }
        }
        return step;
    }

    static int displayCircleForPlayerTwo(String[][] frontTable, String[][] backTable, int step) {
        System.out.println("Blue Player's turn!");
        Scanner reader = new Scanner(System.in);
        int columnToDrop;
        do {
            System.out.println("Choose a column (1-7):");
            while (!reader.hasNextInt()) {
                System.out.println("That's not a number!");
                System.out.println("Choose a column (1-7):");
                reader.next();
            }
            columnToDrop = reader.nextInt();
        } while (columnToDrop <= 0 || columnToDrop >= 8);
        --columnToDrop;
        for (int i = 5; i >= 0; i--) {
            if (backTable[i][columnToDrop] == null) {
                backTable[i][columnToDrop] = "B";
                frontTable[i][columnToDrop] = "B ";
                step++;
                break;
            }
            if (i == 0 && backTable[i][columnToDrop] != null) {
                System.out.println("This column is full choose another one!");
            }
        }
        return step;
    }

    static String winCheck(String[][] backTable) {
        //Horizontal Check
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if ((backTable[i][j] != null) && (backTable[i][j + 1] != null) && (backTable[i][j + 2] != null)
                        && (backTable[i][j + 3] != null) && (backTable[i][j] == backTable[i][j + 1])
                        && (backTable[i][j + 1] == backTable[i][j + 2])
                        && (backTable[i][j + 2] == backTable[i][j + 3])) {
                    return backTable[i][j];
                }
            }
        }
        //Vertical Check
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 7; j++) {
                if ((backTable[i][j] != null) && (backTable[i + 1][j] != null) && (backTable[i + 2][j] != null)
                        && (backTable[i + 3][j] != null) && (backTable[i][j] == backTable[i + 1][j])
                        && (backTable[i + 1][j] == backTable[i + 2][j])
                        && (backTable[i + 2][j] == backTable[i + 3][j])) {
                    return backTable[i][j];
                }
            }
        }
        //Diagonal Check (Left-Up to Right-Down)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if ((backTable[i][j] != null) && (backTable[i + 1][j + 1] != null) && (backTable[i + 2][j + 2] != null)
                        && (backTable[i + 3][j + 3] != null) && (backTable[i][j] == backTable[i + 1][j + 1])
                        && (backTable[i + 1][j + 1] == backTable[i + 2][j + 2])
                        && (backTable[i + 2][j + 2] == backTable[i + 3][j + 3])) {
                    return backTable[i][j];
                }
            }
        }
        //Diagonal Check (Right-Up to Left-Down)
        for (int i = 0; i < 3; i++) {
            for (int j = 3; j < 7; j++) {
                if ((backTable[i][j] != null) && (backTable[i + 1][j - 1] != null) && (backTable[i + 2][j - 2] != null)
                        && (backTable[i + 3][j - 3] != null) && (backTable[i][j] == backTable[i + 1][j - 1])
                        && (backTable[i + 1][j - 1] == backTable[i + 2][j - 2])
                        && (backTable[i + 2][j - 2] == backTable[i + 3][j - 3])) {
                    return backTable[i][j];
                }
            }
        }
        return null;
    }

    public static int menu() {
        System.out.println("Welcome to Connect Four!");
        System.out.println("1. New Game \n2. Score Board");
        int menuPoint = 0;
        Scanner reader = new Scanner(System.in);
        do {
            System.out.println("Choose a number:");
            while (!reader.hasNextInt()) {
                System.out.println("That's not a number!");
                System.out.println("Choose a number:");
                reader.next();
            }
            menuPoint = reader.nextInt();
        } while (menuPoint <= 0 || menuPoint >= 3);
        return menuPoint;
    }

    public static String restart() {
        System.out.println("Do you want to play again? Y/N");
        Scanner reader = new Scanner(System.in);
        String restartOption;
        do {
            System.out.println("Do you want to play again? Y/N");
            while (!reader.hasNext()) {
                System.out.println("Wrong Input!");
                System.out.println("Do you want to play again? Y/N");
                reader.next();
            }
            restartOption = reader.next();
        } while (restartOption != "Y" || restartOption != "y" || restartOption != "N" || restartOption != "n");
        return restartOption;
    }

    public static void main(String[] args) {
        while (true) {
            String[][] frontTable = createFrontTable();
            String[][] backTable = createBackTable();
            int step = 0;
            int lastTurn = 42;
            int menuPoint = 0;
            String winner = null;

            menuPoint = menu();
            if (menuPoint == 1) {
                printTable(frontTable);
                while (true) {
                    if (step % 2 == 0 && winner == null) {
                        step = displayCircleForPlayerOne(frontTable, backTable, step);
                        printTable(frontTable);
                        winner = winCheck(backTable);
                    }
                    if (step % 2 == 1 && winner == null) {
                        step = displayCircleForPlayerTwo(frontTable, backTable, step);
                        printTable(frontTable);
                        winner = winCheck(backTable);
                    }
                    if (step == lastTurn && winner == null) {
                        System.out.println("Tie!");
                        break;
                    }
                    if (winner != null) {
                        if (winner == "R") {
                            System.out.println("Red won!");
                        }
                        if (winner == "B") {
                            System.out.println("Blue won!");
                        }
                        break;
                    }
                }
            }
            if (menuPoint == 2) {
                System.out.println("SCOREBOARD");
            }
        }
    }
}