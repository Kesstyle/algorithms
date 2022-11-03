package pl.kes.algorithms.book.chapter4.exercises;

public class SearchUF {

  private Graph g;
  private int s;
  private UF uf;

  public SearchUF(Graph g, int s) {
    this.g = g;
    this.s = s;
    uf = new UF(g.V());
    for (int i: g.adj(s)) {
      uf.union(s, i);
    }
  }

  public boolean marked(int v) {
    return uf.connected(s, v);
  }

  public int count() {
    return uf.count(s) - 1;
  }
}
