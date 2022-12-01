package pl.kes.algorithms.book.chapter5;

public class TernarySearchTree<Value> {

  Node root;

  public Value get(String key) {
    Node x = get(root, key, 0);
    if (x == null) {
      return null;
    }
    return x.val;
  }

  public void put(String key, Value val) {
    put(root, key, val, 0);
  }

  private Node put(Node x, String key, Value val, int d) {
    char c = key.charAt(d);
    if (x == null) {
      x = new Node(c);
    }
    if (c < x.c) {
      x.left = put(x.left, key, val, d);
    } else if (c > x.c) {
      x.right = put(x.right, key, val, d);
    } else if (key.length() > d + 1) {
      x.mid = put(x.mid, key, val, d + 1);
    } else {
      x.val = val;
    }
    return x;
  }

  private Node get(Node x, String key, int d) {
    if (x == null) {
      return null;
    }

    char c = key.charAt(d);
    if (x.c == c) {
      if (key.length() <= d + 1) {
        return x;
      }
      return get(x.mid, key, d + 1);
    } else if (c < x.c) {
      return get(x.left, key, d);
    } else {
      return get(x.right, key, d);
    }
  }

  class Node {

    char c;
    Value val;
    Node left, mid, right;

    public Node(char c) {
      this.c = c;
    }

    public Node(char c, Value val) {
      this.c = c;
      this.val = val;
    }

    public Node(char c, Value val, Node left, Node mid, Node right) {
      this.c = c;
      this.val = val;
      this.left = left;
      this.mid = mid;
      this.right = right;
    }
  }
}
