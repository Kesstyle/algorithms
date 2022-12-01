package pl.kes.algorithms.book.chapter5;

public class InsertionSort {

  public void sort(String[] a, int lo, int hi, int d) {
    for (int i = lo; i <= hi; i++) {
      for (int j = i; j > lo && less(a[j], a[j-1], d); j--) {
        exch(a, j, j-1);
      }
    }
  }

  private void exch(String[] a, int i, int j) {
    String tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }

  private boolean less(String a, String b, int d) {
    return a.substring(d).compareTo(b.substring(d)) < 0;
  }
}
