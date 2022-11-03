package pl.kes.algorithms.book.chapter4.exercises.mod;

public class MinPQ<T extends Comparable> {

  private T[] arr;
  private int size;

  public MinPQ(int n) {
    arr = (T[]) new Comparable[n+1];
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public T getMin() {
    T min = arr[1];
    arr[1] = arr[size--];
    sink(1);
    return min;
  }

  public void addElem(T t) {
    arr[++size] = t;
    swim(size);
  }

  private void swap(int i, int j) {
    T tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  private boolean less(int i, int j) {
    return arr[i].compareTo(arr[j]) < 0;
  }

  private void swim(int i) {
    int k = i / 2;
    int n = i;
    while (k > 0) {
      if (!less(n, k)) {
        break;
      }
      swap(n, k);
      n = k;
      k = n / 2;
    }
  }

  private void sink(int i) {
    int k = 2 * i;
    int n = i;
    while (k <= size) {
      if (k < size && less(k+1, k)) {
        k = k + 1;
      }
      if (!less(k, n)) {
        break;
      }
      swap(k, n);
      n = k;
      k = 2 * n;
    }
  }
}
