package pl.kes.algorithms;

import java.util.Arrays;

public class HouseRobber {

  public static void main(String...args) {
    HouseRobber houseRobber = new HouseRobber();
    int[] nums = new int[] {2,7,9,3,1};
    int profit = houseRobber.rob(nums);
    System.out.println(profit);
  }

  int[] bounty;

  public int rob(int[] nums) {
    if (nums.length == 1) {
      return nums[0];
    }
    bounty = new int[nums.length];
    bounty[0] = Math.max(0, nums[0]);
    bounty[1] = Math.max(nums[1], nums[0]);
    for (int i = 2; i < nums.length; i++) {
      bounty[i] = Math.max(bounty[i-1], bounty[i - 2] + nums[i]);
    }
    return bounty[nums.length - 1];
  }
}
