package pl.kes.algorithms.book.chapter5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BoyerMoore {

  private static final int R = 'z' - 'a' + 1;
  private int[] right;
  private String template;

  public BoyerMoore(String template) {
    right = new int[R];
    this.template = template;
    for (int c = 0; c < R; c++) {
      for (int i = 0; i < template.length(); i++) {
        right[c] = -1;
      }
    }
    for (int i = 0; i < template.length(); i++) {
      right[index(template.charAt(i))] = i;
    }
  }

  public int search(String s) {
    int N = s.length(), M = template.length();
    int skip;
    for (int i = 0; i <= N - M; i += skip) {
      skip = 0;
      for (int j = M - 1; j >=0; j--) {
        if (template.charAt(j) != s.charAt(i + j)) {
          skip = Math.max(1, j - right[index(s.charAt(i + j))]);
          break;
        }
      }
      if (skip == 0) {
        return i;
      }
    }
    return N;
  }

  public Collection<Integer> searchAll(String s) {
    int N = s.length(), M = template.length();
    int skip;
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i <= N - M; i += skip) {
      skip = 0;
      for (int j = M - 1; j >=0; j--) {
        if (template.charAt(j) != s.charAt(i + j)) {
          skip = Math.max(1, j - right[index(s.charAt(i + j))]);
          break;
        }
      }
      if (skip == 0) {
        res.add(i);
        skip = 1;
      }
    }
    return res;
  }

  private int index(char c) {
    return 'z' - c;
  }
}
