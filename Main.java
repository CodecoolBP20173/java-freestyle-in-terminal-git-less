import java.util.Scanner;
import com.codecool.termlib.Terminal;
import com.codecool.termlib.Color;
import com.codecool.termlib.Direction;
import com.codecool.termlib.Attribute;

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

    static int displayCircleForPlayerOne(String[][] frontTable, String[][] backTable, int step, String player) {
        System.out.println(player + "'s turn! (Red)");
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

    static int displayCircleForPlayerTwo(String[][] frontTable, String[][] backTable, int step, String player) {
        System.out.println(player + "'s turn! (Blue)");
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
        System.out.println("1. New Game \n2. Score Board \n3. Quit Game");
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
        } while (menuPoint <= 0 || menuPoint >= 4);
        return menuPoint;
    }

    public static String restart() {
        Scanner reader = new Scanner(System.in);
        String restartOption;
        do {
            System.out.println("Do you want to play again? Y/N");
            restartOption = reader.next();
        } while (!restartOption.equalsIgnoreCase("y") && !restartOption.equalsIgnoreCase("n"));
        return restartOption;
    }
  
    static void displayNumber() {
        String[][] numbersToPrint = {
            {"  1", " 11", "1 1", "  1", "  1", " 111"},
            {" 22", "2  2", "   2", "  2", " 2", "2222"},
            {" 33", "3  3", "  3", "  3", "3  3", " 33"},
            {"   4", "  44", " 4 4", "44444", "   4", "   4"},
            {"5555", "5", "555", "   5", "   5", "555"},
            {"   6", "  6", " 66", "6  6", "6  6", " 66"},
            {"7777", "   7", "  7", " 7", "7", "7"}
        };
        Terminal terminalControl = new Terminal();
        terminalControl.clearScreen();
        terminalControl.setColor(Color.RED);
        int X = 37;
        int Y = 15;
        for (int horizontal = 0; horizontal < numbersToPrint.length; horizontal++) {
            int nextNumberY = horizontal * 10;
            for (int vertical = 0; vertical < 6; vertical++) {
                terminalControl.moveTo(X + vertical, Y + nextNumberY);
                System.out.print(numbersToPrint[horizontal][vertical]);
            }
        }
    }

    static void displayElemet(int x, int y, Color color) {
        Terminal terminalControl = new Terminal();
        terminalControl.setBgColor(color);
        for (int j = 0; j < 3; j++) {
            terminalControl.moveTo(x + j, y);
            for (int i = 0; i < 5; i++) {
                terminalControl.setChar(' ');
            }
        }
    }

    static int[] frontendCoordinates(int x, int y) {
        int[][][] coordinates = {
            {{7, 15}, {7, 25}, {7, 35}, {7, 45}, {7, 55}, {7, 65}, {7, 75}},
            {{12, 15}, {12, 25}, {12, 35}, {12, 45}, {12, 55}, {12, 65}, {12, 75}},
            {{17, 15}, {17, 25}, {17, 35}, {17, 45}, {17, 55}, {17, 65}, {17, 75}},
            {{22, 15}, {22, 25}, {22, 35}, {22, 45}, {22, 55}, {22, 65}, {22, 75}},
            {{27, 15}, {27, 25}, {27, 35}, {27, 45}, {27, 55}, {27, 65}, {27, 75}},
            {{32, 15}, {32, 25}, {32, 35}, {32, 45}, {32, 55}, {32, 65}, {32, 75}}
        };
        return coordinates[x][y];
    }

    public static void main(String[] args) {
        while (true) {
            String[][] frontTable = createFrontTable();
            String[][] backTable = createBackTable();
            String redPlayer, bluePlayer;
            int step = 0;
            int lastTurn = 42;
            String restart = "";
            int menuPoint = 0;
            String winner = null;

            menuPoint = menu();
            if (menuPoint == 1) {
                Scanner reader = new Scanner(System.in);
                System.out.println("First Player enter your name:");
                redPlayer = reader.next();
                System.out.println("Second Player enter your name:");
                bluePlayer = reader.next();
                printTable(frontTable);
                while (true) {
                    if (step % 2 == 0 && winner == null) {
                        step = displayCircleForPlayerOne(frontTable, backTable, step, redPlayer);
                        printTable(frontTable);
                        winner = winCheck(backTable);
                    }
                    if (step % 2 == 1 && winner == null) {
                        step = displayCircleForPlayerTwo(frontTable, backTable, step, bluePlayer);
                        printTable(frontTable);
                        winner = winCheck(backTable);
                    }
                    if (step == lastTurn && winner == null) {
                        System.out.println("Tie!");
                        restart = restart();
                            if (restart.equalsIgnoreCase("y"))
                            {
                                break;
                            }
                            if (restart.equalsIgnoreCase("n"))
                            {
                                System.exit(0);
                            }
                    }
                    if (winner != null) {
                        if (winner == "R") {
                            System.out.println(redPlayer + " won!");
                            restart = restart();
                            if (restart.equalsIgnoreCase("y"))
                            {
                                break;
                            }
                            if (restart.equalsIgnoreCase("n"))
                            {
                                System.exit(0);
                            }
                        }
                        if (winner == "B") {
                            System.out.println(bluePlayer + " won!");
                            restart = restart();
                            if (restart.equalsIgnoreCase("y"))
                            {
                                break;
                            }
                            if (restart.equalsIgnoreCase("n"))
                            {
                                System.exit(0);
                            }
                        }
                        break;
                    }
                }
            }
            if (menuPoint == 2) {
                System.out.println("SCOREBOARD");
            }
            if (menuPoint == 3) {
                System.exit(0);
            }
        }
    }
}