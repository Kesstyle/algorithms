package pl.kes.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ParallelCourses {

  public static void main(String...args) {
    ParallelCourses parallelCourses = new ParallelCourses();
    int n = 3;
    int[][] courses = new int[][] {{1, 3}, {2, 3}};
    System.out.println(parallelCourses.minimumSemesters(n, courses));
  }

  Stack<Integer> topological = new Stack<>();
  Map<Integer, List<Integer>> adjMap;
  int[] lengths;
  boolean[] marked;
  boolean[] recurs;
  int max = 0;
  boolean loopFound = false;

  public int minimumSemesters(int n, int[][] relations) {
    marked = new boolean[n + 1];
    lengths = new int[n + 1];
    recurs = new boolean[n + 1];
    adjMap = new HashMap<>();
    for (int i = 0; i < relations.length; i++) {
      int s = relations[i][0];
      int d = relations[i][1];
      if (adjMap.get(s) == null) {
        adjMap.put(s, new ArrayList<>());
      }
      adjMap.get(s).add(d);
    }
    for (int i = 0; i < n; i++) {
      if (!marked[i]) {
        dfs(i);
      }
    }
    if (loopFound) {
      return -1;
    }
    while (!topological.isEmpty()) {
      relaxVertice(topological.pop());
    }
    return max + 1;
  }

  private void relaxVertice(int node) {
    List<Integer> adj = adjMap.get(node);
    if (adj == null) {
      return;
    }
    for (int d: adj) {
      if (lengths[d] < lengths[node] + 1) {
        lengths[d] = lengths[node] + 1;
      }
      if (max < lengths[d]) {
        max = lengths[d];
      }
    }
  }

  private void dfs(int node) {
    marked[node] = true;
    recurs[node] = true;
    List<Integer> adj = adjMap.get(node);
    if (adj != null) {
      for (int d: adj) {
        if (recurs[d]) {
          loopFound = true;
          return;
        }
        if (!marked[d]) {
          dfs(d);
        }
      }
    }
    recurs[node] = false;
    topological.push(node);
  }
}
