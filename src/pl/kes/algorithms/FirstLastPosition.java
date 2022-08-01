package pl.kes.algorithms;

public class FirstLastPosition {

  public static void main(String...args) {
    FirstLastPosition firstLastPosition = new FirstLastPosition();
    int[] nums = new int[] {1,2,2};
    int[] result = firstLastPosition.searchRange(nums, 2);
    System.out.println(result[0] + " " + result[1]);
  }

  public int[] searchRange(int[] nums, int target) {
    if (nums.length == 0) {
      return new int[] {-1, -1};
    }
    int[] result = new int[] {posMin(nums, target), posMax(nums, target)};
    if (result[0] == -1 && result[1] != -1) {
      result[0] = result[1];
    } else if (result[1] == -1 && result[0] != -1) {
      result[1] = result[0];
    }
    if (result[0] > result[1]) {
      int tmp = result[0];
      result[0] = result[1];
      result[1] = tmp;
    }
    return result;
  }

  private int posMin(int[] nums, int target) {
    int begin = 0;
    int end = nums.length - 1;
    while (end - begin > 1) {
    //  System.out.println("begin end: " + begin + " " + end);
      int half = (end - begin) / 2;
      int index = begin + half;
      if (nums[index] >= target) {
        end = index;
      } else {
        begin = index;
      }
    }
 //   System.out.println("end = " + end);
    if (nums[begin] == target) {
      return begin;
    }
    if (nums[end] != target) {
      return -1;
    }
    return end;
  }

  private int posMax(int[] nums, int target) {
    int begin = 0;
    int end = nums.length - 1;
    while (end - begin > 1) {
  //    System.out.println("begin end: " + begin + " " + end);
      int half = (end - begin) / 2;
      int index = begin + half;
      if (nums[index] > target) {
        end = index;
      } else {
        begin = index;
      }
    }
 //   System.out.println("begin = " + begin);
    if (nums[end] == target) {
      return end;
    }
    if (nums[begin] != target) {
      return -1;
    }
    return begin;
  }
}
