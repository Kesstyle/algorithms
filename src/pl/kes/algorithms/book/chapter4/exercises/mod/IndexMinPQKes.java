package pl.kes.algorithms.book.chapter4.exercises.mod;

public class IndexMinPQKes<T extends Comparable> {

  private int[] indexes;
  private T[] arr;
  private int size;

  public IndexMinPQKes(int n) {
    arr = (T[]) new Comparable[n+1];
    indexes = new int[n+1];
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public T getMin() {
    T min = arr[1];
    arr[1] = arr[size];
    indexes[1] = indexes[size--];
    sink(1);
    return min;
  }

  public int getMinIndex() {
    int minIndex = indexes[1];
    arr[1] = arr[size];
    indexes[1] = indexes[size--];
    sink(1);
    return minIndex;
  }

  public void addElem(int index, T t) {
    arr[++size] = t;
    indexes[size] = index;
    swim(size);
  }

  public void update(int index, T t) {
    for (int i = 1; i <= size; i++) {
      if (indexes[i] == index) {
        arr[i] = t;
        return;
      }
    }
  }

  public boolean exists(int index) {
    for (int i = 1; i <= size; i++) {
      if (indexes[i] == index) {
        return true;
      }
    }
    return false;
  }

  private void swap(int i, int j) {
    T tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;

    int tmpIndex = indexes[i];
    indexes[i] = indexes[j];
    indexes[j] = tmpIndex;
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
