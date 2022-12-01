package pl.kes.algorithms;

public class MeetingRooms2 {

  public static void main(String...args) {
    MeetingRooms2 meetingRooms2 = new MeetingRooms2();
    int[][] intervals = new int[][] {{7,10},{2,4}};
    int res = meetingRooms2.minMeetingRooms(intervals);
    System.out.println(res);
  }

  public int minMeetingRooms(int[][] intervals) {
    sort(intervals, 0, intervals.length - 1);
    Heap heap = new Heap(intervals.length);
    heap.add(intervals[0][1]);
    for (int i = 1; i < intervals.length; i++) {
      int end = heap.peek();
      if (intervals[i][0] >= end) {
        heap.getMin();
      }
      heap.add(intervals[i][1]);
    }
    return heap.size();
  }

  private void sort(int[][] intervals, int start, int end) {
    if (end <= start) {
      return;
    }
    int[] base = intervals[start];
    int i = start, j = end+1;
    while (true) {
      while (compare(base, intervals[++i]) >= 0) {
        if (i == end) {
          break;
        }
      }
      while (compare(base, intervals[--j]) < 0) {
        if (j == start) {
          break;
        }
      }
      if (i >= j) {
        break;
      }
      exchange(intervals, i, j);
    }
    exchange(intervals, start, j);
    sort(intervals, start, j - 1);
    sort(intervals, j + 1, end);
  }

  private void exchange(int[][] intervals, int i, int j) {
    int[] tmp = intervals[i];
    intervals[i] = intervals[j];
    intervals[j] = tmp;
  }

  private int compare(int[] first, int[] second) {
    if (first[0] > second[0]) {
      return 1;
    } else if (first[0] < second[0]) {
      return -1;
    }
    return 0;
  }

  class Heap {

    int[] arr;
    int n;

    public Heap() {
      arr = new int[5];
    }

    public Heap(int size) {
      arr = new int[size + 1];
    }

    public void add(int num) {
      arr[++n] = num;
      swim(n);
      if (n > arr.length / 2) {
        extendArray();
      }
    }

    public int getMin() {
      int min = arr[1];
      arr[1] = arr[n--];
      sink(1);
      return min;
    }

    public int peek() {
      return arr[1];
    }

    public int size() {
      return n;
    }

    private void swim(int i) {
      int k = i / 2;
      int j = i;
      while (k > 0) {
        if (!less(j, k)) {
          break;
        }
        exchange(j, k);
        j = k;
        k = j / 2;
      }
    }

    private void sink(int i) {
      int k = 2 * i;
      int j = i;
      while (k <= n) {
        if (less(k+1, k)) {
          k = k + 1;
        }
        if (!less(k, j)) {
          break;
        }
        exchange(k, j);
        j = k;
        k = 2 * j;
      }
    }

    private void extendArray() {
      int size = arr.length;
      int[] tmp = new int[size * 2];
      for (int i = 0; i < size; i ++) {
        tmp[i] = arr[i];
      }
      arr = tmp;
    }

    private boolean less(int i, int j) {
      return arr[i] < arr[j];
    }

    private void exchange(int i, int j) {
      int tmp = arr[i];
      arr[i] = arr[j];
      arr[j] = tmp;
    }
  }
}
