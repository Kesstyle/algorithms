package pl.kes.algorithms;

public class RussianDollEnvelopes {

  public static void main(String...args) {
    RussianDollEnvelopes russianDollEnvelopes = new RussianDollEnvelopes();
    int[][] envelopes = new int[][] {{4,5},{4,6},{6,7},{2,3},{1,1},{1,1}};
    int result = russianDollEnvelopes.maxEnvelopes(envelopes);
    System.out.println(result);
  }

  // 3 3 5 2 0 0
  // 1 1 0 3 4 4

  int[] dpOut;
  int n;

  public int maxEnvelopes(int[][] envelopes) {
    n = envelopes.length;
    dpOut = new int[n];
    dpOut[0] = 1;
    for (int i = 1; i < n; i++) {
      dpOut[i] = 1;
      for (int j = i - 1; j >= 0; j--) {
        if (canPutOutside(envelopes[i], envelopes[j])) {
          dpOut[i] = Math.max(dpOut[i], dpOut[j] + 1);
        }
      }
      for (int j = 0; j <= i - 1; j++) {
        if (canPutOutside(envelopes[j], envelopes[i])) {
          dpOut[j] = Math.max(dpOut[j] + 1, dpOut[i] + 1);
        }
      }
    }

    int res = 0;
    for (int i = 0; i < n; i ++) {
      res = Math.max(res, dpOut[i]);
    }
    return res;
  }

  private boolean canPutOutside(int[] source, int[] dest) {
    return source[0] > dest[0] && source[1] > dest[1];
  }
}
