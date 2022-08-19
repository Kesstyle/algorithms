package pl.kes.algorithms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

  public static void main(String...args) {
    TwoSum twoSum = new TwoSum();
    int[] nums = new int[] {4, 6, 7, 1, 8, 11, 5, 100, 4, 2, 6, 8, 9, 16, 18};
    twoSum.sort(nums, 0, nums.length - 1);
    Arrays.stream(nums).sequential().forEach(System.out::println);
  }

  public int[] twoSum(int[] nums, int target) {
    final Map<Integer, Integer> indexesMap = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      int number = nums[i];
      if (indexesMap.containsKey(target - number)) {
        return new int[] {indexesMap.get(target - number), i};
      } else {
        indexesMap.put(number, i);
      }
    }
    return null;
  }

  private void sort(int[] nums, int begin, int end) {
    if (end - begin < 1) {
      return;
    }
    int pivotNumber = nums[begin];
    int i = begin + 1;
    int j = end;
    while (i <= end && j >= begin) {
      while (i <= end && nums[i] < pivotNumber) {
        i++;
      }
      while (j >= begin && nums[j] > pivotNumber) {
        j--;
      }
      if (i < j) {
        swap(nums, i, j);
      } else {
        swap(nums, begin, j);
        break;
      }
    }
    sort(nums, begin, j - 1);
    sort(nums, j + 1, end);
  }

  private void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }
}
