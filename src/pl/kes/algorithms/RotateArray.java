package pl.kes.algorithms;

public class RotateArray {

  public static void main(String...args) {
    RotateArray rotateArray = new RotateArray();
    int[] array = new int[] {1,2,3,4,5,6};
    int k = 4;
    rotateArray.rotate(array, k);
    for (int i = 0; i < array.length; i++) {
      System.out.print(array[i] + " ");
    }
  }

  public void rotate(int[] nums, int k) {
    int n = nums.length;
    if (k % n == 0) {
      return;
    }
    int sum = 0;
    int accum = 0;
    for (int i = 0; i < n; i++) {
      sum += accum / k;
      if (sum >= n) {
        break;
      }
      accum = k;
      while (accum % n != 0) {
        swap(nums, i, (i + accum) % n);
        accum += k;
      }
    }
  }

  private void swap(int[] nums, int i, int j) {
    nums[i] = nums[i] + nums[j];
    nums[j] = nums[i] - nums[j];
    nums[i] = nums[i] - nums[j];
  }
}
