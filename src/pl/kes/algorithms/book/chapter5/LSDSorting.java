package pl.kes.algorithms.book.chapter5;

public class LSDSorting {

  public void sort(String[] a, int w) {
    int n = a.length;
    int r = 'z' - 'a' + 1;
    for (int i = w - 1; i >= 0; i--) {
      int[] counts = new int[r + 1];
      for (int j = 0; j < n; j++) {
        counts[key(a[j], i) + 1]++;
      }
      for (int j = 0; j < r; j++) {
        counts[j + 1] += counts[j];
      }
      String[] aux = new String[n];
      for (int j = 0; j < n; j++) {
        aux[counts[key(a[j], i)]++] = a[j];
      }
      for (int j = 0; j < n; j++) {
        a[j] = aux[j];
      }
    }
  }

  private int key(String str, int w) {
    return str.charAt(w) - 'a';
  }
}
