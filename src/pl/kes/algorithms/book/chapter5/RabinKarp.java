package pl.kes.algorithms.book.chapter5;

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
      if (hash == hashPattern && verifyMatch(s.substring(i - M + 1, i + 1))) {
        return i - M + 1;
      }
    }
    return N;
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
