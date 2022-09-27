package pl.kes.algorithms;

public class BuySellStock {

  public static void main(String...args) {
    BuySellStock buySellStock = new BuySellStock();
    int[] prices = new int[] {7,6,4,3,1};
    int profit = buySellStock.maxProfit(prices);
    System.out.println(profit);
  }

  public int maxProfit(int[] prices) {
    if (prices.length == 1) {
      return 0;
    }
    boolean bought = false;
    int buyPrice = 0;
    int sum = 0;
    for (int i = 1; i < prices.length; i++) {
      int prev = prices[i-1];
      int next = prices[i];
      if (next > prev && !bought) {
        bought = true;
        buyPrice = prev;
      } else if (next <= prev && bought) {
        bought = false;
        sum += prev - buyPrice;
      }
    }
    if (bought) {
      sum += prices[prices.length - 1] - buyPrice;
    }
    return sum;
  }
}
