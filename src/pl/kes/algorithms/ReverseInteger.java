package pl.kes.algorithms;

public class ReverseInteger {

  public static void main(String...args) {
    ReverseInteger reverseInteger = new ReverseInteger();
    System.out.println(reverseInteger.reverse(-2147483648));
  }

  public int reverse(int x) {
    int result = 0;
    int[] stack = new int[10];
    int i = 0;
    boolean isNegative = x < 0;
    if (isNegative) {
      if (x == Integer.MIN_VALUE) {
        return 0;
      }
      x *= -1;
    }

    while (x != 0) {
      int nextDigit = x % 10;
      x /= 10;
      stack[i++] = nextDigit;
    }

    if (i > 10) {
      return 0;
    }
    if (i == 10 && stack[0] > 2) {
      return 0;
    }

    int arraySize = i;
    i--;
    int multiplyer = 1;
    if (isNegative) {
      while (i >= 0) {
        if (arraySize - i >= 10) {
          int tmp = Integer.MIN_VALUE + result;
          if (-1 * tmp / multiplyer < stack[i]) {
            return 0;
          }
        }
        result += multiplyer * stack[i--];
        multiplyer *= 10;
      }
    } else {
      while (i >= 0) {
        if (arraySize - i >= 10) {
          int tmp = Integer.MAX_VALUE - result;
          if (tmp / multiplyer < stack[i]) {
            return 0;
          }
        }
        result += multiplyer * stack[i--];
        multiplyer *= 10;
      }
    }
    return isNegative? -result : result;
  }
}
