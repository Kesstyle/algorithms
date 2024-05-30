package pl.kes.algorithms;

import java.util.HashMap;
import java.util.Map;

public class NodesScore {

    public static void main(String...args) {
        String[] words = new String[] {"abc","ab","bc","b"};
        NodesScore nodesScore = new NodesScore();
        nodesScore.sumPrefixScores(words);
    }

  private Node root;
  private Node[] nodes;

  public int[] sumPrefixScores(String[] words) {
    root = new Node();
    int n = words.length;
    nodes = new Node[n];
    for (int i = 0; i < n; i++) {
      char[] array = words[i].toCharArray();
      Node tmp = root;
      Node node = null;
      for (int j = 0; j < array.length; j++) {
        char c = array[j];
        if (tmp.getNode(c) == null) {
          node = new Node(tmp, c);
          tmp.addNode(c, node);
        } else {
          tmp.getNode(c).rank++;
        }
        tmp = node;
      }
      nodes[i] = tmp;
    }

    int[] result = new int[n];
    for (int i = 0; i < n; i++) {
      result[i] = score(nodes[i]);
    }
    return result;
  }

  private int score(Node n) {
    if (n == root || n == null) {
      return 0;
    }
    if (n.score != 0) {
      return n.score;
    }
    n.score = n.rank + score(n.parent);
    return n.score;
  }

  class Node {
    Node parent;
    Map<Character, Node> children;
    char key;
    int rank;
    int score;

    public Node() {
      children = new HashMap<>();
      rank = 1;
    }

    public Node(Node parent) {
      children = new HashMap<>();
      this.parent = parent;
      rank = 1;
    }

    public Node(Node parent, char key) {
      children = new HashMap<>();
      this.parent = parent;
      rank = 1;
      this.key = key;
    }

    public Node getNode(char c) {
      return children.get(c);
    }

    public void addNode(char c, Node n) {
      children.put(c, n);
    }
  }
}