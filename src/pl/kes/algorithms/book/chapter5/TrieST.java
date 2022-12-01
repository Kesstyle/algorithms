package pl.kes.algorithms.book.chapter5;

import edu.princeton.cs.algs4.Queue;

public class TrieST<Value> {

  private static int R = 'z' - 'a' + 1;
  private Node root;

  public TrieST() {
    root = new Node();
  }

  public void put(String key, Value val) {
    put(root, key, 0, val);
  }

  public Value get(String key) {
    Node x = get(root, key, 0);
    if (x == null) {
      return null;
    }
    return (Value) x.val;
  }

  public void delete(String key) {
    root = delete(root, key, 0);
  }

  public boolean contains(String key) {
    return get(key) != null;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public String longestPrefixOf(String s) {
    int position = searchLongestPrefixLength(root, s, 0, 0);
    return s.substring(0, position);
  }

  public Iterable<String> keysWithPrefix(String s) {
    Node x = get(root, s, 0);
    Queue<String> queue = new Queue<>();
    collect(x, s, queue);
    return queue;
  }

  public Iterable<String> keysThatMatch(String s) {
    Queue<String> queue = new Queue<>();
    collect(root, "", s, queue);
    return queue;
  }

  public int size() {
    return size(root);
  }

  public Iterable<String> keys() {
    return keysWithPrefix("");
  }

  public String floor(String s) {
    return floor(root, s, "", 0, true);
  }

  public String ceil(String s) {
    return ceil(root, s, "", 0, true);
  }

  private String ceil(Node x, String s, String pre, int d, boolean exact) {
    if (x == null) {
      return null;
    }
    String max = null;
    if (x.val != null) {
      max = pre;
    }
    if (exact && d == s.length()) {
      return max;
    }
    int minC = exact ? index(s.charAt(d)) : 0;
    for (int i = minC; i < R; i++) {
      String tmp = ceil(x.nodes[i], s, pre + ch(i), d + 1, exact && ch(i) == s.charAt(d));
      if (tmp != null) {
        return tmp;
      }
    }
    if (exact && d < s.length()) {
      return null; // now < nows
    }
    return max;
  }

  public int rank(String s) {
    return rank(root, s, 0);
  }

  public String select(int rank) {
    return select(root, "", rank);
  }

  private String select(Node x, String pre, int rank) {
    if (x == null) {
      return null;
    }
    if (rank == 0 && size(x) == 1) {
      return keysWithPrefix(pre).iterator().next();
    }

    int tmp = 0;
    for (int i = 0; i < R; i++) {
      int size = size(x.nodes[i]);
      if (tmp + size <= rank) {
        tmp += size;
      } else {
        return select(x.nodes[i], pre + ch(i), rank - tmp);
      }
    }
    return null;
  }

  private int rank(Node x, String s, int d) {
    if (x == null) {
      return 0;
    }
    if (d >= s.length()) {
      return 0;
    }
    int index = index(s.charAt(d));
    int rank = 0;
    for (int i = 0; i < index; i++) {
      rank += size(x.nodes[i]);
    }
    return rank + rank(x.nodes[index], s, d + 1);
  }

  private String floor(Node x, String s, String pre, int d, boolean exact) {
    if (x == null) {
      return null;
    }
    String max = null;
    if (x.val != null) {
      max = pre;
    }
    if (d == s.length() && exact) {
      return max;
    }
    int maxC = exact ? index(s.charAt(d)) : R - 1;
    for (int i = maxC; i >= 0; i--) {
      String tmp = floor(x.nodes[i], s, pre + ch(i), d + 1, exact && ch(i) == s.charAt(d));
      if (tmp != null) {
        return tmp;
      }
    }
    return max;
  }

  private Node delete(Node x, String key, int d) {
    if (x == null) {
      return null;
    }
    if (d == key.length()) {
      if (x.val != null) {
        x.size--;
      }
      x.val = null;
    } else {
      int c = index(key.charAt(d));
      int formerSize = size(x.nodes[c]);
      x.nodes[c] = delete(x.nodes[c], key, d + 1);
      x.size += size(x.nodes[c]) - formerSize;
    }
    if (x.val != null) {
      return x;
    }
    for (int i = 0; i < x.nodes.length; i++) {
      if (x.nodes[i] != null) {
        return x;
      }
    }
    return null;
  }

  private int searchLongestPrefixLength(Node x, String s, int d, int length) {
    if (x == null) {
      return length;
    }
    if (x.val != null) {
      length = d;
    }
    if (d == s.length()) {
      return length;
    }
    return searchLongestPrefixLength(x.nodes[index(s.charAt(d))], s, d + 1, length);
  }

  private void collect(Node x, String pre, String pat, Queue<String> strs) {
    if (x == null) {
      return;
    }
    int d = pre.length();
    if (x.val != null && d == pat.length()) {
      strs.enqueue(pre);
    }
    if (d == pat.length()) {
      return;
    }
    char next = pat.charAt(d);
    if (next == '.') {
      for (int c = 0; c < R; c++) {
        collect(x.nodes[c], pre + ch(c), pat, strs);
      }
    } else {
      collect(x.nodes[ch(next)], pre + next, pat, strs);
    }

  }

  private void collect(Node x, String pre, Queue<String> strs) {
    if (x == null) {
      return;
    }
    if (x.val != null) {
      strs.enqueue(pre);
    }
    for (int c = 0; c < R; c++) {
      collect(x.nodes[c], pre + ch(c), strs);
    }
  }

  private int size(Node x) {
    if (x == null) {
      return 0;
    }
    return x.size;
  }

  private Node put(Node x, String key, int d, Value val) {
    if (d == key.length()) {
      if (x.val == null) {
        x.size++;
      }
      x.val = val;
      return x;
    }
    int index = index(key.charAt(d));
    int formerSize = size(x.nodes[index]);
    if (x.nodes[index] == null) {
      x.nodes[index] = new Node();
    }
    Node res = put(x.nodes[index], key, d + 1, val);
    x.size += size(x.nodes[index]) - formerSize;
    return res;
  }

  private Node get(Node x, String key, int d) {
    if (x == null) {
      return null;
    }
    if (d == key.length()) {
      return x;
    }
    int cKey = index(key.charAt(d));
    return get(x.nodes[cKey], key, d + 1);
  }

  private int index(char c) {
    return c - 'a';
  }

  private int index(String s, int d) {
    if (d >= s.length()) {
      return -1;
    }
    return index(s.charAt(d));
  }

  private char ch(int index) {
    return (char) ('a' + index);
  }

  class Node<Value> {
    Value val;
    Node<Value>[] nodes;
    int size;

    public Node() {
      this.nodes = new Node[R];
    }

    public Node(Value val) {
      this.val = val;
      this.nodes = new Node[R];
    }

    public Node(Value val, Node<Value>[] nodes) {
      this.val = val;
      this.nodes = nodes;
    }
  }
}
