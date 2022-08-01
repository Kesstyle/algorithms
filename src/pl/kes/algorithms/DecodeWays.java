package pl.kes.algorithms;

import java.util.HashMap;
import java.util.Map;

public class DecodeWays {

  static Map<Integer, Integer> results = new HashMap<>();

  public static void main(String...args) {
    System.out.println(numDecodings("226"));
  }

  public static int numDecodings(String s) {
    return numDecodings(s, 0);
  }

  public static int numDecodings(String s, int startIndex) {
    if (results.get(startIndex) != null) {
      return results.get(startIndex);
    }
    if (s.length() - startIndex == 0) {
      results.put(startIndex, 1);
      return 1;
    }
    if (s.length() - startIndex == 1) {
      if (Integer.parseInt(String.valueOf(s.charAt(s.length() - 1))) > 0) {
        results.put(startIndex, 1);
        return 1;
      } else {
        results.put(startIndex, 0);
        return 0;
      }
    }
    if (s.charAt(startIndex) == '0') {
      results.put(startIndex, 0);
      return 0;
    }
    int result = 0;
    int number = Integer.parseInt(s.substring(startIndex, startIndex + 2));
    result += numDecodings(s, startIndex + 1);
    if (number <= 26) {
      result += numDecodings(s, startIndex + 2);
    }
    results.put(startIndex, result);
    return result;
  }
}
