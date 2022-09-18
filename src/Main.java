import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();

        String[][] minesweeperGrid = new String[9][9];
        int numberOfMines;
        int minesPlaced = 0, mineX, mineY;
        System.out.println("How many mines do you want on the field?");
        numberOfMines = input.nextInt();

        for (int x=0; x<minesweeperGrid.length; x++) {
            for (int y=0; y<minesweeperGrid.length; y++) {
                minesweeperGrid[x][y] = ".";
            }
        }
        while (minesPlaced < numberOfMines) {
            mineX = random.nextInt(9);
            mineY = random.nextInt(9);
            minesPlaced = minesweeperGrid[mineX][mineY].equals("X") ? minesPlaced : minesPlaced+1;
            minesweeperGrid[mineX][mineY] = "X";
        }

        for (int x=0; x<minesweeperGrid.length; x++) {
            for (int y=0; y<minesweeperGrid.length; y++) {
                int minesAround = 0;
                if (minesweeperGrid[x][y].equals(".")) {
                    for (int checkX = -1; checkX < 2; checkX++) {
                        for (int checkY=-1; checkY < 2; checkY++) {
                            try {
                                if (minesweeperGrid[x+checkX][y+checkY].equals("X")) {
                                    minesAround++;
                                }
                            } catch (IndexOutOfBoundsException ignored) {
                            }
                        }
                    }
                    if (minesAround>0)
                        minesweeperGrid[x][y] = String.valueOf(minesAround);
                }
            }
        }
        for (String[] row : minesweeperGrid) {
            for (String column : row) {
                System.out.print(column);
            }
            System.out.println();
        }
    }
}
