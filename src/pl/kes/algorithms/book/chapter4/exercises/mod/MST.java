package pl.kes.algorithms.book.chapter4.exercises.mod;

import pl.kes.algorithms.book.chapter1.Queue;

public class MST {

  private boolean[] marked;
  private Queue<Edge> edges;
  private MinPQ<Edge> minPQ;
  private double weight;

  public MST(EdgeWeightedGraph g) {
    minPQ = new MinPQ<>(g.E());
    marked = new boolean[g.V()];
    edges = new Queue<>();

    visit(g, 0);
    while (!minPQ.isEmpty()) {
      Edge e = minPQ.getMin();
      int v = e.either();
      int w = e.other(v);
      if (marked[v] && marked[w]) {
        continue;
      }
      edges.enqueue(e);
      weight += e.weight();
      if (!marked[v]) {
        visit(g, v);
      }
      if (!marked[w]) {
        visit(g, w);
      }
    }
  }

  public Iterable<Edge> edges() {
    return edges;
  }

  public double weight() {
    return weight;
  }

  private void visit(EdgeWeightedGraph g, int v) {
    marked[v] = true;
    for (Edge e: g.adj(v)) {
      if (!marked[e.other(v)]) {
        minPQ.addElem(e);
      }
    }
  }


}
