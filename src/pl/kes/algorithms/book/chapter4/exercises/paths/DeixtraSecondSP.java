package pl.kes.algorithms.book.chapter4.exercises.paths;

import java.util.ArrayList;
import java.util.Stack;
import pl.kes.algorithms.book.chapter4.exercises.mod.IndexMinPQKes;

public class DeixtraSecondSP {

  private DirectedEdge[] edgeTo;
  private double[] distTo;
  private DirectedEdge[] edgeToSecond;
  private double[] distToSecond;
  private IndexMinPQKes<Double> minPQ;

  public DeixtraSecondSP(EdgeWeightedDigraph g, int s) {
    edgeTo = new DirectedEdge[g.V()];
    distTo = new double[g.V()];
    edgeToSecond = new DirectedEdge[g.V()];
    distToSecond = new double[g.V()];
    minPQ = new IndexMinPQKes<>(g.V());
    for (int i = 0; i < g.V(); i++) {
      distTo[i] = Double.POSITIVE_INFINITY; // O(V)
      distToSecond[i] = Double.POSITIVE_INFINITY; // O(V)
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

  public boolean hasSecondPathTo(int v) {
    return distToSecond[v] != Double.POSITIVE_INFINITY;
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

  public Iterable<DirectedEdge> secondPathTo(int v) {
    if (!hasSecondPathTo(v)) {
      return new ArrayList<>();
    }
    final Stack<DirectedEdge> path = new Stack<>();
    int x = v;
    while (edgeToSecond[x] != null || edgeTo[x] != null) {
      if (edgeToSecond[x] != null) {
        path.push(edgeToSecond[x]);
        x = edgeToSecond[x].from();
      } else {
        path.push(edgeTo[x]);
        x = edgeTo[x].from();
      }
    }
    return path;
  }

  private void relax(DirectedEdge e) {
    int v = e.from();
    int w = e.to();
    if (distTo[w] > distTo[v] + e.weight()) {
      edgeToSecond[w] = edgeTo[w];
      distToSecond[w] = distTo[w];
      edgeTo[w] = e;
      distTo[w] = distTo[v] + e.weight();
      if (minPQ.exists(w)) {
        minPQ.update(w, distTo[w]);
      } else {
        minPQ.addElem(w, distTo[w]);
      }
    } else if (distToSecond[w] > distTo[v] + e.weight()) {
      edgeToSecond[w] = e;
      distToSecond[w] = distTo[v] + e.weight();
    }
  }

  private void relax(EdgeWeightedDigraph g, int v) {
    for (DirectedEdge e: g.adj(v)) {
      relax(e); // O(logV)
    }
  }
}
