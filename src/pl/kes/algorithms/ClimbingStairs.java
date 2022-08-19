package pl.kes.algorithms;

import java.util.HashMap;
import java.util.Map;

public class ClimbingStairs {

  private Map<Integer, Integer> steps = new HashMap<>(Map.of(1, 1, 2, 2));

  public int climbStairs(int n) {
    if (steps.containsKey(n)) {
      return steps.get(n);
    }
    for (int i = 3; i <= n; i++) {
       steps.put(i, steps.get(i - 1) + steps.get(i - 2));
    }
    return steps.get(n);
  }
}
