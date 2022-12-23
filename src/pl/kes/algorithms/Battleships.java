package pl.kes.algorithms;

public class Battleships {

  private static final char EMPTY = 'E';
  private static final char SHIP = 'S';

  public int countBattleships(char[][] board) {
    int res = 0;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if (board[i][j] != 'S' && board[i][j] != 'E') {
          res += dfs(board, i, j);
        }
      }
    }
    return res;
  }

  private int dfs(char[][] board, int i, int j) {
    if (i >= board.length || i < 0 || j < 0 || j >= board[0].length) {
      return 0;
    }
    char c = board[i][j];
    if (c == 'S' || c == 'E') {
      return 0;
    }
    if (c == '.') {
      board[i][j] = 'E';
      return 0;
    }
    board[i][j] = 'S';
    dfs(board, i - 1, j);
    dfs(board, i + 1, j);
    dfs(board, i, j - 1);
    dfs(board, i, j + 1);
    return 1;
  }
}
