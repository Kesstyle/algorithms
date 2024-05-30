package pl.kes.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class NetworkDelayTime {

  private int[] connectAt;
  private boolean[] visited;
  private Map<Integer, List<Pair>> adjMap;

  public int networkDelayTime(int[][] times, int n, int k) {
    connectAt = new int[n + 1];
    visited = new boolean[n + 1];
    Arrays.fill(connectAt, Integer.MAX_VALUE);
    connectAt[k] = 0;
    visited[k] = true;
    adjMap = new HashMap<>();
    for (int i = 0; i < times.length; i++) {
      adjMap.putIfAbsent(times[i][0], new ArrayList<>());
      adjMap.get(times[i][0]).add(new Pair(times[i][1], times[i][2]));
    }
    PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.dist));
    pq.add(new Pair(k, 0));
    while (!pq.isEmpty()) {
      Pair next = pq.poll();
      int v = next.v;
      visited[v] = true;
      if (connectAt[v] < next.dist) {
        continue;
      }
      List<Pair> adjList = adjMap.get(v);
      if (adjList == null) {
        continue;
      }
      for (Pair adj: adjList) {
        if (visited[adj.v] || connectAt[adj.v] <= next.dist + adj.dist) {
          continue;
        }
        connectAt[adj.v] = next.dist + adj.dist;
        pq.add(new Pair(adj.v, connectAt[adj.v]));
      }
    }
    int max = Integer.MIN_VALUE;
    for (int i = 1; i <= n; i++) {
      if (connectAt[i] == Integer.MAX_VALUE) {
        return -1;
      }
      if (max < connectAt[i]) {
        max = connectAt[i];
      }
    }
    return max;
  }

  class Pair {
    int v;
    int dist;

    public Pair(int v, int dist) {
      this.v = v;
      this.dist = dist;
    }
  }
}
