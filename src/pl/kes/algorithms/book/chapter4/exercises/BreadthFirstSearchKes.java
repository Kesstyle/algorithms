package pl.kes.algorithms.book.chapter4.exercises;

import pl.kes.algorithms.book.chapter1.Queue;

public class BreadthFirstSearchKes {

  private int[] edgeTo;
  private boolean[] marked;
  private int[] distTo;
  private int s;

  public BreadthFirstSearchKes(Graph g, int s) {
    edgeTo = new int[g.V()];
    marked = new boolean[g.V()];
    distTo = new int[g.V()];
    this.s = s;
    bfs(g, s);
  }

  public int distTo(int v) {
    return distTo[v];
  }

  private void bfs(Graph g, int s) {
    Queue<Integer> queue = new Queue<>();
    queue.enqueue(s);
    marked[s] = true;
    while (!queue.isEmpty()) {
      int v = queue.dequeue();
      for (int w : g.adj(v)) {
        if (marked[w]) {
          continue;
        }
        edgeTo[w] = v;
        marked[w] = true;
        distTo[w] = distTo[v] + 1;
        queue.enqueue(w);
      }
    }
  }
}
