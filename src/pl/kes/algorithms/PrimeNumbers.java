package pl.kes.algorithms;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumbers {

  public static void main(String...args) {
    PrimeNumbers primeNumbers = new PrimeNumbers();
    int n = 10;
    int res = primeNumbers.countPrimes(n);
    System.out.println(res);
  }

  boolean[] visited = new boolean[5000001];
  List<Integer> primes = new ArrayList<>();

  public int countPrimes(int n) {
    for (int i = 2; i < visited.length; i++) {
      if (!visited[i]) {
        primes.add(i);
        for (int j = i; j < visited.length; j += i) {
          visited[j] = true;
        }
      }
    }
    int l0 = 0, l1 = primes.size();
    int m =  l0 + (l1 - l0) / 2;
    while (l0 <= l1) {
      m = l0 + (l1 - l0) / 2;
      if (primes.get(m) >= n) {
        l1 = m-1;
      } else {
        l0 = m+1;
      }
    }
    return m+1;
  }
}
