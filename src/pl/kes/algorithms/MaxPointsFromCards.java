package pl.kes.algorithms;

public class MaxPointsFromCards {



  public int maxScore(int[] cardPoints, int k) {
    int sum = 0;
    for (int i = 0; i < k; i++) {
      sum += cardPoints[i];
    }
    if (k == cardPoints.length) {
      return sum;
    }
    int max = sum;
    int n = cardPoints.length;
    for (int i = 1; i <= k; i++) {
      sum = sum - cardPoints[k - i] + cardPoints[n - i];
      if (sum > max) {
        max = sum;
      }
    }
    return max;
  }

}
