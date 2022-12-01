package pl.kes.algorithms;

import java.util.Stack;

public class DecodeString {

  public static void main(String...args) {
    DecodeString decodeString = new DecodeString();
    String pattern = "3[z]2[2[y]pq4[2[jk]e1[f]]]ef";
    System.out.println(decodeString.decodeString(pattern));
  }

  public String decodeString(String s) {
    Stack<Integer> numbers = new Stack<>();
    Stack<String> strs = new Stack<>();
    String sb = "";
    int number = 0;
    int h = 10;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (Character.isDigit(c)) {
        number = h * number + c - '0';
      }

      if (isSymbol(c)) {
        sb = sb + c;
      }

      if (c == '[') {
        numbers.push(number);
        number = 0;
        strs.push(sb);
        sb = "";
        continue;
      }

      if (c == ']') {
        int num = numbers.pop();
        sb = strs.pop() + sb.repeat(num);
      }
    }
    return sb;
  }

  private boolean isSymbol(char c) {
    return 'z' - c <= 'z' - 'a' && 'z' - c >= 0;
  }
}
