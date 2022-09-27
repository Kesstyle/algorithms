package pl.kes.algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestSubstringKElem {

  public static void main(String... args) {
    LongestSubstringKElem longestSubstringKElem = new LongestSubstringKElem();
    String s = "bbaaacbd";
    int k = 3;
    System.out.println(longestSubstringKElem.longestSubstring(s, k));
  }

  public int longestSubstring(String s, int k) {
    char[] array = s.toCharArray();
    return maxStr(array, 0, s.length(), k);
  }

  private int maxStr(char[] array, int begin, int end, int k) {
    if (end - begin < k) {
      return 0;
    }
    int[] occurrencies = new int[26];
    for (int i = begin; i < end; i++) {
      occurrencies[array[i] - 'a']++;
    }
    for (int i = begin; i < end; i++) {
      if (occurrencies[array[i] - 'a'] < k) {
        return Math.max(maxStr(array, begin, i, k), maxStr(array, i+1, end, k));
      }
    }
    return end - begin;
  }

}
