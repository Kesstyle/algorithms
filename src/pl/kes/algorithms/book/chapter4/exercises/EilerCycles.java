package pl.kes.algorithms.book.chapter4.exercises;

import java.util.List;

public class EilerCycles {

  private int[] degrees;
  private boolean[] marked;
  private boolean hasEilerCycle = true;

  public EilerCycles(Graph g) {
    this.marked = new boolean[g.V()];
    this.degrees = new int[g.V()];
    dfs(g, 0);

  }

  private List<Integer> cycle(Graph g, int s) {
    return null;
  }

  private void dfs(Graph g, int s) {
    marked[s] = true;
    int degree = 0;
    for (int v: g.adj(s)) {
      degree++;
      if (!marked[v]) {
        dfs(g, v);
      }
    }
    if (degree % 2 == 1) {
      hasEilerCycle = false;
    }
    degrees[s] = degree;
  }
}
