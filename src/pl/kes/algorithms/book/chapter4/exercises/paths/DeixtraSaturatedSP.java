package pl.kes.algorithms.book.chapter4.exercises.paths;

import java.util.Stack;
import pl.kes.algorithms.book.chapter4.exercises.mod.IndexMinPQKes;

public class DeixtraSaturatedSP {

  private DirectedEdge[] edgeTo;
  private double[] distTo;
  private IndexMinPQKes<Double> minPQ;

  public DeixtraSaturatedSP(EdgeWeightedSaturatedDigraph g, int s) {
    edgeTo = new DirectedEdge[g.V()];
    distTo = new double[g.V()];
    minPQ = new IndexMinPQKes<>(g.V());
    for (int i = 0; i < g.V(); i++) {
      distTo[i] = Double.POSITIVE_INFINITY; // O(V)
    }
    distTo[s] = 0.0;
    minPQ.addElem(s, 0.0); // const
    while (!minPQ.isEmpty()) {
      relax(g, minPQ.getMinIndex()); // O(E log V)
    }
  }

  public boolean hasPathTo(int v) {
    return distTo[v] != Double.POSITIVE_INFINITY;
  }

  public Iterable<DirectedEdge> pathTo(int v) {
    if (!hasPathTo(v)) {
      return null;
    }
    final Stack<DirectedEdge> path = new Stack<>();
    int x = v;
    while (edgeTo[x] != null) {
      path.push(edgeTo[x]);
      x = edgeTo[x].from();
    }
    return path;
  }

  private void relax(int v, int w, double weight) {
    if (distTo[w] > distTo[v] + weight) {
      edgeTo[w] = new DirectedEdge(v, w, weight);
      distTo[w] = distTo[v] + weight;
      if (minPQ.exists(w)) {
        minPQ.update(w, distTo[w]);
      } else {
        minPQ.addElem(w, distTo[w]);
      }
    }
  }

  private void relax(EdgeWeightedSaturatedDigraph g, int v) {
    for (int w: g.adj(v)) {
      relax(v, w, g.weight(v, w)); // O(logV)
    }
  }
}
