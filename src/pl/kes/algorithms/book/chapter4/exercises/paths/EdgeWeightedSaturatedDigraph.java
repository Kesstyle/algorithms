package pl.kes.algorithms.book.chapter4.exercises.paths;

import java.util.ArrayList;
import java.util.List;
import edu.princeton.cs.algs4.Bag;
import pl.kes.algorithms.book.standard.In;

public class EdgeWeightedSaturatedDigraph {

  private int V;
  private Double[][] weights;

  public EdgeWeightedSaturatedDigraph(int V) {
    this.V = V;
    weights = new Double[V][V];
  }

  public EdgeWeightedSaturatedDigraph(In in) {
    int V = in.readInt();
    this.V = V;
    weights = new Double[V][V];
    while (in.hasNextLine()) {
      int v = in.readInt();
      int w = in.readInt();
      double wg = in.readDouble();
      addEdge(v, w, wg);
    }
  }

  public int V() {
    return V;
  }

  public void addEdge(int v, int w, double weight) {
    weights[v][w] = weight;
  }

  public Iterable<Integer> adj(int v) {
    final List<Integer> adjVertices = new ArrayList<>();
    for (int i = 0; i < V; i++) {
      if (weights[v][i] != null) {
        adjVertices.add(i);
      }
    }
    return adjVertices;
  }

  public Double weight(int i, int j) {
    return weights[i][j];
  }

  public Iterable<DirectedEdge> edges() {
    final List<DirectedEdge> edgeList = new ArrayList<>();
    for (int i = 0; i < V; i++) {
      for (int j = 0; j < V; j++) {
        if (weights[i][j] != null) {
          edgeList.add(new DirectedEdge(i, j, weights[i][j]));
        }
      }
    }
    return edgeList;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < V; i++) {
      sb.append(i + ": ");
      for (int j = 0; j < V; j++) {
        sb.append(i + " -> " + j + " " + weights[i][j] + " ");
      }
      sb.append("\r\n");
    }
    return sb.toString();
  }
}
