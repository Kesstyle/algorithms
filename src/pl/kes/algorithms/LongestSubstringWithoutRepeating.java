package pl.kes.algorithms;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeating {

  public static void main(String...args) {
    LongestSubstringWithoutRepeating longestSubstringWithoutRepeating = new LongestSubstringWithoutRepeating();
    String test = "pwwkew";
    System.out.println(longestSubstringWithoutRepeating.lengthOfLongestSubstring(test));
  }

  private Map<Character, Integer> charMaps = new HashMap<>();

  public int lengthOfLongestSubstring(String s) {
    if (s.length() <= 1) {
      return s.length();
    }
    char[] array = s.toCharArray();
    int max = 0;
    int lastRepeat = -1;
    for (int i = 0; i < array.length; i++) {
      char nextSymbol = array[i];
      if (charMaps.containsKey(nextSymbol)) {
        max = Math.max(max, i - lastRepeat - 1);
        lastRepeat = Math.max(charMaps.get(nextSymbol), lastRepeat);
      }
      charMaps.put(nextSymbol, i);
    }
    return Math.max(max, array.length - lastRepeat - 1);
  }
}
