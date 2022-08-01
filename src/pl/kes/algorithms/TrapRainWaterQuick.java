package pl.kes.algorithms;

public class TrapRainWaterQuick {


  public static void main(String...args) {
    System.out.println(trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
  }

  public static int trap(int[] height) {
    int n = height.length;
    int[] maxHeightLeft = new int[n];
    int[] maxHeightRight = new int[n];

    int maxLeft = 0;
    int maxRight = 0;
    int i = 0;
    int j = n - 1;
    while (i < n) {
      maxHeightLeft[i] = maxLeft;
      if (height[i] > maxLeft) {
        maxLeft = height[i];
      }
      i++;

      maxHeightRight[j] = maxRight;
      if (height[j] > maxRight) {
        maxRight = height[j];
      }
      j--;
    }

    int result = 0;
    for (int k = 0; k < n; k++) {
      int water = Math.min(maxHeightLeft[k], maxHeightRight[k]) - height[k];
      if (water > 0) {
        result += water;
      }
    }
    return result;
  }

}
