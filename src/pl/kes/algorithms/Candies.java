package pl.kes.algorithms;

import java.util.ArrayList;
import java.util.List;

public class Candies {

  private int[] candies;
  private List<Integer>[] adj;

  public int candy(int[] ratings) {
    int n = ratings.length;
    if (n == 1) {
      return 1;
    }
    candies = new int[n];
    adj = new List[n];
    for (int i = 0; i < n - 1; i++) {
      int j = i + 1;
      if (ratings[i] > ratings[j]) {
        if (adj[i] == null) {
          adj[i] = new ArrayList<>();
        }
        adj[i].add(j);
      } else if (ratings[i] < ratings[j]) {
        if (adj[j] == null) {
          adj[j] = new ArrayList<>();
        }
        adj[j].add(i);
      }
    }
    for (int i = 0; i < n; i++) {
      if (candies[i] == 0) {
        dfs(i);
      }
    }
    int res = 0;
    for (int i = 0; i < n; i++) {
      res += candies[i];
    }
    return res;
  }

  private void dfs(int x) {
    if (adj[x] == null) {
      candies[x] = 1;
      return;
    }
    int max = 1;
    for (int n: adj[x]) {
      if (candies[n] == 0) {
        dfs(n);
      }
      if (max <= candies[n]) {
        max = candies[n] + 1;
      }
    }
    candies[x] = max;
  }
}
