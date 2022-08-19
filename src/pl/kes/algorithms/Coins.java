package pl.kes.algorithms;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Coins {

  public static void main(String...args) {
    Matcher flightInfoMatcher = Pattern.compile("asasd").matcher(null);
  }

  public int coinChange(int[] coins, int amount) {
    if (amount == 0) {
      return 0;
    }
    int[] dynamic = new int[amount + 1];
    Arrays.fill(dynamic, Integer.MAX_VALUE);
    for (int coin: coins) {
      if (coin > amount) {
        continue;
      }
      dynamic[coin] = 1;
    }

    for (int coin: coins) {
      if (coin > amount) {
        continue;
      }
      for (int sum = 1; sum <= amount; sum++) {
        if (dynamic[sum] == Integer.MAX_VALUE || sum + coin > amount) {
          continue;
        }
        dynamic[sum + coin] = Math.min(dynamic[sum + coin], dynamic[sum] + dynamic[coin]);
      }
    }

    if (dynamic[amount] == Integer.MAX_VALUE) {
      return -1;
    } else {
      return dynamic[amount];
    }
  }

  private int changeCoin(int[] coins, int amount, int startingIndex) {
    if (amount == 0) {
      return 0;
    }
    if (amount < coins[0]) {
      return -1;
    }
    int sum = amount;
    int minRank = Integer.MAX_VALUE;
    for (int i = startingIndex; i >= 0; i--) {
 //     System.out.println("remaining sum is " + sum);
      if (coins[i] == sum) {
        return 1;
      }
      if (coins[i] < sum) {
//        System.out.println("coin is " + coins[i] + ", remaining sum is " + sum);
        int rank = changeCoin(coins, sum - coins[i], i);
        if (rank == -1) {
          continue;
        } else {
          if (minRank > rank) {
            minRank = rank;
          }
        }
      }
    }
    if (minRank == Integer.MAX_VALUE) {
      return -1;
    }
    return minRank + 1;
  }

  private void sort(int[] coins) {
    int n = coins.length;
    for (int i = 1; i < n; i++) {
      for (int j = i; j > 0 && coins[j] < coins[j - 1]; j--) {
        int tmp = coins[j];
        coins[j] = coins[j-1];
        coins[j-1] = tmp;
      }
    }
  }
}
