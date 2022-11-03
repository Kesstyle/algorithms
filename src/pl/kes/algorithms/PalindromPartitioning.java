package pl.kes.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PalindromPartitioning {

  public static void main(String...args) {
    PalindromPartitioning palindromPartitioning = new PalindromPartitioning();
    String s = "aaaaaaaaaaaa";
    List<List<String>> res = palindromPartitioning.partition(s);
    for (int i = 0; i < res.size(); i++) {
      for (int j = 0; j < res.get(i).size(); j++) {
        System.out.print(res.get(i).get(j) + ", ");
      }
      System.out.println();
    }
  }

  Map<Integer, List<List<String>>> lists = new HashMap<>();
  int n;

  public List<List<String>> partition(String s) {
    n = s.length();

    // O(n * )
    for (int i = 0; i < n; i++) {
      addToLists(s, i, 0);
      addToLists(s, i, 1);
    }
    return lists.get(n-1);
  }

  private void addToLists(String s, int index, int shift) {
    int k = index, r = index + shift;
    // O(n) +
    for (int i = 0; k - i >= 0 && r + i < n; i++) {
      if (s.charAt(k - i) == s.charAt(r + i)) {
        // O(1)
        if (lists.get(r+i) == null) {
          lists.put(r+i, new ArrayList<>());
        }
        // O(1)
        List<List<String>> actualList = lists.get(r+i);

        String tmp = s.substring(k-i, r+i+1);
        if (k - i - 1 >= 0) {
          // O(1)
          List<List<String>> prevLists = lists.get(k - i - 1);
          //
          for (int j = 0; j < prevLists.size(); j++) {
            List<String> toAdd = new ArrayList<>(prevLists.get(j));
            toAdd.add(tmp);
            actualList.add(toAdd);
          }
        } else {
          // O(n)
          actualList.add(new ArrayList<>(List.of(tmp)));
        }
      }
    }
  }
}
