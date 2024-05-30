package pl.kes.algorithms.book.chapter4.exercises;

import java.util.ArrayList;
import java.util.List;

public class DirectedDFSKes {

  private boolean[] marked;
  private int source;

  public DirectedDFSKes(Digraph g, int v) {
    marked = new boolean[g.V()];
    source = v;
    dfs(g, v);
  }

  public DirectedDFSKes(Digraph g, Iterable<Integer> vv) {
    marked = new boolean[g.V()];
    for (Integer v: vv) {
      dfs(g, v);
    }
  }

  public Iterable<Integer> allMarkedExceptSource() {
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < marked.length; i++) {
      if (marked[i] && i != source) {
        res.add(i);
      }
    }
    return res;
  }

  public Iterable<Integer> allMarked() {
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < marked.length; i++) {
      if (marked[i]) {
        res.add(i);
      }
    }
    return res;
  }

  private void dfs(Digraph g, int v) {
    marked[v] = true;
    for (Integer w: g.adj(v)) {
      if (!marked[w]) {
        dfs(g, w);
      }
    }
  }
}
