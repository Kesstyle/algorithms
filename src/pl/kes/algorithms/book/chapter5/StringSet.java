package pl.kes.algorithms.book.chapter5;

public class StringSet {

  private static final int R = 'z' - 'a' + 1;

  private Node root;

  public StringSet() {
    root = new Node();
  }

  public void add(String key) {
    Node x = root;
    for (int d = 0; d < key.length(); d++) {
      char c = key.charAt(d);
      int idx = index(c);
      if (x.nodes[idx] == null) {
        x.nodes[idx] = new Node();
      }
      x.size++;
      x = x.nodes[idx];
    }
    x.active = true;
  }

  public void delete(String key) {
    delete(root, key, 0);
  }

  public boolean contains(String key) {
    Node x = root;
    for (int d = 0; d < key.length(); d++) {
      char c = key.charAt(d);
      int idx = index(c);
      if (x.nodes[idx] == null) {
        return false;
      }
      x = x.nodes[idx];
    }
    return x.active;
  }

  public boolean isEmpty() {
    return size() == 1;
  }

  public int size() {
    return size(root);
  }

  public String toString() {
    return "str";
  }

  private int size(Node x) {
    if (x == null) {
      return 0;
    }
    return x.size;
  }

  private Node delete(Node x, String key, int d) {
    if (x == null) {
      return null;
    }
    if (d < key.length()) {
      char c = key.charAt(d);
      int idx = index(c);
      int formerSize = size(x.nodes[idx]);
      x.nodes[idx] = delete(x.nodes[idx], key, d + 1);
      x.size = x.size - formerSize + size(x.nodes[idx]);
    } else {
      x.active = false;
    }

    if (x.active) {
      return x;
    }
    for (int i = 0; i < R; i++) {
      if (x.nodes[i] != null) {
        return x;
      }
    }
    return null;
  }

  private int index(char c) {
    return c - 'a';
  }

  class Node {

    boolean active;
    Node[] nodes;
    int size;

    public Node() {
      this.active = false;
      nodes = new Node[R];
      size = 1;
    }

    public Node(final boolean active) {
      this.active = active;
      nodes = new Node[R];
    }
  }
}
