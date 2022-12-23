package pl.kes.algorithms.book.chapter5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

  public Collection<Integer> searchAll(String txt) {
    int i, j, N = txt.length();
    List<Integer> result = new ArrayList<>();
    int x = 0;
    for (i = 0, j = 0; i < N; i++) {
      if (j >= M) {
        // found one, reset
        result.add(i - M);
        j = x;
        x--;
      }
      if (j == 0) {
        x = 0;
      } else if (j == 1) {
        x = dfa[index(txt.charAt(i))][0];
      } else {
        x = dfa[index(txt.charAt(i))][x];
      }
      j = dfa[index(txt.charAt(i))][j];
    }
    if (j == M) {
      result.add(N - M);
    }
    return result;
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
