package pl.kes.algorithms;

public class HappyNumber {

  public boolean isHappy(int n) {
    int slow = n, fast = n;
    do {
      slow = sumDigitsSquares(slow);
      fast = sumDigitsSquares(sumDigitsSquares(fast));
    } while (slow != fast);
    if (slow == 1) {
      return true;
    }
    return false;
  }

  private int sumDigitsSquares(int n) {
    int sum = 0;
    while (n > 0) {
      int tmp = n % 10;
      sum += tmp * tmp;
      n /= 10;
    }
    return sum;
  }
}
