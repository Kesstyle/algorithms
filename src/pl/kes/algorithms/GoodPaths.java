package pl.kes.algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class GoodPaths {

  public static void main(String...args) {
    GoodPaths goodPaths = new GoodPaths();
    int[][] edges = new int[][] {{0,1},{0,2},{2,3},{2,4}};
    int[] vals = new int[] {1,3,2,1,3};
    System.out.println(goodPaths.numberOfGoodPaths(vals, edges));
  }

  Map<Integer, List<Integer>> valsToIndexes;
  Map<Integer, List<Integer>> adjMap;

  public int numberOfGoodPaths(int[] vals, int[][] edges) {
    int n = vals.length;
    valsToIndexes = new TreeMap<>(Comparator.comparingInt(a -> a));
    adjMap = new HashMap<>();
    int sum = 0;
    for (int i = 0; i < n; i++) {
      int val = vals[i];
      if (valsToIndexes.get(val) == null) {
        valsToIndexes.put(val, new ArrayList<>());
      }
      valsToIndexes.get(val).add(i);
    }
    for (int i = 0; i < edges.length; i++) {
      int a = edges[i][0];
      int b = edges[i][1];
      if (adjMap.get(a) == null) {
        adjMap.put(a, new ArrayList<>());
      }
      if (adjMap.get(b) == null) {
        adjMap.put(b, new ArrayList<>());
      }
      adjMap.get(a).add(b);
      adjMap.get(b).add(a);
    }

    Union union = new Union(n);
    for (Map.Entry<Integer, List<Integer>> entry: valsToIndexes.entrySet()) {
      for (int i: entry.getValue()) {
        if (adjMap.get(i) == null) {
          continue;
        }
        for (int j: adjMap.get(i)) {
          if (vals[i] >= vals[j]) {
            union.union(i, j);
          }
        }
      }
      Map<Integer, Integer> groups = new HashMap<>();
      for (int i: entry.getValue()) {
        int gr = union.find(i);
        if (groups.get(gr) == null) {
          groups.put(gr, 0);
        }
        groups.put(gr, groups.get(gr) + 1);
      }
      for (Map.Entry<Integer, Integer> gE: groups.entrySet()) {
        sum += (gE.getValue() * (gE.getValue() + 1)) / 2;
      }
    }

    return sum;
  }

  class Union {
    int[] parents;
    int[] ranks;

    public Union(int n) {
      parents = new int[n];
      ranks = new int[n];
      for (int i = 0; i < n; i++) {
        parents[i] = i;
        ranks[i] = 1;
      }
    }

    public int rank(int i) {
      return ranks[find(i)];
    }

    public int find(int i) {
      int q = i;
      while (q != parents[q]) {
        parents[q] = parents[parents[q]];
        q = parents[q];
      }
      HashSet<Integer>[] groups = (HashSet<Integer>[]) new Set[26];
      return parents[q];
    }

    public void union(int i, int j) {
      int p = find(i);
      int q = find(j);
      if (p == q) {
        return;
      }
      int rankP = rank(p);
      int rankQ = rank(q);
      if (rankP > rankQ) {
        parents[q] = p;
        ranks[p] += ranks[q];
      } else {
        parents[p] = q;
        ranks[q] += ranks[p];
      }
    }
  }
}
