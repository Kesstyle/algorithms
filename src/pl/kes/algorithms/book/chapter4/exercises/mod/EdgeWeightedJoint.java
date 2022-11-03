package pl.kes.algorithms.book.chapter4.exercises.mod;

public class EdgeWeightedJoint {

  private boolean[] marked;
  private boolean joint;

  public EdgeWeightedJoint(EdgeWeightedGraph e) {
    marked = new boolean[e.V()];
    joint = true;
    for (int i = 0; i < e.V(); i++) {
      if (!marked[i]) {
        if (i > 0) {
          joint = false;
          break;
        }
        dfs(e, i);
      }
    }
  }

  public boolean isJoint() {
    return joint;
  }

  private void dfs(EdgeWeightedGraph g, int v) {
    marked[v] = true;
    for (Edge e: g.adj(v)) {
      int w = e.other(v);
      if (!marked[w]) {
        dfs(g, w);
      }
    }
  }
}
