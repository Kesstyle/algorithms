package pl.kes.algorithms.book.chapter4.exercises;

import java.util.ArrayList;
import java.util.List;

public class Degrees {

  private int[] indegrees;
  private int[] outdegrees;
  private int V;

  public Degrees(Digraph g) {
    this.V = g.V();
    indegrees = new int[V];
    outdegrees = new int[V];
    for (int i = 0; i < V; i++) {
      for (Integer v: g.adj(i)) {
        indegrees[v]++;
        outdegrees[i]++;
      }
    }
  }

  public int indegree(int v) {
    return indegrees[v];
  }

  public int outdegree(int v) {
    return outdegrees[v];
  }

  public Iterable<Integer> sources() {
    final List<Integer> res = new ArrayList<>();
    for (int i = 0; i < V; i++) {
      if (indegrees[i] == 0 && outdegrees[i] != 0) {
        res.add(i);
      }
    }
    return res;
  }

  public Iterable<Integer> sinks() {
    final List<Integer> res = new ArrayList<>();
    for (int i = 0; i < V; i++) {
      if (indegrees[i] != 0 && outdegrees[i] == 0) {
        res.add(i);
      }
    }
    return res;
  }

  public boolean isMap() {
    for (int i = 0; i < V; i++) {
      if (outdegrees[i] != 1) {
        return false;
      }
    }
    return true;
  }
}
