package pl.kes.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations {

  public static void main(String...args) {
    Permutations permutations = new Permutations();
    int[] nums = new int[] {0, 1};
    List<List<Integer>> result = permutations.permute(nums);
    for (List<Integer> innerList: result) {
      System.out.print("[");
      for (Integer elem: innerList) {
        System.out.print(elem + " ");
      }
      System.out.print("]");
    }
  }

  public List<List<Integer>> permute(int[] nums) {
    if (nums.length <= 1) {
      List<Integer> result = new ArrayList<>();
      result.add(nums[0]);
      return new ArrayList<>(List.of(result));
    }
    List<List<Integer>> resultList = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      int[] newArray = new int[nums.length - 1];
      int j = 0;
      int k = 0;
      while (j != nums.length - 1) {
        if (i != k) {
          newArray[j++] = nums[k];
        }
        k++;
      }
      List<List<Integer>> tmp = permuteWithHead(newArray, nums[i]);
      resultList.addAll(tmp);
    }
    return resultList;
  }


  private List<List<Integer>> permuteWithHead(int[] nums, int tail) {
    if (nums.length <= 1) {
      List<Integer> result = new ArrayList<>();
      result.add(nums[0]);
      result.add(tail);
      return new ArrayList<>(List.of(result));
    }

    List<List<Integer>> resultList = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      int[] newArray = new int[nums.length - 1];
      int j = 0;
      int n = 0;
      while (j != nums.length - 1) {
        if (i != n) {
          newArray[j++] = nums[n];
        }
        n++;
      }
      List<List<Integer>> tmp = permuteWithHead(newArray, nums[i]);
      for (int k = 0; k < tmp.size(); k++) {
        tmp.get(k).add(tail);
      }
      resultList.addAll(tmp);
    }
    return resultList;
  }
}
