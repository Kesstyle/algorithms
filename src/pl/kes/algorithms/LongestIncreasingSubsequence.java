package pl.kes.algorithms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestIncreasingSubsequence {

  public static void main(String...args) {
    LongestIncreasingSubsequence longestIncreasingSubsequence = new LongestIncreasingSubsequence();
    int[] seq = new int[] {10,9,2,5,3,7,101,18};
    int result = longestIncreasingSubsequence.lengthOfLIS(seq);
    System.out.println(result);
  }

  public int lengthOfLIS(int[] nums) {
    int[] LIS = new int[nums.length];
    Arrays.fill(LIS,1);
    for(int i = nums.length -1; i >=0; i--){
      for(int j=i+1; j < nums.length; j++){
        if(nums[j] > nums[i]){
          LIS[i] = Math.max(LIS[i], 1 + LIS[j]);
        }
      }
    }

    int ans =0;
    for(int i = 0; i < nums.length; i++){
      ans = Math.max(ans, LIS[i]);
    }
    return ans;
  }
}
