package pl.kes.algorithms.book.chapter4.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DirectedCycle {

  private int[] edgeTo;
  private boolean[] marked;
  private Stack<Integer> cycle;
  private boolean[] inStack;
  private int n;

  public DirectedCycle(Digraph g) {
    n = g.V();
    edgeTo = new int[n];
    marked = new boolean[n];
    inStack = new boolean[n];
    for (int i = 0; i < n; i++) {
      if (!marked[i]) {
        dfs(g, i);
      }
    }
  }

  public boolean hasCycle() {
    return cycle != null;
  }

  public Iterable<Integer> cycle() {
    final List<Integer> cList = new ArrayList<>();
    if (!hasCycle()) {
      return cList;
    }
    while (!cycle.isEmpty()) {
      cList.add(cycle.pop());
    }
    return cList;
  }

  private void dfs(Digraph g, int v) {
    inStack[v] = true;
    marked[v] = true;
    for (Integer w: g.adj(v)) {
      if (!marked[w]) {
        edgeTo[w] = v;
        dfs(g, w);
      } else if (inStack[w]) {
        cycle = new Stack<>();
        for (int x = v; x != w; x = edgeTo[x]) {
          cycle.push(x);
        }
        cycle.push(w);
        cycle.push(v);
      }
    }

    inStack[v] = false;
  }
}
