package pl.kes.algorithms;

public class SortColors {

  public static void main(String...args) {
    SortColors sortColors = new SortColors();
    int[] nums = new int[] {0, 1, 2, 2, 1, 1, 0 ,1, 1, 0, 0, 1, 2};
    sortColors.sortColors(nums);
    for (int i = 0; i < nums.length; i++) {
      System.out.print(nums[i] + " ");
    }
  }

  public void sortColors(int[] nums) {
    int zeroCounts = 0;
    int oneCounts = 0;
    int twoCounts = 0;
    for (int n = 0; n < nums.length; n++) {
      if (nums[n] == 0) {
        zeroCounts++;
      } else if (nums[n] == 1) {
        oneCounts++;
      } else {
        twoCounts++;
      }
    }

    for (int i = 0; i < nums.length; i++) {
      if (i < zeroCounts) {
        nums[i] = 0;
      } else if (i >= zeroCounts && i < zeroCounts + oneCounts) {
        nums[i] = 1;
      } else {
        nums[i] = 2;
      }
    }
  }

}
