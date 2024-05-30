package pl.kes.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ExpressionAddOperators {

  public static void main(String...args) {
    ExpressionAddOperators expressionAddOperators = new ExpressionAddOperators();
    String num = "000";
    int target = 0;
    List<String> res = expressionAddOperators.addOperators(num, target);
    res.stream().forEach(System.out::println);
  }

  Map<Integer, List<String>> allExpressions = new HashMap<>();
  Map<String, Long> expResults = new HashMap<>();
  Stack<Long> numbers = new Stack<>();
  List<String> neededExps = new ArrayList<>();

  public List<String> addOperators(String num, int target) {
    List<String> allExps = allPossibleExpressions(num, 0);
    for (int i = 0; i < allExps.size(); i++) {
      long res = evalExp(allExps.get(i));
      if (res == target) {
        neededExps.add(allExps.get(i));
      }
    }
    return neededExps;
  }

  private List<String> allPossibleExpressions(String str, int start) {
    int end = str.length();
    int n = end - start;
    if (n == 1) {
      return Arrays.asList(str.substring(start));
    }
    if (n == 0) {
      return Arrays.asList();
    }
    if (allExpressions.get(start) != null) {
      return allExpressions.get(start);
    }
    List<String> result = new ArrayList<>();
    if (str.charAt(start) != '0') {
      result.add(str.substring(start));
    }
    int till = str.charAt(start) != '0' ? n : 2;
    for (int i = 1; i < till; i++) {
      List<String> allExpr = allPossibleExpressions(str, start + i);
      String chunk = str.substring(start, start + i);
      for (String strExp: allExpr) {
        result.add(chunk + "+" + strExp);
        result.add(chunk + "-" + strExp);
        result.add(chunk + "*" + strExp);
      }
    }
    allExpressions.put(start, result);
    return result;
  }

  private long evalExp(String exp) {
    if (expResults.get(exp) != null) {
      return expResults.get(exp);
    }
    long next = 0;
    int i = 0;
    int indexOfPlus = exp.indexOf('+');
    if (indexOfPlus != -1) {
      long result = evalExp(exp.substring(0, indexOfPlus)) + evalExp(exp.substring(indexOfPlus + 1, exp.length()));
      expResults.put(exp, result);
      return result;
    }
    while (i < exp.length() && Character.isDigit(exp.charAt(i))) {
      next = next * 10 + exp.charAt(i) - '0';
      i++;
    }
    numbers.push(next);
    next = 0;
    while (i < exp.length()) {
      char operation = exp.charAt(i++);
      while (i < exp.length() && Character.isDigit(exp.charAt(i))) {
        next = next * 10 + exp.charAt(i) - '0';
        i++;
      }
      if (operation == '*') {
        long prev = numbers.pop();
        numbers.push(prev * next);
      } else if (operation == '-') {
        numbers.push(-next);
      } else {
        numbers.push(next);
      }
      next = 0;
    }
    long res = 0;
    while (!numbers.isEmpty()) {
      res += numbers.pop();
    }
    expResults.put(exp, res);
    return res;
  }
}
