package pl.kes.algorithms.book.chapter4.exercises.paths;

import java.util.Stack;

public class AcyclicLP {

  private DirectedEdge[] edgeTo;
  private double[] distTo;

  public AcyclicLP(EdgeWeightedDigraph g, int s) {
    edgeTo = new DirectedEdge[g.V()];
    distTo = new double[g.V()];
    for (int i = 0; i < g.V(); i++) {
      distTo[i] = Double.NEGATIVE_INFINITY; // O(V)
    }
    distTo[s] = 0.0;
    EdgeWeightedDigraphTopological topo = new EdgeWeightedDigraphTopological(g);
    Iterable<Integer> topological = topo.topological();
    for (int top: topological) {
      relax(g, top); // O(E log V)
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

  public double distTo(int v) {
    double res = 0.0;
    Iterable<DirectedEdge> edges = pathTo(v);
    if (edges != null) {
      for (DirectedEdge de: edges) {
        res += de.weight();
      }
    }
    return res;
  }

  private void relax(DirectedEdge e) {
    int v = e.from();
    int w = e.to();
    if (distTo[w] < distTo[v] + e.weight()) {
      edgeTo[w] = e;
      distTo[w] = distTo[v] + e.weight();
    }
  }

  private void relax(EdgeWeightedDigraph g, int v) {
    for (DirectedEdge e: g.adj(v)) {
      relax(e); // O(logV)
    }
  }
}
