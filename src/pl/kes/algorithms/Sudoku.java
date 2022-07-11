package pl.kes.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Sudoku {

  private final static char EMPTY = '.';
  private final static int SIZE_X = 9;
  private final static int SIZE_Y = 9;
  private final static Set<Character> ALLOWED_NUMBERS = new HashSet<>(Set.of('1', '2', '3', '4', '5', '6', '7', '8', '9'));
  private final static int[] IMPACTED_PIECES = new int[81];
  private static int track = 0;

  public static void main(String...args) {
    // char[][] board = new char[][] {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
    char[][] board = new char[][] {{'.','.','9','7','4','8','.','.','.'},{'7','.','.','.','.','.','.','.','.'},{'.','2','.','1','.','9','.','.','.'},{'.','.','7','.','.','.','2','4','.'},{'.','6','4','.','1','.','5','9','.'},{'.','9','8','.','.','.','3','.','.'},{'.','.','.','8','.','3','.','2','.'},{'.','.','.','.','.','.','.','.','6'},{'.','.','.','2','7','5','9','.','.'}};
    solveSudoku(board);
  }

  public static void solveSudoku(char[][] board) {
    // System.out.println("======================= NEW ITERATION =============================");
    int numberDefined = 0;
    Map<Integer, Character[]> emptyCoordinates = new HashMap<>();
    for (int i = 0; i < SIZE_X; i++) {
      for (int j = 0; j < SIZE_Y; j++) {
        //     // System.out.println("i = " + i + ", j = " + j);
        char piece = board[i][j];
        if (piece == EMPTY) {
          //       // System.out.println("Found empty piece: " + i + " " + j);
          Character[] allowed = allowedNumbers(board, i, j);
          // System.out.print(i + " " + j + " : allowed values ");
          for (Character allow: allowed) {
            // System.out.print(allow);
          }
          // System.out.println();

          emptyCoordinates.put(i*10 + j, allowed);
          if (allowed.length == 1) {
            numberDefined++;
            board[i][j] = allowed[0];
            IMPACTED_PIECES[track++] = 10*i + j;
            System.out.println("track = " + track);
          }
          if (allowed.length == 0) {
            // System.out.println("No allowed values! Invalid solution!");
            return;
          }
        }
      }
    }

    // System.out.println("numberDefined = " + numberDefined + ", empty = " + emptyCoordinates.size());
    if (numberDefined == 0 && emptyCoordinates.size() > 0) {
      // Try some numbers now...
      int k = 2; // we look for anything where 2 numbers are possible;
      char[][] result = null;
      while (result == null) {
        for (Map.Entry<Integer, Character[]> empty: emptyCoordinates.entrySet()) {
          Character[] allowedForEmpty = empty.getValue();
          if (allowedForEmpty.length > k) {
            continue;
          }
          for (int m = 0; m < k; m++) {
            int emptyCoordinate = empty.getKey();
            int emptyX = getX(emptyCoordinate);
            int emptyY = getY(emptyCoordinate);
            int lastChange = track - 1;
            board[emptyX][emptyY] = allowedForEmpty[m];
            IMPACTED_PIECES[track++] = emptyX*10 + emptyY;
            System.out.println("track = " + track);
            solveSudoku(board);
            if (!containsEmpty(board)) {
              return;
            } else {
              // System.out.println("!!!!!!!!!!!!!!!!!m!! Invalid solution detected !!!!!!!!!!!!!!!!!!!!!!!!");
              for (int u = track - 1; u > lastChange; u--) {
                int coord = IMPACTED_PIECES[u];
                board[getX(coord)][getY(coord)] = EMPTY;
                track--;
                System.out.println("track = " + track);
              }
            }
          }
        }
        k++;
      }
    }

    if (emptyCoordinates.size() > 0) {
      solveSudoku(board);
    } else {
      // System.out.println("###################### Solution is done ########################");
      for (int i = 0; i < SIZE_X; i++) {
        for (int j = 0; j < SIZE_Y; j++) {
          // System.out.print(board[i][j] + " ");
        }
        // System.out.println();
      }
    }
  }

  private static boolean containsEmpty(char[][] board) {
    for (int i = 0; i < SIZE_X; i++) {
      for (int j = 0; j < SIZE_Y; j++) {
        if (isEmpty(board[i][j])) {
          return true;
        }
      }
    }
    return false;
  }

  private static char[][] copyOfBoard(char[][] board) {
    char[][] tmpBoard = new char[9][9];
    for (int i = 0; i < SIZE_X; i++) {
      for (int j = 0; j < SIZE_Y; j++) {
        tmpBoard[i][j] = board[i][j];
      }
    }
    return tmpBoard;
  }

  private static void copyBoard(char[][] source, char[][] target) {
    for (int i = 0; i < SIZE_X; i++) {
      for (int j = 0; j < SIZE_Y; j++) {
        target[i][j] = source[i][j];
      }
    }
  }

  private static boolean isEmpty(char piece) {
    return piece == EMPTY;
  }

  private static Character[] allowedNumbers(char[][] board, int x, int y) {
    for (int i = 0; i < SIZE_X; i++) {
      char piece = board[i][y];
      if (ALLOWED_NUMBERS.contains(piece)) {
        ALLOWED_NUMBERS.remove(piece);
      }
    }
    for (int j = 0; j < SIZE_Y; j++) {
      char piece = board[x][j];
      if (ALLOWED_NUMBERS.contains(piece)) {
        ALLOWED_NUMBERS.remove(piece);
      }
    }
    for (int i = x - x % 3; i < 3 + x - x % 3; i++) {
      for (int j = y - y % 3; j < 3 + y - y % 3; j++) {
        char piece = board[i][j];
        if (ALLOWED_NUMBERS.contains(piece)) {
          ALLOWED_NUMBERS.remove(piece);
        }
      }
    }
    Character[] result = ALLOWED_NUMBERS.toArray(new Character[ALLOWED_NUMBERS.size()]);
    ALLOWED_NUMBERS.addAll(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9'));

    return result;
  }

  private static int getX(int coordinate) {
    return coordinate / 10;
  }

  private static int getY(int coordinate) {
    return coordinate % 10;
  }
}
