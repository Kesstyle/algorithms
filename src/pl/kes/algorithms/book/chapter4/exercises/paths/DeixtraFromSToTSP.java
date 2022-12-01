package pl.kes.algorithms.book.chapter4.exercises.paths;

import java.util.Stack;
import pl.kes.algorithms.book.chapter4.exercises.mod.IndexMinPQKes;

public class DeixtraFromSToTSP {

  private DirectedEdge[] edgeTo;
  private double[] distTo;
  private IndexMinPQKes<Double> minPQ;
  private int shortestFromT = -1;

  public DeixtraFromSToTSP(EdgeWeightedDigraph g, int[] sources, int[] sinks) {
    boolean[] isSink = new boolean[g.V()];
    for (int i = 0; i < sinks.length; i++) {
      isSink[sinks[i]] = true;
    }
    edgeTo = new DirectedEdge[g.V()];
    distTo = new double[g.V()];
    minPQ = new IndexMinPQKes<>(g.V());
    for (int i = 0; i < g.V(); i++) {
      distTo[i] = Double.POSITIVE_INFINITY; // O(V)
    }
    for (int i: sources) {
      distTo[i] = 0.0;
      minPQ.addElem(i, 0.0); // const
    }

    while (!minPQ.isEmpty()) {
      int next = minPQ.getMinIndex();
      if (isSink[next]) {
        shortestFromT = next;
        return;
      }
      relax(g, next); // O(E log V)
    }
  }

  public Iterable<DirectedEdge> pathToSink() {
    return pathTo(shortestFromT);
  }

  public boolean hasPathToSink() {
    return hasPathTo(shortestFromT);
  }

  private Iterable<DirectedEdge> pathTo(int v) {
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

  private boolean hasPathTo(int v) {
    return distTo[v] != Double.POSITIVE_INFINITY;
  }

  private void relax(DirectedEdge e) {
    int v = e.from();
    int w = e.to();
    if (distTo[w] > distTo[v] + e.weight()) {
      edgeTo[w] = e;
      distTo[w] = distTo[v] + e.weight();
      if (minPQ.exists(w)) {
        minPQ.update(w, distTo[w]);
      } else {
        minPQ.addElem(w, distTo[w]);
      }
    }
  }

  private void relax(EdgeWeightedDigraph g, int v) {
    for (DirectedEdge e: g.adj(v)) {
      relax(e); // O(logV)
    }
  }
}
