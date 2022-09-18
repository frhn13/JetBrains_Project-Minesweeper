import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();

        String[][] minesweeperGrid = new String[12][12];
        String[][] playerGrid = new String[12][12];
        int numberOfMines;
        int minesPlaced = 0, mineX, mineY;
        int xCoordinate, yCoordinate, cellsMarked = 0, minesMarked = 0;
        System.out.println("How many mines do you want on the field?");
        numberOfMines = input.nextInt();

        for (int x = 0; x < minesweeperGrid.length; x++) {
            if (x == 0) {
                for (int y = 0; y < 12; y++) {
                    if (y == 0) {
                        playerGrid[0][y] = " ";
                        minesweeperGrid[0][y] = " ";
                    }
                    else if (y == 1 || y == 11) {
                        playerGrid[0][y] = "|";
                        minesweeperGrid[0][y] = "|";
                    }
                    else {
                        playerGrid[0][y] = String.valueOf(y - 1);
                        minesweeperGrid[0][y] = String.valueOf(y - 1);
                    }
                }
            } else if (x == 1) {
                for (int y = 0; y < minesweeperGrid.length; y++) {
                    if (y == 1 || y == 11) {
                        playerGrid[1][y] = "|";
                        minesweeperGrid[1][y] = "|";
                    }
                    else {
                        playerGrid[1][y] = "-";
                        minesweeperGrid[1][y] = "-";
                    }
                }
            } else if (x < 11) {
                for (int y = 0; y < minesweeperGrid.length; y++) {
                    if (y == 0) {
                        playerGrid[x][0] = String.valueOf(x - 1);
                        minesweeperGrid[x][0] = String.valueOf(x - 1);
                    }
                    else if (y == 1 || y == 11) {
                        playerGrid[x][y] = "|";
                        minesweeperGrid[x][y] = "|";
                    }
                    else {
                        playerGrid[x][y] = ".";
                        minesweeperGrid[x][y] = ".";
                    }
                }
            } else {
                for (int y = 0; y < minesweeperGrid.length; y++) {
                    if (y == 1 || y == 11) {
                        playerGrid[x][y] = "|";
                        minesweeperGrid[x][y] = "|";
                    }
                    else {
                        playerGrid[x][y] = "-";
                        minesweeperGrid[x][y] = "-";
                    }
                }
            }
        }
        while (minesPlaced < numberOfMines) {
            mineX = random.nextInt(12);
            mineY = random.nextInt(12);
            if (minesweeperGrid[mineX][mineY].equals(".")) {
                minesweeperGrid[mineX][mineY] = "X";
                minesPlaced++;
            }
        }

        for (int x = 0; x < minesweeperGrid.length; x++) {
            for (int y = 0; y < minesweeperGrid.length; y++) {
                int minesAround = 0;
                if (minesweeperGrid[x][y].equals(".")) {
                    for (int checkX = -1; checkX < 2; checkX++) {
                        for (int checkY = -1; checkY < 2; checkY++) {
                            try {
                                if (minesweeperGrid[x + checkX][y + checkY].equals("X")) {
                                    minesAround++;
                                }
                            } catch (IndexOutOfBoundsException ignored) {
                            }
                        }
                    }
                    if (minesAround > 0) {
                        minesweeperGrid[x][y] = String.valueOf(minesAround);
                        playerGrid[x][y] = String.valueOf(minesAround);
                    }
                }
            }
        }

        //displayGrid(minesweeperGrid);
        //System.out.println();
        displayGrid(playerGrid);

        while(minesMarked<numberOfMines || cellsMarked>0) {
            System.out.println("Set/delete mines marks (x and y coordinates):");
            xCoordinate = input.nextInt() + 1;
            yCoordinate = input.nextInt() + 1;
            if (playerGrid[xCoordinate][yCoordinate].equals(".")) {
                playerGrid[xCoordinate][yCoordinate] = "*";
                if (minesweeperGrid[xCoordinate][yCoordinate].equals("X"))
                    minesMarked++;
                else
                    cellsMarked++;
                displayGrid(playerGrid);
            }
            else if (playerGrid[xCoordinate][yCoordinate].equals("*")) {
                playerGrid[xCoordinate][yCoordinate] = ".";
                if (minesweeperGrid[xCoordinate][yCoordinate].equals("X"))
                    minesMarked--;
                else
                    cellsMarked--;
                displayGrid(playerGrid);
            }
            else {
                System.out.println("There is a number here!");
            }
            //displayGrid(minesweeperGrid);
            //System.out.printf("Mines marked: %d, Cells marked %d\n", minesMarked, cellsMarked);
            //displayGrid(playerGrid);
        }
        System.out.println("Congratulations! You found all the mines!");
    }
    public static void displayGrid(String[][] grid){
        for (String[] row : grid) {
            for (String column : row) {
                System.out.print(column);
            }
            System.out.println();
        }
    }
}
