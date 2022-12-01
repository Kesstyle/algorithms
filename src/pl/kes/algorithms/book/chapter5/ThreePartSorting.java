package pl.kes.algorithms.book.chapter5;

public class ThreePartSorting {

  public void sort(String[] a) {
    sort(a, 0, a.length - 1, 0);
    isSorted(a);
  }

  private void sort(String[] a, int lo, int hi, int d) {
    if (hi <= lo) {
      return;
    }
    int lt = lo, gt = hi;
    int i = lo + 1;
    int v = key(a[lo], d);
    while (i <= gt) {
      int w = key(a[i], d);
      if (w > v) {
        exch(a, i, gt--);
      } else if (w < v) {
        exch(a, i++, lt++);
      } else {
        i++;
      }
    }
    sort(a, lo, lt - 1, d);
    sort(a, lt, gt, d + 1);
    sort(a, gt + 1, hi, d);
  }

  private void exch(String[] a, int i, int j) {
    String tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }


  private int key(String s, int d) {
    if (s.length() <= d) {
      return -1;
    }
    return s.charAt(d) - 'a';
  }

  private void isSorted(String[] a) {
    for (int i = 0; i < a.length - 1; i++) {
      if (a[i].compareTo(a[i+1]) > 0) {
        throw new IllegalStateException();
      }
    }
  }

}
