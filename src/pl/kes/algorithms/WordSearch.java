package pl.kes.algorithms;

public class WordSearch {

  public static void main(String...args) {
    WordSearch wordSearch = new WordSearch();
    char[][] board = new char[][] {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
    String s = "ABCCED";
    boolean result = wordSearch.exist(board, s);
    System.out.println(result);
  }

  private int[][] dfa;
  private boolean[][] marked;
  private int M;
  private static final int R = 'z' - 'A' + 1;
  private int X;
  private int Y;

  public boolean exist(char[][] board, String word) {
    X = board.length;
    Y = board[0].length;
    marked = new boolean[X][Y];
    for (int i = 0; i < X; i++) {
      for (int j = 0; j < Y; j++) {
        if (word.charAt(0) == board[i][j] && recursive(word, board, i, j, 0)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean recursive(String s, char[][] board, int x, int y, int j) {
    if (x < 0 || y < 0 || x >= X || y >= Y) {
      return false;
    }

    if (marked[x][y]) {
      return false;
    }

    if (s.charAt(j) != board[x][y]) {
      return false;
    }

    if (j == s.length() - 1) {
      return true;
    }

    marked[x][y] = true;
    if (recursive(s, board, x + 1, y, j + 1) ||
        recursive(s, board, x - 1, y, j + 1) ||
        recursive(s, board, x, y + 1, j + 1) ||
        recursive(s, board, x, y - 1, j + 1)) {
      return true;
    }
    marked[x][y] = false;
    return false;
  }

  private void buildDfa(String s) {
    M = s.length();
    dfa = new int[R][M];
    dfa[0][0] = 1;
    int x = 0;
    for (int i = 1; i < M; i++) {
      for (int j = 0; j < R; j++) {
        dfa[j][i] = dfa[j][x];
      }
      dfa[i][i] = i + 1;
      x = dfa[index(s.charAt(i))][x];
    }
  }

  private int index(char c) {
    return c - 'A';
  }
}
