package pl.kes.algorithms.book.chapter4.exercises.mod;

import edu.princeton.cs.algs4.Bag;
import pl.kes.algorithms.book.chapter2.IndexMinPQ;

public class PrimMST {

  private Edge[] edge;
  private double[] dist;
  private IndexMinPQKes<Double> mq;
  private boolean[] marked;

  public PrimMST(EdgeWeightedGraph e) {
    edge = new Edge[e.V()];
    dist = new double[e.V()];
    mq = new IndexMinPQKes<>(e.V());
    marked = new boolean[e.V()];

    for (int i = 0; i < dist.length; i++) {
      dist[i] = Double.POSITIVE_INFINITY;
    }
    mq.addElem(0, 0.0);
    while (!mq.isEmpty()) {
      visit(e, mq.getMinIndex());
    }
  }

  public Iterable<Edge> edges() {
    Bag<Edge> bag = new Bag<>();
    for (int i = 0; i < edge.length; i++) {
      if (edge[i] != null) {
        bag.add(edge[i]);
      }
    }
    return bag;
  }

  private void visit(EdgeWeightedGraph g, int v) {
    marked[v] = true;
    for (Edge e: g.adj(v)) {
      int w = e.other(v);
      if (marked[w]) {
        continue;
      }
      if (dist[w] > e.weight()) {
        dist[w] = e.weight();
        edge[w] = e;
        if (mq.exists(w)) {
          mq.update(w, e.weight());
        } else {
          mq.addElem(w, e.weight());
        }
      }
    }
  }
}
