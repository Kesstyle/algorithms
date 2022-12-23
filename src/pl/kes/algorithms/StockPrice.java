package pl.kes.algorithms;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class StockPrice {

  private int lastTime;
  private int current;
  Map<Integer, Integer> timestamps;
  PriorityQueue<int[]> max, min;

  public StockPrice() {
    lastTime = -1;
    timestamps = new HashMap<>();
    max = new PriorityQueue<>((a, b) -> b[0] - a[0]);
    min = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
  }

  public void update(int timestamp, int price) {
    if (lastTime <= timestamp) {
      lastTime = timestamp;
      current = price;
    }
    timestamps.put(timestamp, price);
    min.add(new int[] {price, timestamp});
    max.add(new int[] {price, timestamp});
  }

  public int current() {
    return current;
  }

  public int maximum() {
    while (true) {
      int[] maxArr = max.peek();
      if (timestamps.get(maxArr[1]) == maxArr[0]) {
        return maxArr[0];
      }
      max.poll();
    }
  }

  public int minimum() {
    while (true) {
      int[] minArr = min.peek();
      if (timestamps.get(minArr[1]) == minArr[0]) {
        return minArr[0];
      }
      min.poll();
    }
  }


}
