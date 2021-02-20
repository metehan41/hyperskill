package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        char[][] grid = gridInput();
        gridOutput(grid);
        boolean whoWin = whoWin(grid);
        // System.out.println(whoWin);
        boolean flag2 = true;
        while (whoWin) {
            moveX(grid, flag2);
            gridOutput(grid);
            boolean whoWinX = whoWin(grid);
            if ( whoWinX == false) {
                break;
            }
            moveO(grid, flag2);
            gridOutput(grid);
            whoWin = whoWin(grid);
        }
    }

    public static char[][] gridInput() {
        char[][] grid = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = '_';
            }

        }
        return grid;
    }

    public static void gridOutput(char[][] grid) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] != '_') {
                    System.out.print(grid[i][j] + " ");
                } else if (grid[i][j] == '_') {
                    System.out.print("  ");
                }
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static boolean whoWin(char[][] grid) {
        int counterX = 0;
        int counterO = 0;

        int totalCounterX = 0;
        int totalCounterO = 0;

        int pointX = 0;
        int pointO = 0;

        boolean isGameFinish = true;
        boolean isPossibleChar = true;
        boolean isPossibleMove = true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == 'X') {
                    counterX += 1;
                    totalCounterX++;
                } else if (grid[i][j] == 'O') {
                    counterO += 1;
                    totalCounterO++;
                } else if (grid[i][j] == '_') {
                    isGameFinish = false;
                } else {
                    isPossibleChar = false;
                }
            }
            if (counterX == 3) {
                pointX += 1;
            }
            if (counterO == 3) {
                pointO += 1;
            }
            counterX = 0;
            counterO = 0;
        }

        int[] whoWinDiagonal = whoWinDiagonal(grid);
        int[] whoWinColumn = whoWinColumn(grid);

        pointX = pointX + whoWinColumn[0] + whoWinDiagonal[0];
        pointO = pointO + whoWinColumn[1] + whoWinDiagonal[1];

        if (Math.abs(totalCounterX - totalCounterO) > 1) {
            isPossibleMove = false;
        }

        if (pointX > pointO && isPossibleChar && isPossibleMove) {
            System.out.println("X wins");
            return false;
        } else if (pointO > pointX && isPossibleChar && isPossibleMove) {
            System.out.println("O wins");
            return false;
        } else if (pointX == 0 && pointO == 0 && isGameFinish && isPossibleMove) {
            System.out.println("Draw");
            return false;
        } else if (isPossibleChar && !isGameFinish && isPossibleMove) {
            return true;
        } else if (isPossibleChar) {
            System.out.println("Impossible");
            return false;
        } else if (!isPossibleMove) {
            System.out.println("Impossible");
            return false;
        } else {
            System.out.println("Impossible");
            return false;
        }
    }

    public static int[] whoWinDiagonal(char[][] grid) {
        int counterX1 = 0;
        int counterO1 = 0;

        int counterX2 = 0;
        int counterO2 = 0;

        int[] point = new int[2];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j && grid[i][j] == 'X') {
                    counterX1++;
                } else if (i == j && grid[i][j] == 'O') {
                    counterO1++;
                }
                if (i + j == 2 && grid[i][j] == 'X') {
                    counterX2++;
                } else if (i + j == 2 && grid[i][j] == 'O') {
                    counterO2++;
                }
            }
        }
        if (counterX1 == 3) {
            point[0] += 1;
        }
        if (counterO1 == 3) {
            point[1] += 1;
        }
        if (counterX2 == 3) {
            point[0] += 1;
        }
        if (counterO2 == 3) {
            point[1] += 1;
        }
        return point;

    }

    public static int[] whoWinColumn(char[][] grid) {
        int counterX = 0;
        int counterO = 0;

        int[] point = new int[2];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[j][i] == 'X') {
                    counterX += 1;
                }
                if (grid[j][i] == 'O') {
                    counterO += 1;
                }
            }
            if (counterX == 3) {
                point[0] += 1;
            }
            if (counterO == 3) {
                point[1] += 1;
            }
            counterX = 0;
            counterO = 0;
        }
        return point;
    }

    public static void moveX(char[][] grid, boolean flag) {
        while (flag) {
            System.out.print("Enter the coordinates:");
            String[] move = scanner.nextLine().strip().split(" ");
            try {
                int moveRow = Integer.parseInt(move[0]);
                int moveColumn = Integer.parseInt(move[1]);
                if (1 <= moveRow && moveRow <= 3 && 1 <= moveColumn && moveColumn <= 3 && grid[moveRow - 1][moveColumn - 1] == '_') {
                    grid[moveRow - 1][moveColumn - 1] = 'X';
                    flag = false;
                } else if (1 <= moveRow && moveRow <= 3 && 1 <= moveColumn && moveColumn <= 3 && grid[moveRow - 1][moveColumn - 1] != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                    flag = true;
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                    flag = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                flag = true;
            }
        }
    }
    public static void moveO(char[][] grid, boolean flag) {
        while (flag) {
            System.out.print("Enter the coordinates:");
            String[] move = scanner.nextLine().strip().split(" ");
            try {
                int moveRow = Integer.parseInt(move[0]);
                int moveColumn = Integer.parseInt(move[1]);
                if (1 <= moveRow && moveRow <= 3 && 1 <= moveColumn && moveColumn <= 3 && grid[moveRow - 1][moveColumn - 1] == '_') {
                    grid[moveRow - 1][moveColumn - 1] = 'O';
                    flag = false;
                } else if (1 <= moveRow && moveRow <= 3 && 1 <= moveColumn && moveColumn <= 3 && grid[moveRow - 1][moveColumn - 1] != '_') {
                    System.out.println("This cell is occupied! Choose another one!");
                    flag = true;
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                    flag = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                flag = true;
            }
        }
    }
}