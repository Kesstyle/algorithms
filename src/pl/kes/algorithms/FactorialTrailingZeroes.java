package pl.kes.algorithms;

public class FactorialTrailingZeroes {

  public static void main(String...args) {
    FactorialTrailingZeroes factorialTrailingZeroes = new FactorialTrailingZeroes();
    int n = 254;
    int res = factorialTrailingZeroes.trailingZeroes(n);
    System.out.println(res);
  }

  public int trailingZeroes(int n) {
    return n / 5 + n / 25 + n / 125 + n / 625 + n / 3125;
  }
}
