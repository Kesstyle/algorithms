package pl.kes.algorithms;

import java.util.ArrayList;
import java.util.List;

public class EmployeeFreeTime {

  public static void main(String...args) {
    EmployeeFreeTime employeeFreeTime = new EmployeeFreeTime();
    List<Interval> first = new ArrayList<>();
    first.add(new Interval(1, 2));
    first.add(new Interval(5, 6));
    List<List<Interval>> input = new ArrayList<>();
    input.add(first);
    first = new ArrayList<>();
    first.add(new Interval(1, 3));
    first.add(new Interval(4, 10));
    input.add(first);
    employeeFreeTime.employeeFreeTime(input);
  }

  public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
    int size = 0;
    for (int i = 0; i < schedule.size(); i++) {
      size += schedule.get(i).size();
    }
    PQ pq = new PQ(size);
    for (int i = 0; i < schedule.size(); i++) {
      for (int j = 0; j < schedule.get(i).size(); j++) {
        pq.add(schedule.get(i).get(j));
      }
    }
    List<Interval> sumIntervals = new ArrayList<>();
    Interval newInt = null;
    while (!pq.isEmpty()) {
      Interval tmp = pq.getMin();
      if (newInt == null) {
        newInt = tmp;
        continue;
      }
      if (intersect(newInt, tmp)) {
        newInt = joinIntersected(newInt, tmp);
      } else {
        sumIntervals.add(newInt);
        newInt = tmp;
      }
    }
    if (newInt != null) {
      sumIntervals.add(newInt);
    }
    List<Interval> result = new ArrayList<>();
    for (int i = 0; i < sumIntervals.size() - 1; i++) {
      result.add(new Interval(sumIntervals.get(i).end, sumIntervals.get(i + 1).start));
    }
    return result;
  }

  private boolean intersect(Interval first, Interval second) {
    if (first.start < second.start && first.end < second.start ||
        second.start < first.start && second.end < first.start) {
      return false;
    }
    return true;
  }

  private Interval joinIntersected(Interval first, Interval second) {
    int newStart = Math.min(first.start, second.start);
    int newEnd = Math.max(first.end, second.end);
    return new Interval(newStart, newEnd);
  }

  class PQ {

    Interval[] heap;
    int size;

    public PQ(int n) {
      heap = new Interval[n + 1];
    }

    public void add(Interval inter) {
      heap[++size] = inter;
      swim(size);
    }

    public Interval getMin() {
      Interval tmp = heap[1];
      swap(1, size--);
      sink(1);
      return tmp;
    }

    public boolean isEmpty() {
      return size == 0;
    }

    private void sink(int i) {
      int k = 2 * i;
      int n = i;
      while (k <= size) {
        if (k < size && less(k + 1, k)) {
          k = k + 1;
        }
        if (less(n, k)) {
          return;
        }
        swap(n, k);
        n = k;
        k = 2 * n;
      }
    }

    private void swim(int i) {
      int k = i / 2;
      int n = i;
      while (k > 0) {
        if (less(k, n)) {
          return;
        }
        swap(n, k);
        n = k;
        k = n / 2;
      }
    }

    private void swap(int i, int j) {
      Interval tmp = heap[i];
      heap[i] = heap[j];
      heap[j] = tmp;
    }

    private boolean less(int i, int j) {
      Interval first = heap[i];
      Interval second = heap[j];
      if (first.start < second.start) {
        return true;
      }
      if (first.start == second.start && first.end < second.end) {
        return true;
      }
      return false;
    }
  }

  static class Interval {
    public int start;
    public int end;

    public Interval() {
    }

    public Interval(int _start, int _end) {
      start = _start;
      end = _end;
    }
  }
}


