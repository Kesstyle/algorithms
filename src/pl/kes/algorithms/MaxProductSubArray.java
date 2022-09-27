package pl.kes.algorithms;

public class MaxProductSubArray {

  public static void main(String...args) {
    MaxProductSubArray maxProductSubArray = new MaxProductSubArray();
    int[] nums = new int[] {-2,-4,-4};
    System.out.println(maxProductSubArray.maxProduct(nums));
  }

  public int maxProduct(int[] nums) {
    if (nums.length == 1) {
      return nums[0];
    }
    int multi1 = 1;
    int multi2 = 1;
    int max = Integer.MIN_VALUE;
    int n = nums.length;
    for (int i = 0; i < n; i++) {
      int num = nums[i];
      if (max < num) {
        max = num;
      }

      if (num > 0) {
        multi1 *= num;
        multi2 *= num;
      } else if (num < 0) {
        if (multi1 < 0 ^ multi2 < 0) {
          multi1 *= num;
          multi2 *= num;
        } else {
          multi1 *= num;
          multi2 = Integer.MIN_VALUE;
        }
      } else {
        multi1 = Integer.MIN_VALUE;
        multi2 = Integer.MIN_VALUE;
      }

      if (max < multi1) {
        max = multi1;
      }
      if (max < multi2) {
        max = multi2;
      }
      if (multi1 == Integer.MIN_VALUE) {
        multi1 = 1;
      }
      if (multi2 == Integer.MIN_VALUE) {
        multi2 = 1;
      }
    }
    return max;
  }
}
