package pl.kes.algorithms.book.chapter4.exercises.mod;

public class Edge implements Comparable<Edge> {

  private int v;
  private int w;
  private double weight;

  public Edge(int v, int w, double weight) {
    this.v = v;
    this.w = w;
    this.weight = weight;
  }

  public double weight() {
    return weight;
  }

  public int either() {
    return v;
  }

  public int other(int x) {
    if (x == v) {
      return w;
    }
    if (x == w) {
      return v;
    }
    throw new IllegalArgumentException(x+ " is not allow for the edge " + v + " " + w);
  }

  public String toString() {
    return String.format("%d-%d %.2f", v, w, weight);
  }

  @Override
  public int compareTo(Edge e) {
    if (weight < e.weight) {
      return -1;
    }
    if (weight > e.weight) {
      return 1;
    }
    return 0;
  }

  public boolean equals(Edge e) {
    if (weight != e.weight) {
      return false;
    }
    int v = this.either();
    int w = this.other(v);
    int v1 = e.either();
    if (v1 == v && e.other(v) == w) {
      return true;
    }
    if (v1 == w && e.other(w) == v) {
      return true;
    }
    return false;
  }
}
