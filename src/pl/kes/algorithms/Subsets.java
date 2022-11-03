package pl.kes.algorithms;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

  public static void main(String...args) {
    Subsets subsets = new Subsets();
    int[] nums = new int[] {1, 2, 3};
    List<List<Integer>> result = subsets.subsets(nums);
    for (int i = 0; i < result.size(); i++) {
      System.out.print("[");
      for (int j = 0; j < result.get(i).size(); j++) {
        System.out.print(result.get(i).get(j) + " ");
      }
      System.out.println("]");
    }
  }

  List<List<Integer>> newRes = new ArrayList<>();

  public List<List<Integer>> subsets(int[] nums) {
    return subsets(nums, nums.length - 1);
  }

  private List<List<Integer>> subsets(int[] nums, int i) {
    if (i >= nums.length) {
      return subsets(nums, nums.length);
    }
    if (i < 0) {
      return new ArrayList<>(List.of(new ArrayList<>()));
    }
    List<List<Integer>> prevRes = subsets(nums, i - 1);
    newRes.clear();
    for (int j = 0; j < prevRes.size(); j++) {
      List<Integer> item = new ArrayList<>();
      item.add(nums[i]);
      item.addAll(prevRes.get(j));
      newRes.add(item);
    }
    prevRes.addAll(newRes);
    return prevRes;
  }
}
