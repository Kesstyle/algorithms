package pl.kes.algorithms.book.chapter4.exercises.paths;

import java.util.ArrayList;
import java.util.List;
import edu.princeton.cs.algs4.Bag;
import pl.kes.algorithms.book.standard.In;

public class EdgeWeightedDigraph {

  private int V;
  private int E;
  private Bag<DirectedEdge>[] edges;

  public EdgeWeightedDigraph(int V) {
    this.V = V;
    this.E = 0;
    edges = (Bag<DirectedEdge>[]) new Bag[V];
    for (int i = 0; i < V; i++) {
      edges[i] = new Bag<>();
    }
  }

  public EdgeWeightedDigraph(In in) {
    int V = in.readInt();
    this.V = V;
    this.E = 0;
    edges = (Bag<DirectedEdge>[]) new Bag[V];
    for (int i = 0; i < V; i++) {
      edges[i] = new Bag<>();
    }
    while (in.hasNextLine()) {
      int v = in.readInt();
      int w = in.readInt();
      double wg = in.readDouble();
      addEdge(new DirectedEdge(v, w, wg));
    }
  }

  public int V() {
    return V;
  }

  public int E() {
    return E;
  }

  public void addEdge(DirectedEdge e) {
    E++;
    edges[e.from()].add(e);
  }

  public Iterable<DirectedEdge> adj(int v) {
    return edges[v];
  }

  public Iterable<DirectedEdge> edges() {
    final List<DirectedEdge> edgeList = new ArrayList<>();
    for (int i = 0; i < V; i++) {
      for (DirectedEdge e: edges[i]) {
        edgeList.add(e);
      }
    }
    return edgeList;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < V; i++) {
      sb.append(i + ": ");
      for (DirectedEdge directedEdge: edges[i]) {
        sb.append(directedEdge + " ");
      }
      sb.append("\r\n");
    }
    return sb.toString();
  }
}
