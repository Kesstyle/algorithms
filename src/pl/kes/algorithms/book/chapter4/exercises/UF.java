package pl.kes.algorithms.book.chapter4.exercises;

import pl.kes.algorithms.book.standard.StdOut;

public class UF {

  private int[] parents;
  private int[] ranks;
  private int[] counts;
  private int n;

  public UF(int n) {
    this.n = n;
    parents = new int[n];
    ranks = new int[n];
    counts = new int[n];
    for (int i = 0; i < n; i++) {
      parents[i] = i;
      ranks[i] = 0;
      counts[i] = 1;
    }
  }

  public int find(int p) {
    while (p != parents[p]) {
      parents[p] = parents[parents[p]];
      p = parents[p];
    }
    return p;
  }

  public int rank(int p) {
    return ranks[p];
  }

  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }

  public void union(int p, int q) {
    int rootP = find(p);
    int rootQ = find(q);
    if (rootP == rootQ) {
      StdOut.println("Already joined: " + p + ", " + q);
      return;
    }
    if (rank(rootP) < rank(rootQ)) {
      parents[rootP] = rootQ;
      counts[rootQ] += counts[rootP];
    } else if (rank(rootQ) < rank(rootP)) {
      parents[rootQ] = rootP;
      counts[rootP] += counts[rootQ];
    } else {
      parents[rootP] = rootQ;
      counts[rootQ] += counts[rootP];
      ranks[rootQ]++;
    }
    n--;
  }

  public int count(int p) {
    return counts[find(p)];
  }
}
