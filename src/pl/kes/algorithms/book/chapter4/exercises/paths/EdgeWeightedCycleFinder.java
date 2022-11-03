package pl.kes.algorithms.book.chapter4.exercises.paths;

import java.util.Stack;

public class EdgeWeightedCycleFinder {

  private boolean[] marked;
  private boolean[] inStack;
  private DirectedEdge[] edgeTo;
  private Stack<DirectedEdge> cycle;

  public EdgeWeightedCycleFinder(EdgeWeightedDigraph g) {
    marked = new boolean[g.V()];
    inStack = new boolean[g.V()];
    edgeTo = new DirectedEdge[g.V()];
    for (int i = 0; i < g.V(); i++) {
      if (!marked[i]) {
        dfs(g, i);
      }
    }
  }

  public Iterable<DirectedEdge> cycle() {
    return cycle;
  }

  private void dfs(EdgeWeightedDigraph g, int v) {
    marked[v] = true;
    inStack[v] = true;
    for (DirectedEdge e: g.adj(v)) {
      int w = e.to();
      if (!marked[w] || edgeTo[w] == null) {
        edgeTo[w] = e;
      }
      if (!marked[w]) {
        dfs(g, w);
      }
      if (inStack[w]) {
        cycle = new Stack<>();
        for (DirectedEdge edge = edgeTo[v]; edge.to() != w; edge = edgeTo[edge.from()]) {
          cycle.push(edge);
        }
        cycle.push(e);
      }
    }

    inStack[v] = false;
  }

}
