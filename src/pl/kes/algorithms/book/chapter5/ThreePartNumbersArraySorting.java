package pl.kes.algorithms.book.chapter5;

public class ThreePartNumbersArraySorting {

  public void sort(int[][] arr) {
    sort(arr, 0, arr.length - 1, 0);
  }

  private void sort(int[][] arr, int lo, int hi, int d) {
    if (hi <= lo) {
      return;
    }
    int lt = lo, gt = hi;
    int i = lt + 1;
    int v = key(arr[lo], d);
    while (i <= gt) {
      int val = key(arr[i], d);
      if (val > v) {
        exchange(arr, i, gt--);
      } else if (val < v) {
        exchange(arr, lt++, i++);
      } else {
        i++;
      }
    }
    sort(arr, lo, lt - 1, d);
    sort(arr, lt, gt, d + 1);
    sort(arr, gt + 1, hi, d);
  }

  private void exchange(int[][] arr, int i, int j) {
    int[] tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  private int key(int[] arr, int d) {
    if (arr.length <= d) {
      return Integer.MIN_VALUE;
    }
    return arr[d];
  }
}
