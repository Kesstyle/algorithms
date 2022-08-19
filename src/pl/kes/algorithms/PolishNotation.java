package pl.kes.algorithms;

import java.util.Set;
import java.util.Stack;

public class PolishNotation {

  public static void main(String...args) {
    PolishNotation polishNotation = new PolishNotation();
    String[] tokens = new String[] {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
    System.out.println(polishNotation.evalRPN(tokens));
  }

  Set<String> operands = Set.of("/", "+", "-", "*");

  public int evalRPN(String[] tokens) {
    Stack<Integer> numbers = new Stack<>();

    for (int i = 0; i < tokens.length; i++) {
      String next = tokens[i];
      if (operands.contains(next)) {
        int second = numbers.pop();
        int first = numbers.pop();
        if ("/".equals(next)) {
          numbers.push(first / second);
        } else if ("*".equals(next)) {
          numbers.push(first * second);
        } else if ("+".equals(next)) {
          numbers.push(first + second);
        } else {
          numbers.push(first - second);
        }
      } else {
        numbers.push(Integer.parseInt(next));
      }
    }
    return numbers.pop();
  }
}
