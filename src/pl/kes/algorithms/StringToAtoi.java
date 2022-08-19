package pl.kes.algorithms;

import java.util.Set;

public class StringToAtoi {

  public static void main(String...args) {
    StringToAtoi stringToAtoi = new StringToAtoi();
    final String str = "-2147483647";
    System.out.println(stringToAtoi.myAtoi(str));
  }

  Set<Character> digits = Set.of('1', '2', '3', '4', '5', '6', '7', '8', '9', '0');

  public int myAtoi(String s) {
    if (s.isBlank()) {
      return 0;
    }
    s = s.trim();
    char[] charArray = s.toCharArray();
    int i = 0;
    boolean negative = false;
    if (charArray[i] == '-') {
      negative = true;
      i++;
    } else if (charArray[i] == '+') {
      i++;
    } else if (!digits.contains(charArray[i])) {
      return 0;
    }
    int sum = 0;
    while (i < charArray.length && digits.contains(charArray[i])) {
      int nextDigit = Integer.parseInt(charArray[i] + "");
      if (sum == 0) {
        sum = nextDigit;
        i++;
        continue;
      }
      if (!negative) {
        int thresholdMulti = (Integer.MAX_VALUE / 10) / sum;
        int thresholdRest = (Integer.MAX_VALUE / 10) % sum;
        if (thresholdMulti > 1 || thresholdMulti == 1 && thresholdRest > 0) {
          sum = 10 * sum + nextDigit;
        } else if (thresholdMulti == 1 && thresholdRest == 0 && nextDigit <= Integer.MAX_VALUE % 10) {
          sum = 10 * sum + nextDigit;
        } else {
          return Integer.MAX_VALUE;
        }
      } else {
        int thresholdMulti = (Integer.MIN_VALUE / 10) / sum;
        int thresholdRest = (Integer.MIN_VALUE / 10) % sum;
        if (thresholdMulti < -1 || thresholdMulti == -1 && thresholdRest < 0) {
          sum = 10 * sum + nextDigit;
        } else if (thresholdMulti == -1 && thresholdRest == 0 && nextDigit < (Integer.MIN_VALUE % 10) * -1) {
          sum = 10 * sum + nextDigit;
        } else {
          return Integer.MIN_VALUE;
        }
      }
      i++;
    }
    return negative ? -sum : sum;
  }
}
