package pl.kes.algorithms;

public class KthSmallestInArray {

  public static void main(String...args) {
    KthSmallestInArray kthSmallestInArray = new KthSmallestInArray();
    int[] nums = new int[] {3,2,3,1,2,4,5,5,6};
    int k = 4;
    int res = kthSmallestInArray.findKthLargest(nums, k);
    System.out.println(res);
  }

  public int findKthLargest(int[] nums, int k) {
    int l1 = -10000;
    int l2 = 10000;
    int mid = (l1 + l2) / 2;
    int k1 = 0;
    int k3 = 0;
    while (true) {
      k1 = 0;
      k3 = 0;
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] > mid) {
          k1++;
        } else if (nums[i] == mid) {
          k3++;
        }
      }
      if (k1 >= k) {
        l1 = mid;
        mid = (l1 + l2) / 2;
      } else if (k1 + k3 >= k) {
        return mid;
      } else {
        l2 = mid;
        mid = (l1 + l2) / 2;
      }
    }
  }
}
