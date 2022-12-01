package pl.kes.algorithms.book.chapter5;

public class LsdVariableSorting {

  private static final int R = 26;

  public void sort(String[] strings, int w) {
    int n = strings.length;
    for (int i = w - 1; i >= 0; i--) {
      int[] counts = new int[R + 2];
      for (int j = 0; j < n; j++) {
        counts[key(strings[j], i) + 2]++;
      }
      for (int j = 0; j < R + 1; j++) {
        counts[j + 1] += counts[j];
      }
      String[] aux = new String[n];
      for (int j = 0; j < n; j++) {
        aux[counts[key(strings[j], i) + 1]++] = strings[j];
      }
      for (int j = 0; j < n; j++) {
        strings[j] = aux[j];
      }
    }
  }

  private int key(String s, int d) {
    if (s.length() <= d) {
      return -1;
    }
    return s.charAt(d) - 'a';
  }
}
