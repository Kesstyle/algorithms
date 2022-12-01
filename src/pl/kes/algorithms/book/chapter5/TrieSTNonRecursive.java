package pl.kes.algorithms.book.chapter5;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import edu.princeton.cs.algs4.Queue;

public class TrieSTNonRecursive<Value> {


  private static int R = 'z' - 'a' + 1;
  private Node root;

  public TrieSTNonRecursive() {
    root = new Node();
  }

  public void put(String key, Value val) {
    Node x = root;
    for (int d = 0; d < key.length(); d++) {
      x.size++;
      char c = key.charAt(d);
      int idx = index(c);
      if (x.nodes[idx] == null) {
        x.nodes[idx] = new Node(c);
      }
      x = x.nodes[idx];
      if (d == key.length() - 1) {
        x.val = val;
      }
    }
  }

  public Value get(String key) {
    Node x = getNodeForKey(key);
    if (x == null) {
      return null;
    }
    return (Value) x.val;
  }

  public void delete(String key) {
    Stack<Node> nodes = new Stack<>();
    Stack<Integer> indexes = new Stack<>();
    Node x = root;
    for (int d = 0; d < key.length(); d++) {
      char c = key.charAt(d);
      int idx = index(c);
      if (x.nodes[idx] == null) {
        return;
      }
      nodes.push(x);
      indexes.push(idx);
      x = x.nodes[idx];
    }
    x.val = null;
    boolean toDelete = true;
    for (int i = 0; i < R; i++) {
      if (x.nodes[i] != null) {
        toDelete = false;
        break;
      }
    }
    if (!toDelete) {
      return;
    }
    while (toDelete && !nodes.isEmpty()) {
      x = nodes.pop();
      int idx = indexes.pop();
      x.nodes[idx] = null;
      for (int i = 0; i < R; i++) {
        if (x.nodes[i] != null) {
          toDelete = false;
          break;
        }
      }
    }
  }

  public boolean contains(String key) {
    return get(key) != null;
  }

  public boolean isEmpty() {
    return size() == 1; // root element always presents
  }

  public String longestPrefixOf(String s) {
    Node x = root;
    String longest = "";
    String pre = "";
    for (int d = 0; d < s.length(); d++) {
      char c = s.charAt(d);
      int idx = index(c);
      pre = pre + c;
      if (x.nodes[idx] == null) {
        return longest;
      }
      x = x.nodes[idx];
      if (x.val != null) {
        longest = pre;
      }
    }
    return longest;
  }

  public Iterable<String> keysWithPrefix(String s) {
    List<String> res = new ArrayList<>();
    Node x = getNodeForKey(s);
    if (x == null) {
      return res;
    }
    String pre = s;
    Queue<Node> nodesToAdd = new Queue<>();
    Queue<String> preStrs = new Queue<>();
    nodesToAdd.enqueue(x);
    preStrs.enqueue(pre);
    while (!nodesToAdd.isEmpty()) {
      x = nodesToAdd.dequeue();
      pre = preStrs.dequeue();
      if (x.val != null) {
        res.add(pre);
      }
      for (int i = 0; i < R; i++) {
        if (x.nodes[i] != null) {
          nodesToAdd.enqueue(x.nodes[i]);
          preStrs.enqueue(pre + charFromIdx(i));
        }
      }
    }
    return res;
  }

  public Iterable<String> keysThatMatch(String s) {
    Queue<Node> nodesToAdd = new Queue<>();
    Queue<String> preStrs = new Queue<>();
    List<String> res = new ArrayList<>();
    nodesToAdd.enqueue(root);
    preStrs.enqueue("");
    while (!nodesToAdd.isEmpty()) {
      Node x = nodesToAdd.dequeue();
      String pre = preStrs.dequeue();
      int d = pre.length();
      if (d == s.length()) {
        res.add(pre);
        continue;
      }
      char c = s.charAt(d);
      if (c == '.') {
        for (int i = 0; i < R; i++) {
          if (x.nodes[i] != null) {
            nodesToAdd.enqueue(x.nodes[i]);
            preStrs.enqueue(pre + charFromIdx(i));
          }
        }
      } else {
        int idx = index(c);
        if (x.nodes[idx] != null) {
          nodesToAdd.enqueue(x.nodes[idx]);
          preStrs.enqueue(pre + charFromIdx(idx));
        }
      }
    }
    return res;
  }

  public int size() {
    return size(root);
  }

  public Iterable<String> keys() {
    return keysWithPrefix("");
  }

  private int size(Node x) {
    if (x == null) {
      return 0;
    }
    return x.size;
  }

  private Node getNodeForKey(String key) {
    Node x = root;
    for (int d = 0; d < key.length(); d++) {
      char c = key.charAt(d);
      int idx = index(c);
      x = x.nodes[idx];
      if (x == null) {
        return null;
      }
      if (d == key.length() - 1) {
        return x;
      }
    }
    return null;
  }

  private int index(char c) {
    return c - 'a';
  }

  private char charFromIdx(int idx) {
    return (char) ('a' + idx);
  }

  class Node<Value> {
    Value val;
    Node<Value>[] nodes;
    int size = 1;

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
