package pl.kes.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MaximumPathSum {

  public static void main(String...args) {
    MaximumPathSum maximumPathSum = new MaximumPathSum();
    TreeNode root = new TreeNode(5);
    root.left = new TreeNode(4);
    root.right = new TreeNode(8);
    root.left.left = new TreeNode(11);
    root.right.left = new TreeNode(13);
    root.right.right = new TreeNode(4);
    root.left.left.left = new TreeNode(7);
    root.left.left.right = new TreeNode(2);
    root.right.left.right = new TreeNode(1);

    int res = maximumPathSum.maxPathSum(root);
    System.out.println(res);
  }

  int max = Integer.MIN_VALUE;

  public int maxPathSum(TreeNode root) {
    calc(root);
    return max;
  }

  private Pair calc(TreeNode root) {
    if (root == null) {
      return new Pair(0, 0);
    }
    int prevLeftB = calc(root.left).b;
    int prevRightB = calc(root.right).b;
    int a = Math.max(0, prevLeftB) + Math.max(0, prevRightB) + root.val;
    if (max < a) {
      max = a;
    }
    int b = Math.max(0, Math.max(prevLeftB, prevRightB)) + root.val;
    return new Pair(a, b);
  }

  class Pair {
    int a;
    int b;

    public Pair(int a, int b) {
      this.a = a;
      this.b = b;
    }
  }


//  private void countV(TreeNode node) {
//    V++;
//    if (node.left != null) {
//      countV(node.left);
//    }
//    if (node.right != null) {
//      countV(node.right);
//    }
//  }
//
//  private void addEdges(TreeNode node) {
//    int ind = index;
//    graph.addEdge(0, index++, node.val);
//    if (node.left != null) {
//      graph.addEdge(ind, index, node.left.val);
//      graph.addEdge(index, ind, node.val);
//      addEdges(node.left);
//    }
//    if (node.right != null) {
//      graph.addEdge(ind, index, node.right.val);
//      graph.addEdge(index, ind, node.val);
//      addEdges(node.right);
//    }
//  }

  class LongestPath {

    PriorityQueue<Integer> priorityQueue;
    boolean[] inQ;
    int[] dist;
    Edge[] edgeTo;
    int max = Integer.MIN_VALUE;

    public LongestPath(Graph graph) {
      inQ = new boolean[graph.V];
      dist = new int[graph.V];
      edgeTo = new Edge[graph.V];
      priorityQueue = new PriorityQueue<>();
      for (int i = 0; i < graph.V; i++) {
        dist[i] = Integer.MIN_VALUE;
      }
      dist[0] = 0;
      inQ[0] = true;
      priorityQueue.add(0);
      while (!priorityQueue.isEmpty()) {
        int v = priorityQueue.poll();
        inQ[v] = false;
        relax(graph, v);
      }
    }

    private void relax(Graph g, int v) {
      for (Edge e: g.adj(v)) {
        int w = e.to;
        if (edgeTo[v] != null && w == edgeTo[v].from) {
          continue;
        }
        if (dist[w] < dist[v] + e.weight) {
          dist[w] = dist[v] + e.weight;
          edgeTo[w] = e;
          if (!inQ[w]) {
            priorityQueue.add(w);
            inQ[w] = true;
          }
          if (max < dist[w]) {
            max = dist[w];
          }
        }
      }
    }

  }

  class Graph {
    List<Edge>[] edges;
    int V;
    int E;

    public Graph(int V) {
      this.V = V;
      edges = (List<Edge>[]) new ArrayList[V];
      for (int i = 0; i < V; i++) {
        edges[i] = new ArrayList<>();
      }
    }

    public Iterable<Edge> adj(int v) {
      return edges[v];
    }

    public void addEdge(int v, int w, int weight) {
      edges[v].add(new Edge(v, w, weight));
    }
  }

  class Edge {
    int from;
    int to;
    int weight;

    public Edge(int from, int to, int weight) {
      this.from = from;
      this.to = to;
      this.weight = weight;
    }
  }

  static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

}
