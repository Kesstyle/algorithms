package pl.kes.algorithms;

public class FindDuplicateNumber {

  public static void main(String...args) {
    FindDuplicateNumber findDuplicateNumber = new FindDuplicateNumber();
    int[] nums = new int[] {3,1,3,4,2};
    int res = findDuplicateNumber.findDuplicate(nums);
    System.out.println(res);
  }

  public int findDuplicate(int[] nums) {
    int fast = 0;
    int slow = 0;
    do {
      slow = nums[slow];
      fast = nums[nums[fast]];
    } while (slow != fast);

    slow = 0;
    while (slow != fast) {
      slow = nums[slow];
      fast = nums[fast];
    }
    return slow;
  }
}
