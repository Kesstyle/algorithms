package pl.kes.algorithms.book.chapter5;

public class WhitespacesSearch {

  public int searchWhitespaces(String s, int M) {
    int N  = s.length();
    for (int i = 0; i < N - M; i++) {
      int skip = 0;
      for (int j = M - 1; j >= 0; j--) {
        if (s.charAt(i + j) != ' ') {
          skip = j + 1;
          if (skip < 1) {
            skip = 1;
          }
        }
      }
      if (skip == 0) {
        return i;
      }
    }
    return N;
  }
}
