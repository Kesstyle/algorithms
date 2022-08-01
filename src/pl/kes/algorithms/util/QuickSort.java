package pl.kes.algorithms.util;

public class QuickSort {

  public static void main(String...args) {
    int[] a = new int[] {5, 5, 5, 3, 2, 6, 8, 8, 8, 1 ,1 ,1 , 10, 11, 5};
    sort3split(a, 0, a.length - 1);
    print(a);
  }

  public static void sort3split(int[] a, int begin, int end) {
    if (end <= begin) {
      return;
    }
    int v = a[begin];
    int lt = begin;
    int i = begin + 1;
    int gt = end;
    while (i <= gt) {
      if (a[i] > v) {
        exchange(a, i, gt--);
      } else if (a[i] < v) {
        exchange(a, lt++, i++);
      } else {
        i++;
      }
    }
    sort3split(a, begin, lt - 1);
    sort3split(a, gt + 1, end);
  }

  private static void exchange(int[] a, int first, int second) {
    int tmp = a[first];
    a[first] = a[second];
    a[second] = tmp;
  }

  private static void print(int[] a) {
    for (int i = 0; i < a.length; i++) {
      System.out.print(a[i] + " ");
    }
  }
}
