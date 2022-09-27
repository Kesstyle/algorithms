package pl.kes.algorithms;

public class ProductOfArray {

  public static void main(String...args) {
    ProductOfArray productOfArray = new ProductOfArray();
    int[] nums = new int[] {-1,1,0,-3,3};
    int[] answer = productOfArray.productExceptSelf(nums);
    for (int i = 0; i < answer.length; i++) {
      System.out.print(answer[i] + " ");
    }
  }

  public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] leftProduct = new int[n];
    int product = 1;
    for (int i = 0; i < n; i++) {
      product *= nums[i];
      leftProduct[i] = product;
    }
    product = 1;
    for (int i = 0; i < n; i++) {
      product *= nums[n - i - 1];
      nums[n - i - 1] = product;
    }
    int[] answer = new int[n];
    for (int i = 0; i < n; i ++) {
      if (i == 0) {
        answer[i] = nums[1];
        continue;
      }
      if (i == n - 1) {
        answer[i] = leftProduct[n - 2];
        continue;
      }
      answer[i] = leftProduct[i - 1] * nums[i + 1];
    }
    return answer;
  }
}
