package pl.kes.algorithms;

public class LargestNumber {

  public static void main(String...args) {
    LargestNumber largestNumber = new LargestNumber();
    final int[] nums = new int[] {1,2,3,4,5,6,7,8,9,0};
    final String result = largestNumber.largestNumber(nums);
    System.out.println(result);
  }

  int[] arr1 = new int[10];
  int[] arr2 = new int[10];

  public String largestNumber(int[] nums) {
    sort(nums, 0, nums.length - 1);
    StringBuilder resultBuilder = new StringBuilder();
    for (int i = 0; i < nums.length; i++) {
      resultBuilder.append(nums[i]);
    }
    int j = 0;
    while (resultBuilder.charAt(j) == '0' && j < nums.length - 1) {
      j++;
    }
    resultBuilder.delete(0, j);
    return resultBuilder.toString();
  }

  public void sort(int[] nums, int begin, int end) {
    if (end - begin < 1) {
      return;
    }
    int pivotIndex = begin;
    int pivotValue = nums[begin];
    int i = begin + 1;
    int j = end;

    while (i <= end && j >= begin) {
      while (i <= end && compare(pivotValue, nums[i]) <= 0) {
        i++;
      }
      while (j >= begin && compare(nums[j], pivotValue) < 0) {
        j--;
      }
      if (j < i) {
        swap(nums, pivotIndex, j);
        break;
      } else {
        swap(nums, i, j);
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

  private int compare(int a, int b) {
    int size1 = 0;
    int size2 = 0;
 //   System.out.println("a b:" + a + " " + b);
    if (a == 0 && b == 0) {
      return 0;
    }
    if (a == 0) {
      return -1;
    }
    if (b == 0) {
      return 1;
    }
    while (a > 0) {
      arr1[size1++] = a%10;
      a /= 10;
    }
    while (b > 0) {
      arr2[size2++] = b%10;
      b /= 10;
    }

    int i = size1 - 1;
    int j = size2 - 1;
  //  System.out.println("size1 size2 a b:" + size1 + " " + size2 + " , " + a + " " + b);
    while (true) {
   //   System.out.println(i + " " + j);
      if (arr1[i] > arr2[j]) {
        return 1;
      } else if (arr1[i] < arr2[j]) {
        return -1;
      }

      if (i == 0 && j == 0) {
        return 0;
      }
      i--;
      j--;
      if (i < 0) {
        i = size1 - 1;
      }
      if (j < 0) {
        j = size2 - 1;
      }
    }
  }
}
