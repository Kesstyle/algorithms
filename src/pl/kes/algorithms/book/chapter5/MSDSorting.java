package pl.kes.algorithms.book.chapter5;

public class MSDSorting {

  private static final int R = 'z' - 'a' + 1;
  private static final int M = 15;

  public void sort(String[] a) {
    sort(a, 0, a.length - 1, 0);
  }

  private void sort(String[] a, int lo, int hi, int d) {
    if (hi - lo <= M) {
      new InsertionSort().sort(a, lo, hi, d);
      return;
    }
    int[] counts = new int[R + 2];
    for (int i = lo; i <= hi; i++) {
      counts[key(a[i], d) + 2]++;
    }
    for (int r = 0; r < R + 1; r++) {
      counts[r + 1] += counts[r];
    }
    String[] aux = new String[hi - lo + 1];
    for (int j = lo; j <= hi; j++) {
      aux[counts[key(a[j], d) + 1]++] = a[j];
    }
    for (int j = lo; j <= hi; j++) {
      a[j] = aux[j - lo];
    }
    for (int r = 0; r < R + 1; r++) {
      sort(a, lo + counts[r], lo + counts[r + 1] - 1, d + 1);
    }
  }

  private int key(String s, int d) {
    if (s.length() <= d) {
      return -1;
    }
    return s.charAt(d) - 'a';
  }
}
