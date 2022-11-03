package pl.kes.algorithms.book.chapter4.exercises;

import pl.kes.algorithms.book.standard.StdOut;

public class StrongComponents {

  private boolean[] marked;
  private int[] components;
  int count;

  public StrongComponents(Digraph g) {
    marked = new boolean[g.V()];
    components = new int[g.V()];
    Digraph gr = g.reverse();
    Iterable<Integer> topological = new TopologicalOrder(gr).topologicalOrder();
    StdOut.println("Topological order for strong components:");
    topological.forEach(i -> StdOut.print(i + " "));
    StdOut.println();
    for (int v: topological) {
      if (!marked[v]) {
        dfs(g, v);
        count++;
      }
    }
  }

  public boolean sameComponent(int v, int w) {
    return components[v] == components[w];
  }

  public String toString() {
    final StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < components.length; i++) {
      stringBuilder.append(i + ": component " + components[i] + "\r\n");
    }
    return stringBuilder.toString();
  }

  private void dfs(Digraph g, int v) {
    marked[v] = true;
    components[v] = count;
    for (int w: g.adj(v)) {
      if (!marked[w]) {
        dfs(g, w);
      }
    }
  }
}
