package pl.kes.algorithms.book.chapter4.exercises.mod;

import edu.princeton.cs.algs4.Queue;

public class MSTByRemoval {

  private Iterable<Edge> mst;
  private MaxPQ<Edge> pq;

  public MSTByRemoval(EdgeWeightedGraph e) {
    pq = new MaxPQ<>(e.E());
    for (Edge edge: e.edges()) {
      pq.addElem(edge);
    }
    while (!pq.isEmpty()) {
      Edge ed = pq.getMax();
      EdgeWeightedGraph eg1 = new EdgeWeightedGraph(e.V());
      for (Edge edge: e.edges()) {
        if (!edge.equals(ed)) {
          eg1.addEdge(edge);
        }
      }
      EdgeWeightedJoint edgeWeightedJoint = new EdgeWeightedJoint(eg1);
      if (edgeWeightedJoint.isJoint()) {
        e.removeEdge(ed);
      }
    }
    mst = e.edges();
  }

  public Iterable<Edge> edges() {
    return mst;
  }

}
