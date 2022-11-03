package pl.kes.algorithms.book.chapter4.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import pl.kes.algorithms.book.chapter1.Queue;
import pl.kes.algorithms.book.standard.StdOut;

public class TopologicalOrder {

  private int n;
  private boolean[] marked;
  private Queue<Integer> pre;
  private Queue<Integer> post;
  private Stack<Integer> reverse;
  private DirectedCycle directedCycle;

  public TopologicalOrder(Digraph g) {
    n = g.V();
    pre = new Queue<>();
    post = new Queue<>();
    reverse = new Stack<>();
    marked = new boolean[n];
    directedCycle = new DirectedCycle(g);
    for (int i = 0; i < n; i++) {
      if (!marked[i]) {
        dfs(g, i);
      }
    }
  }

  public Iterable<Integer> topologicalOrder() {
    if (directedCycle.hasCycle()) {
      StdOut.println("There is cycle! Can't proceed with topological:");
      directedCycle.cycle().forEach(s -> StdOut.print(s + ", "));
      StdOut.println();
    }
    List<Integer> list = new ArrayList<>();
    while (!reverse.isEmpty()) {
      list.add(reverse.pop());
    }
    return list;
  }

  public Iterable<Integer> postOrder() {
    List<Integer> list = new ArrayList<>();
    while (!post.isEmpty()) {
      list.add(post.dequeue());
    }
    return list;
  }

  private void dfs(Digraph g, int v) {
    pre.enqueue(v);
    marked[v] = true;
    for (Integer w: g.adj(v)) {
      if (!marked[w]) {
        dfs(g, w);
      }
    }
    post.enqueue(v);
    reverse.push(v);
  }
}
