package pl.kes.algorithms.book.chapter4.exercises;

public class SearchDFS {

  private boolean[] marked;
  private int count;
  private int canBeDeleted = -1;

  public SearchDFS(Graph g, int s) {
    marked = new boolean[g.V()];
    dfs(g, s);
  }

  public boolean marked(int v) {
    return marked[v];
  }

  public int count() {
    return count;
  }

  private void dfs(Graph g, int v) {
    marked[v] = true;
    count++;
    boolean isFreeToRemove = true;
    for (int i: g.adj(v)) {
      if (!marked(i)) {
        dfs(g, i);
        isFreeToRemove = false;
      }
    }
    if (isFreeToRemove) {
      canBeDeleted = v;
    }
  }
}
