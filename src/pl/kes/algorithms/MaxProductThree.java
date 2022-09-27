package pl.kes.algorithms;

import java.util.Arrays;

public class MaxProductThree {

  public static void main(String... args) {
    MaxProductThree maxProductThree = new MaxProductThree();
    int[] nums = new int[] {1, 2, 3, 4};
    System.out.println(maxProductThree.maximumProduct(nums));
  }

  public int maximumProduct(int[] nums) {
    Arrays.sort(nums);
    int max = nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3];
    int negMax = nums[0] * nums[1] * nums[nums.length - 1];
    return Math.max(max, negMax);
  }
}
