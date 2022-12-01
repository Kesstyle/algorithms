package pl.kes.algorithms;

public class IncreasingTripletSubsequence {

  public static void main(String... args) {
    IncreasingTripletSubsequence tripletSubsequence = new IncreasingTripletSubsequence();
    int[] nums = new int[] {5, 4, 6, 1, 6, 4, 3};
    System.out.println(tripletSubsequence.increasingTriplet(nums));
  }

  public boolean increasingTriplet(int[] nums) {
    if (nums.length < 3) {
      return false;
    }
    int i = 0;
    while (i + 1 < nums.length && nums[i] >= nums[i + 1]) {
      i++;
    }
    if (i >= nums.length - 2) {
      return false;
    }
    int currMin = nums[i];
    int currSec = nums[i + 1];
    i += 2;
    int potMin = Integer.MAX_VALUE;
    while (i < nums.length) {
      if (nums[i] > currSec) {
        return true;
      } else if (nums[i] < currSec && nums[i] > currMin) {
        currSec = nums[i];
      } else if (nums[i] < potMin && nums[i] < currMin) {
        potMin = nums[i];
      } else if (nums[i] > potMin && nums[i] < currSec) {
        currMin = potMin;
        currSec = nums[i];
      }
      i++;
    }
    return false;
  }
}
