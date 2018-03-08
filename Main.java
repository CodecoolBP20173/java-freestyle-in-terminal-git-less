import java.io.BufferedWriter;
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
        //for (int i = 0; i < backTable.length; i++) {
        //    for (int j = 0; j < backTable[i].length; j++) {
        //        backTable[i][j] = "not_null";
        //    }
        //}

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
        Terminal terminalControl = new Terminal();
        terminalControl.moveTo(20, 95);
        System.out.print(player + "'s turn! (Red)");
        Scanner reader = new Scanner(System.in);
        int columnToDrop;
        do {
            terminalControl.moveTo(22, 95);
            System.out.print("Choose a column (1-7): ");
            while (!reader.hasNextInt()) {
                System.out.print("That's not a number!");
                System.out.print("Choose a column (1-7): ");
                reader.next();
            }
            columnToDrop = reader.nextInt();
        } while (columnToDrop <= 0 || columnToDrop >= 8);
        --columnToDrop;
        for (int i = 5; i >= 0; i--) {
            if (backTable[i][columnToDrop] == null) {
                int coordinateY = (columnToDrop * 10) + 15;
                int columnCoordinate = columnToDrop -1;
                moveNewElement(Color.RED, coordinateY, columnToDrop, backTable);
                backTable[i][columnToDrop] = "R";
                frontTable[i][columnToDrop] = "R ";
                step++;
                break;
            }
            if (i == 0 && backTable[i][columnToDrop] != null) {
                System.out.print("This column is full, choose another one!");
            }
        }
        return step;
    }

    static void moveNewElement(Color color, int coordinateY, int columnCoordinate, String[][] backTable) {
        Terminal terminalControl = new Terminal();
        int nextEmptySlot = 5;
        for (int i = 0; i < backTable.length; i++) {
            if (backTable[i][columnCoordinate] != null && !backTable[i][columnCoordinate].isEmpty()) {
                nextEmptySlot = i - 1;
                break;
            } 
        }
        int[] xCoordinatesList = {7, 12, 17, 22, 27, 32};
        int xGoal = xCoordinatesList[nextEmptySlot];
        int x = 2;
        while (x <= xGoal-2) {
            displayElement(x, coordinateY, color);
            try{
                Thread.sleep(40);
            } catch (Exception e){}
            terminalControl.moveTo(x, coordinateY);
            System.out.print("\033[0;0m");
            for (int i = 0; i < 5; i++) {
                terminalControl.setChar(' ');
            }
            x += 1;
        }
    }

    static int displayCircleForPlayerTwo(String[][] frontTable, String[][] backTable, int step, String player) {
        Terminal terminalControl = new Terminal();
        terminalControl.moveTo(20, 95);
        System.out.print(player + "'s turn! (Blue)");
        Scanner reader = new Scanner(System.in);
        int columnToDrop;
        do {
            terminalControl.moveTo(22, 95);
            System.out.print("Choose a column (1-7): ");
            while (!reader.hasNextInt()) {
                System.out.print("That's not a number!");
                System.out.print("Choose a column (1-7): ");
                reader.next();
            }
            columnToDrop = reader.nextInt();
        } while (columnToDrop <= 0 || columnToDrop >= 8);
        --columnToDrop;
        for (int i = 5; i >= 0; i--) {
            if (backTable[i][columnToDrop] == null) {
                int coordinateY = (columnToDrop * 10) + 15;
                int columnCoordinate = columnToDrop -1;
                moveNewElement(Color.BLUE, coordinateY, columnToDrop, backTable);
                backTable[i][columnToDrop] = "B";
                frontTable[i][columnToDrop] = "B ";
                step++;
                break;
            }
            if (i == 0 && backTable[i][columnToDrop] != null) {
                System.out.print("This column is full, choose another one!");
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
        Terminal terminalControl = new Terminal();
        terminalControl.moveTo(20, 95);
        System.out.print("Welcome to Connect Four!");
        terminalControl.moveTo(21, 95);
        System.out.print("1. New Game");
        terminalControl.moveTo(22, 95);
        System.out.print("2. Score Board");
        terminalControl.moveTo(23, 95);
        System.out.print("3. Quit Game");
        terminalControl.moveTo(24, 95);

        int menuPoint = 0;
        Scanner reader = new Scanner(System.in);
        do {
            System.out.print("Choose a number: ");
            while (!reader.hasNextInt()) {
                System.out.print("That's not a number!");
                System.out.print("Choose a number: ");
                reader.next();
            }
            menuPoint = reader.nextInt();
        } while (menuPoint <= 0 || menuPoint >= 4);
        return menuPoint;
    }

    public static String restart() {
        Terminal terminalControl = new Terminal();
        Scanner reader = new Scanner(System.in);
        String restartOption;
        do {
            terminalControl.moveTo(30, 95);
            System.out.print("Do you want to play again? Y/N ");
            restartOption = reader.next();
        } while (!restartOption.equalsIgnoreCase("y") && !restartOption.equalsIgnoreCase("n"));
        return restartOption;
    }
  
    static void displayNumbersOfColumns() {
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
        terminalControl.setColor(Color.CYAN);
        int X = 37;
        int Y = 15;
        for (int horizontal = 0; horizontal < numbersToPrint.length; horizontal++) {
            int nextNumberY = horizontal * 10;
            for (int vertical = 0; vertical < 6; vertical++) {
                terminalControl.moveTo(X + vertical, Y + nextNumberY);
                System.out.print(numbersToPrint[horizontal][vertical]);
            }
        }
        System.out.print("\033[0;0m");
    }

    static void displayElement(int x, int y, Color color) {
        Terminal terminalControl = new Terminal();
        terminalControl.setBgColor(color);
        for (int j = 0; j < 3; j++) {
            terminalControl.moveTo(x + j, y);
            for (int i = 0; i < 5; i++) {
                terminalControl.setChar(' ');
            }
        }
        System.out.print("\033[0;0m");
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

    static void printEverything(String[][] backTable) {
        Terminal terminalControl = new Terminal();
        terminalControl.clearScreen();
        displayNumbersOfColumns();
        for (int i = 0; i < backTable.length; i++) {
            for (int j = 0; j < backTable[i].length; j++) {
                if (backTable[i][j] == "B") {
                    displayElement(frontendCoordinates(i, j)[0], frontendCoordinates(i, j)[1], Color.BLUE);
                } else if (backTable[i][j] == "R") {
                    displayElement(frontendCoordinates(i, j)[0], frontendCoordinates(i, j)[1], Color.RED);
                }
            }
        }
    }

    public static void main(String[] args) {
        while (true) {
            Terminal terminalControl = new Terminal();
            terminalControl.clearScreen();
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
                terminalControl.moveTo(30, 95);
                System.out.print("First Player enter your name: ");
                redPlayer = reader.next();
                terminalControl.moveTo(31, 95);
                System.out.print("Second Player enter your name: ");
                bluePlayer = reader.next();
                printEverything(backTable);
                while (true) {
                    if (step % 2 == 0 && winner == null) {
                        step = displayCircleForPlayerOne(frontTable, backTable, step, redPlayer);
                        printEverything(backTable);
                        winner = winCheck(backTable);
                    }
                    if (step % 2 == 1 && winner == null) {
                        step = displayCircleForPlayerTwo(frontTable, backTable, step, bluePlayer);
                        printEverything(backTable);
                        winner = winCheck(backTable);
                    }
                    if (step == lastTurn && winner == null) {
                        printEverything(backTable);
                        terminalControl.moveTo(20, 95);
                        System.out.print("Tie!");
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
                            printEverything(backTable);
                            terminalControl.moveTo(10, 95);
                            System.out.print(redPlayer + " won!");
                            restart = restart();
                            if (restart.equalsIgnoreCase("y"))
                            {
                                break;
                            }
                            if (restart.equalsIgnoreCase("n"))
                            {
                                terminalControl.clearScreen();
                                System.exit(0);
                            }
                        }
                        if (winner == "B") {
                            printEverything(backTable);
                            terminalControl.moveTo(10, 95);
                            System.out.print(bluePlayer + " won!");
                            restart = restart();
                            if (restart.equalsIgnoreCase("y"))
                            {
                                break;
                            }
                            if (restart.equalsIgnoreCase("n"))
                            {
                                terminalControl.clearScreen();
                                System.exit(0);
                            }
                        }
                        break;
                    }
                }
            }
            if (menuPoint == 2) {
                System.out.print("SCOREBOARD");
            }
            if (menuPoint == 3) {
                terminalControl.clearScreen();
                System.exit(0);
            }
        }
    }

    public static void sleep(int time){
        try{
            Thread.sleep(time);
        } catch (Exception e){}
    }
}
