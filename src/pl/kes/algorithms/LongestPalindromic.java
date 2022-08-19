package pl.kes.algorithms;

import java.util.Stack;

public class LongestPalindromic {

  public static void main(String...args) {
    LongestPalindromic longestPalindromic = new LongestPalindromic();
    String test = "babadada";
    System.out.println(longestPalindromic.longestPalindrome(test));
  }

  private String maxStr = "";

  public String longestPalindrome(String s) {
    for (int i = 0; i < s.length(); i++) {
      longestPalindrome(s, i, i);
      longestPalindrome(s, i, i + 1);
    }
    return maxStr;
  }

  public void longestPalindrome(final String s, int start, int end) {
    while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
      if (end - start + 1 > maxStr.length()) {
        maxStr = s.substring(start, end + 1);
      }
      start--;
      end++;
    }
  }

}
