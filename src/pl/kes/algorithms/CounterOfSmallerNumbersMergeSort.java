package pl.kes.algorithms;

import java.util.List;

public class CounterOfSmallerNumbersMergeSort {

  public static void main(String... args) {
    CounterOfSmallerNumbersMergeSort counterOfSmallerNumbers = new CounterOfSmallerNumbersMergeSort();
    int[] nums = new int[] {6, 5, 4, 5, 2, 1};
    List<Integer> res = counterOfSmallerNumbers.countSmaller(nums);
    for (int i = 0; i < res.size(); i++) {
      System.out.print(res.get(i) + " ");
    }
  }

  int n;
  int[] aux;
  Integer[] res;
  int[] indexes;
  int[] indexesTmp;

  public List<Integer> countSmaller(int[] nums) {
    n = nums.length;
    res = new Integer[n];
    aux = new int[n];
    indexes = new int[n];
    indexesTmp = new int[n];
    for (int i = 0; i < n; i++) {
      res[i] = 0;
      indexes[i] = i;
    }
    mergeSort(nums, 0, n - 1);
    return List.of(res);
  }

  private void mergeSort(int[] nums, int begin, int end) {
    if (end <= begin) {
      return;
    }
    int mid = begin + (end - begin) / 2;
    mergeSort(nums, begin, mid);
    mergeSort(nums, mid + 1, end);
    int left = begin;
    int right = mid + 1;
    int buffer = 0;
    for (int i = begin; i <= end; i++) {
      aux[i] = nums[i];
      indexesTmp[i] = indexes[i];
    }
    for (int i = begin; i <= end; i++) {
      if (right > end) {
        res[indexesTmp[left]] += buffer;
        move(nums, i, left++);
        continue;
      }
      if (left > mid) {
        move(nums, i, right++);
        continue;
      }

      if (aux[right] < aux[left]) {
        buffer++;
        move(nums, i, right++);
      } else {
        res[indexesTmp[left]] += buffer;
        move(nums, i, left++);
      }
    }
  }

  private void move(int[] nums, int i, int j) {
    nums[i] = aux[j];
    indexes[i] = indexesTmp[j];
  }
}
