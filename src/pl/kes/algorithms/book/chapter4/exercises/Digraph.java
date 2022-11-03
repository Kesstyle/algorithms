package pl.kes.algorithms.book.chapter4.exercises;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import edu.princeton.cs.algs4.Bag;

public class Digraph {

  private int V;
  private int E;
  private Bag<Integer>[] bags;

  public Digraph(int V) {
    this.V = V;
    this.E = 0;
    bags = (Bag<Integer>[]) new Bag[V];
    for (int i = 0; i < V; i++) {
      bags[i] = new Bag<>();
    }
  }

  public Digraph(Digraph source) {
    this.V = source.V();
    this.E = 0;
    bags = (Bag<Integer>[]) new Bag[V];
    for (int i = 0; i < V; i++) {
      bags[i] = new Bag<>();
      for (Integer v: source.adj(i)) {
        bags[i].add(v);
      }
    }
  }

  public int V() {
    return V;
  }

  public int E() {
    return E;
  }

  public void addEdge(int v, int w) {
    if (v < 0 || w < 0 || v >= V || w >= V) {
      throw new IllegalArgumentException("addEdge: v or w are not valid");
    }
    if (v == w) {
      throw new IllegalArgumentException("addEdge: v cannot be equal to w");
    }
    for (Integer i: adj(v)) {
      if (i == w) {
        throw new IllegalArgumentException("addEdge: parallel edges are not allowed");
      }
    }
    bags[v].add(w);
    E++;
  }

  public Iterable<Integer> adj(int v) {
    if (v < 0  || v >= V ) {
      throw new IllegalArgumentException("adj: v is not valid");
    }
    final List<Integer> list = new ArrayList<>();
    Bag<Integer> bag = bags[v];
    Iterator<Integer> iterator = bag.iterator();
    while (iterator.hasNext()) {
      list.add(iterator.next());
    }
    return list;
  }

  public boolean isTopology(final Iterable<Integer> seq) {
    boolean[] marked = new boolean[V];
    for (Integer s: seq) {
      marked[s] = true;
      for (Integer w: adj(s)) {
        if (marked[w]) {
          return false;
        }
      }
    }
    return true;
  }

  public Digraph reverse() {
    final Digraph reversed = new Digraph(V());
    for (int i = 0; i < V(); i++) {
      for (Integer w: adj(i)) {
        reversed.addEdge(w, i);
      }
    }
    return reversed;
  }

  public boolean hasEdge(int v, int w) {
    if (v < 0 || w < 0 || v >= V || w >= V) {
      throw new IllegalArgumentException("hasEdge: v or w are not valid");
    }
    for (Integer i: adj(v)) {
      if (i == w) {
        return true;
      }
    }
    return false;
  }

  public String toString() {
    final StringBuilder res = new StringBuilder();
    for (int i = 0; i < V; i++) {
      res.append(i + ": ");
      for (Integer v: adj(i)) {
        res.append(v + ", ");
      }
      res.append("\r\n");
    }
    return res.toString();
  }
}
