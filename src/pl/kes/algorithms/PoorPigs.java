package pl.kes.algorithms;

import java.util.HashMap;
import java.util.Map;

public class PoorPigs {

  public static void main(String...args) {
    PoorPigs poorPigs = new PoorPigs();
    int buckets = 10, minutesToDie = 15, minutesToTest = 30;
    System.out.println(poorPigs.poorPigs(buckets, minutesToDie, minutesToTest));
  }

  Map<Integer, Integer> maxMap = new HashMap<>();

  public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
    if (buckets == 1) {
      return 0;
    }
    int numberAttempts = minutesToTest / minutesToDie;
    for (int i = 0; i <= numberAttempts; i++) {
      maxMap.put(i, 1);
    }
    for (int i = 0; i <= numberAttempts; i++) {
      if (i + 1 >= buckets) {
        return 1;
      }
      maxMap.put(1000 + i, i + 1);
    }

    int i = 2;

    while (true) {
      for (int j = 0; j <= numberAttempts; j++) {
        int max = dynamic(i, j);
        if (max >= buckets) {
          return i;
        }
      }
      i++;
    }
  }

  private int dynamic(int numberOfPigs, int attempts) {
    int sum = 0;
    if (attempts == 0) {
      sum = 1;
    } else {
      for (int i = 0; i <= numberOfPigs; i++) {
        sum += cik(i, numberOfPigs) * maxMap.get((numberOfPigs - i) * 1000 + attempts - 1);
      }
    }
    maxMap.put(1000 * numberOfPigs + attempts, sum);
    return sum;
  }

  private int cik(int i, int k) {
    if (i == 0 || i == k) {
      return 1;
    }
    if (i == 1) {
      return k;
    }
    double up = 1;
    for (int j = i + 1; j <= k; j++) {
      up *= j;
    }
    double down = 1;
    for (int j = 1; j <= k - i; j++) {
      down *= j;
    }
    return (int) (up / down);
  }
//
//  class Pair {
//    int attempts;
//    int pigs;
//
//    public Pair(final int attempts, final int pigs) {
//      this.attempts = attempts;
//      this.pigs = pigs;
//    }
//
//    @Override
//    public boolean equals(final Object o) {
//      if (this == o) {
//        return true;
//      }
//      if (o == null || getClass() != o.getClass()) {
//        return false;
//      }
//      final Pair pair = (Pair) o;
//      return attempts == pair.attempts && pigs == pair.pigs;
//    }
//
//    @Override
//    public int hashCode() {
//      return Objects.hash(attempts, pigs);
//    }
//  }
}
