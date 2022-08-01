package pl.kes.algorithms;

public class MySqrt {

  public static void main(String...args) {
    System.out.println(mySqrt(120));
  }

  public static int mySqrt(int x) {
    if (x == 1) {
      return 1;
    }
    int nextRes = x;
    int prevRes = 0;
    int tmp = 0;
    while (nextRes - prevRes > 1) {
      tmp = prevRes + (nextRes - prevRes) / 2;
      int res = x / tmp;
      if (res < tmp) {
        nextRes -= (nextRes - prevRes) / 2;
      } else if (res > tmp) {
        prevRes += (nextRes - prevRes) / 2;
      } else {
        return tmp;
      }
  //    System.out.println("nextRes = " + nextRes + ", prevRes = " + prevRes);
    }
    return prevRes;
  }
}
