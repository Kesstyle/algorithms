package pl.kes.algorithms;

public class SumOf2Integers {

  public static void main(String...args) {
    SumOf2Integers sumOf2Integers = new SumOf2Integers();
    int a = 267;
    int b = 222;
    System.out.println(~b & a);
    System.out.println(sumOf2Integers.getSum(a, b));
  }

  public int getSum(int a, int b) {
    while (b != 0) {
      int c = a & b;
      a = a ^ b;
      b = c << 1;
    }
    return a;
  }
}
