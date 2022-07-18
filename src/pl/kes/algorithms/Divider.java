package pl.kes.algorithms;

public class Divider {

  public static void main(String... args) {
    System.out.println(divide(-2147483648, -1109186033));
  }

  public static int divide(int dividend, int divisor) {
    int reserve = 0;
    if (dividend == Integer.MIN_VALUE) {
      if (divisor == -1) {
        return Integer.MAX_VALUE;
      } else if (divisor == Integer.MIN_VALUE) {
        return 1;
      }
    }
    if (divisor == Integer.MIN_VALUE && dividend != Integer.MIN_VALUE) {
      return 0;
    }
    if (divisor == 1) {
      return dividend;
    }
    if (dividend == 0) {
      return 0;
    }

    boolean diffSign = dividend < 0 ^ divisor < 0;

    if (divisor < 0) {
      divisor = -divisor;
    }

    if (dividend == Integer.MIN_VALUE) {
      reserve += 1;
      dividend += divisor;
      dividend = -dividend;
    } else if (dividend < 0) {
      dividend = -dividend;
    }


    if (dividend < divisor) {
      return diffSign? -reserve : reserve;
    }
    int sumAggregator = divisor;
    int previous = 0;
    int result;
    for (result = 0; ; result++) {
      if (dividend - sumAggregator < 0) {
        result--;
        sumAggregator -= previous;
        break;
      }
      previous = sumAggregator;
      if (Integer.MAX_VALUE - sumAggregator < sumAggregator) {
        break;
      }
      sumAggregator += sumAggregator;
    }

    int tmpResult = degree2(result) + divide(dividend - sumAggregator, divisor) + reserve;
    if (diffSign) {
      return -tmpResult;
    }
    return tmpResult;
  }

  private static int degree2(int degree) {
    if (degree == 0) {
      return 1;
    }

    int result = 1;
    for (int i = 1; i <= degree; i++) {
      result += result;
    }
    return result;
  }
}
