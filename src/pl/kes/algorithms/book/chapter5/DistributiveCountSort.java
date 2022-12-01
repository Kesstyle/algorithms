package pl.kes.algorithms.book.chapter5;

public class DistributiveCountSort {

  public <V> void sort(Entry<Integer, V>[] a) {
    int[] counts = new int[a.length + 1];
    for (int i = 0; i < a.length; i++) {
      counts[a[i].getKey() + 1]++;
    }
    for (int i = 0; i < a.length; i++) {
      counts[i + 1] += counts[i];
    }
    Entry<Integer, V>[] aux = new Entry[a.length];
    for (int i = 0; i < a.length; i++) {
      aux[counts[a[i].getKey()]++] = a[i];
    }
    for (int i = 0; i < a.length; i++) {
      a[i] = aux[i];
    }
  }
}
