package pl.kes.algorithms;

public class XOCapture {

  public static void main(String...args) {
    char[][] board = new char[][] {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
    solve(board);
    printBoard(board);
  }

  public static void solve(char[][] board) {

    for (int i = 0; i < board.length; i++) {
      if (board[i][0] == 'O') {
        board[i][0] = 'Y';
        drawComponent(board, i, 0);
      }
      if (board[i][board[i].length - 1] == 'O') {
        board[i][board[i].length - 1] = 'Y';
        drawComponent(board, i, board[i].length - 1);
      }
    }

    for (int j = 0; j < board[0].length; j++) {
      if (board[0][j] == 'O') {
        board[0][j] = 'Y';
        drawComponent(board, 0, j);
      }
      if (board[board.length - 1][j] == 'O') {
        board[board.length - 1][j] = 'Y';
        drawComponent(board, board.length - 1, j);
      }
    }

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] == 'Y') {
          board[i][j] = 'O';
        } else if (board[i][j] == 'O') {
          board[i][j] = 'X';
        }
      }
    }

  }

  private static void drawComponent(char[][] board, int x, int y) {
    if (x - 1 >= 0 && board[x-1][y] == 'O') {
      board[x-1][y] = 'Y';
      drawComponent(board, x-1, y);
    }
    if (x + 1 < board.length && board[x+1][y] == 'O') {
      board[x+1][y] = 'Y';
      drawComponent(board, x+1, y);
    }
    if (y - 1 >= 0 && board[x][y - 1] == 'O') {
      board[x][y-1] = 'Y';
      drawComponent(board, x, y-1);
    }
    if (y + 1 < board[x].length && board[x][y+1] == 'O') {
      board[x][y+1] = 'Y';
      drawComponent(board, x, y+1);
    }
  }

  private static void printBoard(char[][] board) {
    System.out.println("#########################");
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        System.out.print(board[i][j]);
      }
      System.out.println();
    }
    System.out.println("#########################");
  }

}
