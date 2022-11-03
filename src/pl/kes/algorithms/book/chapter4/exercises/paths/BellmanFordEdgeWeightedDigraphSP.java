package pl.kes.algorithms.book.chapter4.exercises.paths;

import java.util.Stack;
import edu.princeton.cs.algs4.Queue;

public class BellmanFordEdgeWeightedDigraphSP {

  private DirectedEdge[] edgeTo;
  private double[] distTo;
  private Queue<Integer> vq;
  private boolean[] inQ;
  private Iterable<DirectedEdge> negativeCycle;
  private int cost;
  private boolean hasNegativeCycle = false;

  public BellmanFordEdgeWeightedDigraphSP(EdgeWeightedDigraph g, int s) {
    edgeTo = new DirectedEdge[g.V()];
    distTo = new double[g.V()];
    inQ = new boolean[g.V()];
    vq = new Queue<>();
    for (int i = 0; i < g.V(); i++) {
      distTo[i] = Double.POSITIVE_INFINITY; // O(V)
    }
    distTo[s] = 0.0;
    vq.enqueue(s);
    inQ[s] = true;
    while (!hasNegativeCycle && !vq.isEmpty()) {
      int v = vq.dequeue();
      inQ[v] = false;
      relax(g, v); // O(E log V)
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

  public Iterable<DirectedEdge> negativeCycle() {
    return negativeCycle;
  }

  private void relax(DirectedEdge e) {
    int v = e.from();
    int w = e.to();
    if (distTo[w] > distTo[v] + e.weight()) {
      edgeTo[w] = e;
      distTo[w] = distTo[v] + e.weight();
      if (!inQ[w]) {
        vq.enqueue(w);
        inQ[w] = true;
      }
    }
  }

  private void findNegativeCycle() {
    EdgeWeightedDigraph g = new EdgeWeightedDigraph(inQ.length);
    for (DirectedEdge edge: edgeTo) {
      if (edge != null) {
        g.addEdge(edge);
      }
    }
    EdgeWeightedCycleFinder ewcf = new EdgeWeightedCycleFinder(g);
    negativeCycle = ewcf.cycle();
    if (negativeCycle != null) {
      hasNegativeCycle = true;
    }
  }

  private void relax(EdgeWeightedDigraph g, int v) {
    for (DirectedEdge e: g.adj(v)) {
      relax(e); // O(logV)
    }
    if (cost++ % g.V() == 0 && cost != 1) {
      findNegativeCycle();
    }
  }
}
