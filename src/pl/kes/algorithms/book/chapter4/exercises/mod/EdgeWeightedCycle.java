package pl.kes.algorithms.book.chapter4.exercises.mod;

import java.util.Stack;
import pl.kes.algorithms.book.chapter4.exercises.mod.Edge;
import pl.kes.algorithms.book.chapter4.exercises.mod.EdgeWeightedGraph;

public class EdgeWeightedCycle {

  private Stack<Edge> cycle;
  private boolean[] marked;
  private Edge[] edgeTo;

  public EdgeWeightedCycle(EdgeWeightedGraph g) {
    marked = new boolean[g.V()];
    edgeTo = new Edge[g.V()];
    for (int i = 0; i < g.V(); i++) {
      if (!marked[i]) {
        dfs(g, i);
      }
    }
  }

  public Iterable<Edge> edgesCycle() {
    return cycle;
  }

  private void dfs(EdgeWeightedGraph g, int v) {
    marked[v] = true;
    for (Edge e: g.adj(v)) {
      int w = e.other(v);
      if (!marked[w]) {
        edgeTo[w] = e;
        dfs(g, w);
      } else if (cycle == null && ((edgeTo[w] == null && edgeTo[v].other(v) != w) || !e.equals(edgeTo[v]))) {
        cycle = new Stack<>();
        for (int x = v; x != w; x = edgeTo[x].other(x)) {
          cycle.push(edgeTo[x]);
        }
        cycle.push(e);
      }
    }
  }
}
