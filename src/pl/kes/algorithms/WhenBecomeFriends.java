package pl.kes.algorithms;

import java.util.Arrays;
import java.util.Comparator;

public class WhenBecomeFriends {

  public int earliestAcq(int[][] logs, int n) {
    sort(logs, 0, logs.length - 1);
    Union union = new Union(n);
    for (int i = 0; i < logs.length; i++) {
      union.union(logs[i][1], logs[i][2]);
      if (union.allInOneGroup()) {
        return logs[i][0];
      }
    }
    return -1;
  }

  private void sort(int[][] logs, int lo, int hi) {
    if (hi - lo <= 0) {
      return;
    }
    int pivot = logs[lo][0];
    int i = lo;
    int j = hi + 1;
    while (i <= j) {
      while (logs[++i][0] < pivot) {
        if (i == hi) {
          break;
        }
      }
      while (logs[--j][0] >= pivot) {
        if (j == lo) {
          break;
        }
      }
      if (i >= j) {
        break;
      }
      swap(logs, i, j);
    }
    swap(logs, lo, j);
    sort(logs, lo, j - 1);
    sort(logs, j + 1, hi);
  }

  private void swap(int[][] logs, int i, int j) {
    int[] tmp = logs[i];
    logs[i] = logs[j];
    logs[j] = tmp;
  }

  class Union {

    int[] groups;
    int[] ranks;
    int numOfGroups;

    public Union(int size){
      groups = new int[size];
      ranks = new int[size];
      numOfGroups = size;
      for (int i = 0; i < size; i++) {
        groups[i] = i;
        ranks[i] = 1;
      }
    }

    public boolean allInOneGroup() {
      return numOfGroups == 1;
    }

    public void union(int i, int j) {
      int p = find(i);
      int q = find(j);
      if (p == q) {
        return;
      }
      if (ranks[p] > ranks[q]) {
        ranks[p] += ranks[q];
        groups[q] = p;
      } else {
        ranks[q] += ranks[p];
        groups[p] = q;
      }
      numOfGroups--;
    }

    public int find(int i) {
      int p = i;
      int q = groups[p];
      while (p != q) {
        groups[p] = groups[q];
        p = groups[p];
        q = groups[p];
      }
      return p;
    }
  }
}
