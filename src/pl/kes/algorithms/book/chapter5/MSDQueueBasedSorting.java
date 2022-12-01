package pl.kes.algorithms.book.chapter5;

import edu.princeton.cs.algs4.Queue;

public class MSDQueueBasedSorting {

  private static final int R = 'z' - 'a' + 1;
  private static final int M = 15;

  public void sort(String[] a) {
    Queue<String> queue = new Queue<>();
    for (int i = 0; i < a.length; i++) {
      queue.enqueue(a[i]); // O (n)
    }
    sort(queue, 0);
    int i = 0;
    while (!queue.isEmpty()) {
      a[i++] = queue.dequeue(); // O (n)
    }
  }

  private void sort(Queue<String> queue, int d) { // O(nR)
    if (queue == null || queue.isEmpty()) {
      return;
    }
    Queue<String>[] queues = new Queue[R + 1];
    for (int i = 0; i < R + 1; i++) {
      queues[i] = new Queue<>(); // O (R)
    }
    boolean anyMeaningfulSorting = false;
    while (!queue.isEmpty()) { // O(n)
      final String str = queue.dequeue();
      final int index = key(str, d) + 1;
      if (index != 0) {
        anyMeaningfulSorting = true;
      }
      queues[key(str, d) + 1].enqueue(str);
    }

    if (anyMeaningfulSorting) {
      for (int i = 0; i < R + 1; i++) { // O(R)
        sort(queues[i], d + 1); // O(ni + R)
      } // ==> O(Rn + R) = O(nR)
    }
    for (int i = 0; i < R + 1; i++) { // O(n)
      while (!queues[i].isEmpty()) {
        queue.enqueue(queues[i].dequeue());
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
