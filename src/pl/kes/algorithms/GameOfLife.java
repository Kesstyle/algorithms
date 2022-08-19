package pl.kes.algorithms;

public class GameOfLife {

  public static void main(String...args) {
    GameOfLife gameOfLife = new GameOfLife();
    int[][] board = new int[][] {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
    gameOfLife.gameOfLife(board);

  }

  int m;
  int n;

  public void gameOfLife(int[][] board) {
    m = board.length;
    n = board[0].length;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        board[i][j] = countNeighbours(board, i, j) * 10 + board[i][j];
      }
    }

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        int rank = board[i][j] / 10;
        int cell = board[i][j] % 10;
        if (cell == 1 && (rank < 2 || rank > 3)) {
          board[i][j] = 0;
        } else if (cell == 0 && rank == 3) {
          board[i][j] = 1;
        } else {
          board[i][j] = cell;
        }
      }
    }
  }

  private int countNeighbours(int[][] board, int x, int y) {
    int numLive = 0;
    for (int i = Math.max(x - 1, 0); i <= Math.min(x + 1, m - 1); i++) {
      for (int j = Math.max(y - 1, 0); j <= Math.min(y + 1, n - 1); j++) {
        if (i == x && j == y) {
          continue;
        }
        if (board[i][j] % 10 == 1) {
          numLive++;
        }
      }
    }
    return numLive;
  }

  public void gameOfLifeRecursive(int[][] board) {
    m = board.length;
    n = board[0].length;

    decideOnCell(board, 0, 0);
  }

  private void decideOnCell(int[][] board, int x, int y) {
    int rank = countNeighbours(board, x, y);
    if (x == m - 1 && y != n - 1) {
      decideOnCell(board, 0, y + 1);
    } else if (x != m - 1 || y != n - 1) {
      decideOnCell(board, x + 1, y);
    }
    if (board[x][y] == 1 && (rank < 2 || rank > 3)) {
      board[x][y] = 0;
    } else if (board[x][y] == 0 && rank == 3) {
      board[x][y] = 1;
    }
    //   System.out.println("Setting " + x + "," + y + " to " + board[x][y]);
  }

  public void gameOfLifeSimple(int[][] board) {
    m = board.length;
    n = board[0].length;

    int[][] lives = new int[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        lives[i][j] = countNeighbours(board, i, j);
      }
    }
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i][j] == 1 && (lives[i][j] < 2 || lives[i][j] > 3)) {
          board[i][j] = 0;
        } else if (board[i][j] == 0 && lives[i][j] == 3) {
          board[i][j] = 1;
        }
      }
    }
  }
}
