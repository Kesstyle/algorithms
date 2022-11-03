package pl.kes.algorithms.book.chapter4.exercises.paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class EdgeWeightedDigraphTopological {

  private boolean[] marked;
  private Stack<Integer> topological;

  public EdgeWeightedDigraphTopological(EdgeWeightedDigraph g) {
    marked = new boolean[g.V()];
    topological = new Stack<>();
    for (int i = 0; i < g.V(); i++) {
      if (!marked[i]) {
        dfs(g, i);
      }
    }
  }

  public Iterable<Integer> topological() {
    List<Integer> topoList = new ArrayList<>();
    while (!topological.isEmpty()) {
      topoList.add(topological.pop());
    }
    return topoList;
  }

  private void dfs(EdgeWeightedDigraph g, int v) {
    marked[v] = true;
    for (DirectedEdge e: g.adj(v)) {
      int w = e.to();
      if (marked[w]) {
        continue;
      }
      dfs(g, w);
    }
    topological.push(v);
  }
}
