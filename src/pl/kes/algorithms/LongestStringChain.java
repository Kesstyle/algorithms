package pl.kes.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class LongestStringChain {

  public static void main(String...args) {
    LongestStringChain longestStringChain = new LongestStringChain();
    String[] words = new String[] {"abcd","dbqca"};
    System.out.println(longestStringChain.longestStrChain(words));
  }

  Map<Integer, List<Integer>> adj = new HashMap<>();
  Map<Integer, List<Integer>> lengthMap = new HashMap<>();
  boolean[] marked;
  int[] distTo;
  Stack<Integer> topo = new Stack<>();
  int max = 0;

  public int longestStrChain(String[] words) {
    marked = new boolean[words.length];
    distTo = new int[words.length];
    int maxLength = 0;
    for (int i = 0; i < words.length; i++) {
      adj.put(i, new ArrayList<>());
      if (lengthMap.get(words[i].length()) == null) {
        lengthMap.put(words[i].length(), new ArrayList<>());
      }
      lengthMap.get(words[i].length()).add(i);
      if (maxLength < words[i].length()) {
        maxLength = words[i].length();
      }
    }
    for (int i = 1; i < maxLength; i++) {
      List<Integer> indexes = lengthMap.get(i);
      if (indexes == null || indexes.isEmpty()) {
        continue;
      }
      List<Integer> next = lengthMap.get(i + 1);
      if (next == null) {
        continue;
      }
      for (int index: indexes) {
        for (int nextInd: next) {
          if (isPred(words[index], words[nextInd])) {
            adj.get(index).add(nextInd);
          }
        }
      }
    }
    for (int ind: adj.keySet()) {
      if (!marked[ind]) {
        dfs(ind);
      }
    }
    while (!topo.isEmpty()) {
      relax(topo.pop());
    }
    return max + 1;
  }

  private void relax(int index) {
    for (int i: adj.get(index)) {
      if (distTo[i] < distTo[index] + 1) {
        distTo[i] = distTo[index] + 1;
        if (max < distTo[i]) {
          max = distTo[i];
        }
      }
    }
  }

  private void dfs(int index) {
    marked[index] = true;
    for (int i: adj.get(index)) {
      if (!marked[i]) {
        dfs(i);
      }
    }
    topo.push(index);
  }

  private boolean isPred(String source, String target) {
    if (target.length() != source.length() + 1) {
      return false;
    }
    int i = 0;
    int j = 0;
    while (i < source.length() && source.charAt(i) == target.charAt(j)) {
      i++;
      j++;
    }
    if (i == source.length()) {
      return true;
    }
    j++;
    while (i < source.length() && source.charAt(i) == target.charAt(j)) {
      i++;
      j++;
    }
    return i == source.length();
  }

}
