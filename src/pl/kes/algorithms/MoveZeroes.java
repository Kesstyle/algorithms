package pl.kes.algorithms;

public class MoveZeroes {

  public static void main(String...args) {

  }

  public static void moveZeroes(int[] nums) {
    int count = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 0) {
        continue;
      }
      if (count != i) {
        nums[count] = nums[i];
      }
      count++;
    }
    for (int i = count; i < nums.length; i++) {
      nums[i] = 0;
    }
  }
}
