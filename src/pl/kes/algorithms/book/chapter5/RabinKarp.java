package pl.kes.algorithms.book.chapter5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RabinKarp {

  private static final int R = 'z' - 'a' + 1;
  private static final int Q = 113;
  private int hashPattern;
  private int M;
  private String pattern;

  public RabinKarp(String pattern) {
    M = pattern.length();
    hashPattern = hash(pattern);
    this.pattern = pattern;
  }

  public int search(String s) {
    int N = s.length();
    int RM = 1;
    for (int j = 1; j <= M-1; j++) {
      RM = (R * RM) % Q;
    }
    int hash = hash(s.substring(0, M));
    if (hash == hashPattern) {
      return 0;
    }
    for (int i = M; i < N; i++) {
      hash = (hash + Q - (RM * index(s.charAt(i - M))) % Q ) % Q;
      hash = (R * hash + index(s.charAt(i))) % Q;
      if (hash == hashPattern ) {
        return i - M + 1;
      }
    }
    return N;
  }

  public Collection<Integer> searchAll(String s) {
    int N = s.length();
    int hash = hash(s.substring(0, M));
    List<Integer> res = new ArrayList<>();
    if (hash == hashPattern) {
      res.add(0);
    }
    int RM = 1;
    for (int i = 0; i < M - 1; i++) {
      RM = (RM * R) % Q;
    }
    for (int i = M; i < N; i++) {
      hash = (hash + Q - (RM * index(s.charAt(i - M))) % Q) % Q;
      hash = (R * hash + index(s.charAt(i))) % Q;
      if (hash == hashPattern) {
        res.add(i - M + 1);
      }
    }
    return res;
  }

  private boolean verifyMatch(String s) {
    if (s.length() != M) {
      return false;
    }
    for (int i = 0; i < M; i++) {
      if (s.charAt(i) != pattern.charAt(i)) {
        return false;
      }
    }
    return true;
  }

  private int hash(String s) {
    int h = 0;
    for (int i = 0; i < M; i++) {
      h = (R * h + index(s.charAt(i))) % Q;
    }
    return h;
  }

  private int index(char c) {
    return 'z' - c;
  }
}
