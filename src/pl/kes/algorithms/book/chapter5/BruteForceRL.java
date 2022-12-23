package pl.kes.algorithms.book.chapter5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BruteForceRL {

  public int search(String s, String pat) {
    int N = s.length(), M = pat.length();
    for (int i = 0; i <= N - M; i ++) {
      boolean found = true;
      for (int j = M - 1; j >= 0; j--) {
        if (s.charAt(i + j) != pat.charAt(j)) {
          found = false;
          break;
        }
      }
      if (found) {
        return i;
      }
    }
    return N;
  }

  public int count(String s, String pat) {
    return searchAll(s, pat).size();
  }

  public Collection<Integer> searchAll(String s, String pat) {
    int res = -1;
    List<Integer> result = new ArrayList<>();
    int start = 0;
    int N = s.length();
    while (res < N) {
      String str = s.substring(start);
      N = str.length();
      res = search(str, pat);
      if (res < N) {
        start += res + 1;
        result.add(start - 1);
        if (start == N) {
          break;
        }
      }
    }
    return result;
  }
}
