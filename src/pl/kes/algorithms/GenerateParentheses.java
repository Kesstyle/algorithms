package pl.kes.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateParentheses {

  static List<String> variants = new ArrayList<>();

  public static void main(String...args) {
    List<String> result = generateParenthesis(8);
    for (String s: result) {
      System.out.println(s);
    }
  }

  public static List<String> generateParenthesis(int n) {
    generateParenthesis(n, 0, 0, "");
    return variants;
  }

  private static void generateParenthesis(int n, int openCount, int closeCount, String str) {
    System.out.println(openCount*10 + closeCount);
    if (openCount == n && closeCount == n) {
      variants.add(str);
      return;
    }
    if (openCount < n) {
      generateParenthesis(n, openCount + 1, closeCount, str + '(');
    }
    if (closeCount < openCount) {
      generateParenthesis(n, openCount, closeCount + 1, str + ')');
    }
  }
}
