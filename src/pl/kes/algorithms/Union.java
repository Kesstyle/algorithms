package pl.kes.algorithms;

import java.util.Set;
import java.util.TreeSet;

public class Union {

  public static void main(String ... args) {
    Set<Integer> test = new TreeSet<>((a, b) -> a - b);
    test.add(5);
    test.add(8);
    test.add(1);
    test.add(4);
    test.add(0);
    for (Integer v: test) {
      System.out.println(v);
    }
  }

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