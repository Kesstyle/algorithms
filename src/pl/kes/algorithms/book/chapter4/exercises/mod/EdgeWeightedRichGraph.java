package pl.kes.algorithms.book.chapter4.exercises.mod;

import edu.princeton.cs.algs4.Bag;

public class EdgeWeightedRichGraph {

  private int V;
  private int E;
  private double[][] weights;

  public EdgeWeightedRichGraph(int V) {
    this.V = V;
    E = 0;
    weights = new double[V][V];
  }

  public int V() {
    return V;
  }

  public int E() {
    return E;
  }

  public Iterable<Edge> adj(int v) {
    final Bag<Edge> res = new Bag<>();
    for (int i = 0; i < V; i++) {
      if (weights[v][i] != 0.0) {
        res.add(new Edge(v, i, weights[v][i]));
      }
    }
    return res;
  }

  public Iterable<Edge> edges() {
    Bag<Edge> b = new Bag<>();
    for (int i = 0; i < V; i++) {
      for (int j = i + 1; j < V; j++) {
        if (weights[i][j] != 0.0) {
          b.add(new Edge(i, j, weights[i][j]));
        }
      }
    }
    return b;
  }

  public void addEdge(Edge e) {
    int v = e.either();
    int w = e.other(v);
    weights[v][w] = e.weight();
    weights[w][v] = e.weight();
    E++;
  }
}
