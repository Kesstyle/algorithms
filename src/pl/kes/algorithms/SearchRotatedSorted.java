package pl.kes.algorithms;

public class SearchRotatedSorted {

  public static void main(String...args) {
    SearchRotatedSorted searchRotatedSorted = new SearchRotatedSorted();
    int[] nums = new int[] {1, 3};
    int target = 3;
    int res = searchRotatedSorted.search(nums, target);
    System.out.println(res);
    System.out.println(searchRotatedSorted.minIndex(nums));
  }

  public int search(int[] nums, int target) {
    if (nums.length == 1) {
      if (nums[0] == target) {
        return 0;
      } else {
        return -1;
      }
    }
    int beginOfArray = minIndex(nums);
    int n = nums.length;
    int start = beginOfArray;
    int end = beginOfArray + n - 1;

    while (end - start > 1) {
      int k = (end + start) / 2;
      int idx = k % n;
      if (nums[idx] == target) {
        return idx;
      }
      if (target > nums[idx]) {
        start = k;
      } else {
        end = k;
      }
    }
    if (target == nums[start % n]) {
      return start % n;
    }
    if (target == nums[end % n]) {
      return end % n;
    }
    return -1;
  }

  public int minIndex(int[] nums) {
    int beg = nums[0];
    int end = nums.length;
    int start = 0;
    while (end - start > 1) {
      int k = (end + start) / 2;
      if (beg < nums[k]) {
        start = k;
      } else {
        end = k;
      }
    }
    if (end >= nums.length) {
      return 0;
    }
    if (nums[start] < nums[end]) {
      return start;
    }
    return end;
  }
}
