package pl.kes.algorithms.book.chapter4.exercises;

import pl.kes.algorithms.book.chapter1.Queue;

public class GraphProperties {

  private int[] distTo;
  private int[] edgeTo;
  private boolean[] marked;
  private int source;
  private int[] eccentricities;
  private int diameter;
  private int radius;
  private int center;
  private int loop;

  public GraphProperties(Graph g) {
    this.distTo = new int[g.V()];
    this.marked = new boolean[g.V()];
    this.edgeTo = new int[g.V()];
    this.eccentricities = new int[g.V()];
    loop = Integer.MAX_VALUE;

    for (int i = 0; i < g.V(); i++) {
      this.source = i;
      bfs(g, i);

      int maxDist = Integer.MIN_VALUE;
      for (int j = 0; j < distTo.length; j++) {
        if (distTo[j] > maxDist) {
          maxDist = distTo[j];
        }
      }
      eccentricities[i] = maxDist;

      this.distTo = new int[g.V()];
      this.marked = new boolean[g.V()];
    }

    radius = Integer.MAX_VALUE;
    diameter = Integer.MIN_VALUE;
    for (int i = 0; i < eccentricities.length; i++) {
      if (radius > eccentricities[i]) {
        center = i;
        radius = eccentricities[i];
      }
      if (diameter < eccentricities[i]) {
        diameter = eccentricities[i];
      }
    }
  }

  public int eccentricity(int v) {
    return eccentricities[v];
  }

  public int diameter() {
    return diameter;
  }

  public int radius() {
    return radius;
  }

  public int center() {
    return center;
  }

  public int girth() {
    return loop;
  }

  private void bfs(Graph g, int s) {
    marked[s] = true;
    final Queue<Integer> queue = new Queue<>();
    queue.enqueue(s);
    while (!queue.isEmpty()) {
      int v = queue.dequeue();
      for (int w: g.adj(v)) {
        if (marked[w]) {
          if (edgeTo[v] != w) {
            int length = distTo[w] + distTo[v] + 1;
            if (length < loop) {
              loop = length;
            }
          }
          continue;
        }
        distTo[w] = distTo[v] + 1;
        marked[w] = true;
        edgeTo[w] = v;
        queue.enqueue(w);
      }
    }
  }
}
