package pl.kes.algorithms.book.chapter4.exercises.mod;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import edu.princeton.cs.algs4.Bag;

public class EdgeWeightedGraph {

  private int V;
  private int E;
  private List<Edge>[] edges;

  public EdgeWeightedGraph(int V) {
    this.V = V;
    E = 0;
    edges = (List<Edge>[]) new List[V];
    for (int i = 0; i < V; i++) {
      edges[i] = new ArrayList<>();
    }
  }

  public int V() {
    return V;
  }

  public int E() {
    return E;
  }

  public Iterable<Edge> adj(int v) {
    return edges[v];
  }

  public void removeEdge(Edge e) {
    int v = e.either();
    int w = e.other(v);
    Iterator<Edge> edgIt = edges[v].iterator();
    while (edgIt.hasNext()) {
      Edge ed = edgIt.next();
      if (ed.other(v) == w) {
        edgIt.remove();
        break;
      }
    }
    edgIt = edges[w].iterator();
    while (edgIt.hasNext()) {
      Edge ed = edgIt.next();
      if (ed.other(w) == v) {
        edgIt.remove();
        break;
      }
    }
  }

  public Iterable<Edge> edges() {
    Bag<Edge> b = new Bag<>();
    for (int i = 0; i < edges.length; i++) {
      for (Edge e: edges[i]) {
        if (e.other(i) > i) {
          b.add(e);
        }
      }
    }
    return b;
  }

  public void addEdge(Edge e) {
    int v = e.either();
    int w = e.other(v);
    edges[v].add(e);
    edges[w].add(e);
    E++;
  }
}
