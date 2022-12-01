package pl.kes.algorithms.book.chapter5;

public class DFA {

  private int[][] dfa;
  private int M;
  private static final int R = 'z' - 'a' + 1;

  public DFA(String pat) {
    createDfa(pat);
  }

  public int search(String txt) {
    int i, j, N = txt.length();
    for (i = 0, j = 0;  i < N && j < M; i++) {
      j = dfa[index(txt.charAt(i))][j];
    }
    if (j == M) {
      return i - M;
    }
    return N;
  }

  private void createDfa(String pat) {
    M = pat.length();
    dfa = new int[R][M];
    dfa[index(pat.charAt(0))][0] = 1;
    int X = 0;
    for (int j = 1; j < M; j++) {
      for (int i = 0; i < R; i++) {
        dfa[i][j] = dfa[i][X];
      }
      dfa[index(pat.charAt(j))][j] = j + 1;
      X = dfa[index(pat.charAt(j))][X];
    }
  }

  private int index(char c) {
    return c - 'a';
  }
}
