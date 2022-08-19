package pl.kes.algorithms;

public class PeakElement {

  public static void main(String...args) {
    PeakElement peakElement = new PeakElement();
    int[] nums = new int[] {1,2,3,4,5,6,7,9,8,7};
    int peak = peakElement.findPeakElement(nums);
    System.out.println(peak);
  }

  public int findPeakElement(int[] nums) {
    if (nums.length == 1) {
      return 0;
    }
    int n = nums.length;
    if (nums[0] > nums[1]) {
      return 0;
    }
    if (nums[n-1] > nums[n-2]) {
      return n-1;
    }
    int start = 0;
    int end = n-1;
    while (true) {
      int index = start + (end - start) / 2;
      if (nums[index] > nums[index-1] && nums[index] > nums[index+1]) {
        return index;
      }
      if (nums[index] < nums[index-1]) {
        end = index;
      } else {
        start = index;
      }
    }
  }
}
