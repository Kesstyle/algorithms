package pl.kes.algorithms;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SortIntsByPower {

  Map<Integer, Integer> steps = new HashMap<>();

  public int getKth(int lo, int hi, int k) {
    steps.put(1, 0);
    PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
      int powerA = power(a);
      int powerB = power(b);
      if (powerA == powerB) {
        return a - b;
      }
      return powerA - powerB;
    });
    for (int i = lo; i <= hi; i++) {
      pq.add(i);
    }
    return pq.poll();
  }

  private int power(int x) {
    Integer fromMap = steps.get(x);
    if (fromMap != null) {
      return fromMap;
    }
    if (x % 2 == 1) {
      x = 3 * x + 1;
    } else {
      x /= 2;
    }
    if (x == 1) {
      return 1;
    } else {
      return power(x) + 1;
    }
  }
}
