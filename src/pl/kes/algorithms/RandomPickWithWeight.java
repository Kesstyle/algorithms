package pl.kes.algorithms;

import java.util.Random;

public class RandomPickWithWeight {

  public static void main(String...args) {
    RandomPickWithWeight randomPickWithWeight = new RandomPickWithWeight(new int[] {3, 14, 1, 7});
    System.out.println(randomPickWithWeight.pickIndex());
    System.out.println(randomPickWithWeight.pickIndex());
    System.out.println(randomPickWithWeight.pickIndex());
    System.out.println(randomPickWithWeight.pickIndex());
    System.out.println(randomPickWithWeight.pickIndex());
  }

  double[] ranges;
  int n;
  Random random;


  public RandomPickWithWeight(int[] w) {
    n = w.length;
    ranges = new double[n];
    int sum = sum(w);
    double prev = 0;
    for (int i = 0; i < n; i++) {
      prev += (double) w[i] /  (double) sum;
      ranges[i] = prev;
    }
    random = new Random();
  }

  public int pickIndex() {
    return numberFromRandom(random.nextDouble());
  }

  private int numberFromRandom(double random) {
    int start = 0;
    int end = n - 1;
    while (end - start >= 1) {
      int mid = (end + start) / 2;
      double next = ranges[mid];
      if (random > next) {
        start = mid + 1;
      } else if (random < next) {
        end = mid;
      } else {
        return mid;
      }
    }
    return start;
  }

  private int sum(int[] w) {
    int sum = 0;
    for (int i = 0; i < w.length; i++) {
      sum += w[i];
    }
    return sum;
  }
}
