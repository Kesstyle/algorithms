package pl.kes.algorithms.book.chapter5;

public class InsertionNumberSort {

  public void sort(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = i; j > 0 && arr[j] < arr[j-1]; j--) {
        exch(arr, j, j-1);
      }
    }
  }

  private void exch(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }
}
