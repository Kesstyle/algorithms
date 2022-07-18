package pl.kes.algorithms;

public class CanJump {

  public static void main(String...args) {
    System.out.println(canJump(new int[] {0, 2, 3}));
  }

  public static boolean canJump(int[] nums) {
    if (nums.length <= 1) {
      return true;
    }
    if (nums.length == 2) {
      return nums[0] > 0;
    }

    int required = 1;
    for (int i = nums.length - 2; i >= 0; i--) {
      if (nums[i] < required) {
        required++;
      } else {
        required = 1;
      }
    }
    return required == 1;
  }
}
