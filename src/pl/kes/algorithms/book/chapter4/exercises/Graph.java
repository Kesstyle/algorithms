package pl.kes.algorithms.book.chapter4.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import pl.kes.algorithms.book.standard.In;
import pl.kes.algorithms.book.standard.StdOut;

public class Graph {
  private Node[] nodes;
  private final int V;
  private int E;

  public Graph(int V) {
    this.V = V;
    E = 0;
    nodes = new Node[V];
  }

  public Graph(In in) {
    if (in == null) {
      throw new IllegalArgumentException("argument is null");
    }
    try {
      this.V = in.readInt();
      if (V < 0) {
        throw new IllegalArgumentException("number of vertices in a Graph must be non-negative");
      }
      nodes = new Node[V];
      int E = in.readInt();
      if (E < 0) {
        throw new IllegalArgumentException("number of edges in a Graph must be non-negative");
      }
      for (int i = 0; i < E; i++) {
        int v = in.readInt();
        int w = in.readInt();
        validateVertex(v);
        validateVertex(w);
        addEdge(v, w);
      }
    }
    catch (NoSuchElementException e) {
      throw new IllegalArgumentException("invalid input format in Graph constructor", e);
    }
  }

  public Graph(Graph source) {
    V = source.nodes.length;
    this.nodes = new Node[V];
    for (int i = 0; i < V; i++) {
      Node sourceNext = source.nodes[i];
      Node next = null;
      while (sourceNext != null) {
        Node toAdd = new Node(sourceNext.val, null);
        if (nodes[i] == null) {
          nodes[i] = toAdd;
          next = nodes[i];
        } else {
          next.next = toAdd;
          next = next.next;
        }
        sourceNext = sourceNext.next;
      }
    }
  }

  public Iterable<Integer> adj(int v) {
    List<Integer> res = new ArrayList<>();
    Node node = nodes[v];
    while (node != null) {
      res.add(node.val);
      node = node.next;
    }
    return res;
  }

  public int V() {
    return V;
  }

  public int E() {
    return E;
  }

  public boolean hasEdge(int a, int b) {
    if (a < 0 || a >= V || b < 0 || b >= V) {
      throw new RuntimeException("Invalid a or b: " + a + ", " + b);
    }
    Node first = nodes[a];
    Node second = nodes[b];
    if (first == null || second == null) {
      return false;
    }
    while (first.next != null) {
      if (first.val == b) {
        StdOut.println("Edge already exists: " + a + "-" + b);
        return true;
      }
      first = first.next;
    }
    return false;
  }

  public void deleteEdge(int a, int b) {
    if (a < 0 || a >= V || b < 0 || b >= V) {
      throw new RuntimeException("Invalid a or b: " + a + ", " + b);
    }
    if (hasEdge(a, b)) {
      Node af = nodes[a];
      Node afprev = af;
      while (af.val != b) {
        afprev = af;
        af = af.next;
      }
      if (af == nodes[a]) {
        nodes[a] = af.next;
      } else {
        afprev.next = af.next;
      }

      Node bf = nodes[b];
      Node bfprev = bf;
      while (bf.val != a) {
        bfprev = bf;
        bf = bf.next;
      }
      if (bf == nodes[b]) {
        nodes[b] = bf.next;
      } else {
        bfprev.next = bf.next;
      }
    }
  }

  public void addEdge(int a, int b) {
    if (a < 0 || a >= V || b < 0 || b >= V) {
      throw new RuntimeException("Invalid a or b: " + a + ", " + b);
    }
    if (a == b) {
      StdOut.println("Self-loops are not permitted!");
      return;
    }
    if (hasEdge(a, b)) {
      StdOut.println("Edge already exists: " + a + "-" + b);
      return;
    }
    Node first = nodes[a];
    Node second = nodes[b];

    if (first == null) {
      nodes[a] = new Node(b, null);
    } else {
      nodes[a] = new Node(b, nodes[a]);
    }

    if (second == null) {
      nodes[b] = new Node(a, null);
    } else {
      nodes[b] = new Node(a, nodes[b]);
    }
    E++;
    StdOut.println("Added edge: " + a + "-" + b);
  }

  public void print() {
    StdOut.print(toString());
  }

  public String toString() {
    StringBuilder val = new StringBuilder();
    for (int i = 0; i < nodes.length; i++) {
      val.append(i + ": ");
      Node next = nodes[i];
      while (next != null) {
        val.append(next.val + ", ");
        next = next.next;
      }
      val.append("\r\n");
    }
    return val.toString();
  }

  private void validateVertex(int v) {
    if (v < 0 || v >= V) {
      throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }
  }

  class Node {
    int val;
    Node next;

    public Node(int val, Node next) {
      this.val = val;
      this.next = next;
    }
  }
}

