package pl.kes.algorithms;

public class ValidParentheses {

  public static void main(String...args) {
    System.out.println(isValid("()[{]}"));
  }

  public static boolean isValid(String s) {
    char[] stack = new char[8];
    int size = 0;
    char[] pars = s.toCharArray();
    for (int i = 0; i < pars.length; i++) {
      if (pars[i] == '[' || pars[i] == '{' || pars[i] == '(') {
        if (stack.length == size) {
          char[] tmp = new char[size*2];
          for (int j = 0; j < size; j++) {
            tmp[j] = stack[j];
          }
          stack = tmp;
        }
        stack[size++] = pars[i];
      } else {
        char next = pars[i];
        if (size == 0 || next == '}' && stack[size - 1] != '{' ||
            next == ')' && stack[size - 1] != '(' ||
            next == ']' && stack[size - 1] != '[') {
          return false;
        } else {
          size--;
        }
      }
    }
    if (size != 0) {
      return false;
    }

    return true;
  }
}
