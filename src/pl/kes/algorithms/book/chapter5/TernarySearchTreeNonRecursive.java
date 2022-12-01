package pl.kes.algorithms.book.chapter5;

public class TernarySearchTreeNonRecursive<Value> {

  Node root;

  public Value get(String key) {
    Node x = root;
    int d = 0;
    while (d < key.length()) {
      if (x == null) {
        return null;
      }
      char c = key.charAt(d);
      if (x.c == c) {
        if (d == key.length() - 1) {
          return x.val;
        }
        d++;
        x = x.mid;
      } else if (c < x.c) {
        x = x.left;
      } else {
        x = x.right;
      }
    }
    return null;
  }

  public void put(String key, Value val) {
    Node x = root;
    int d = 0;
    while (d < key.length()) {
      char c = key.charAt(d);
      if (x.c == c) {
        if (d == key.length() - 1) {
          x.val = val;
          return;
        }
        d++;
        if (x.mid == null) {
          x.mid = new Node(key.charAt(d));
        }
        x = x.mid;
      } else if (c < x.c) {
        if (x.left == null) {
          x.left = new Node(c);
        }
        x = x.left;
      } else {
        if (x.right == null) {
          x.right = new Node(c);
        }
        x = x.right;
      }
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
