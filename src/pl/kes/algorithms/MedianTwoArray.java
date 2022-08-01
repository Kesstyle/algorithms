package pl.kes.algorithms;

public class MedianTwoArray {

  public static void main(String... args) {
    MedianTwoArray medianTwoArray = new MedianTwoArray();
    int[] nums1 = new int[] {1, 2};
    int[] nums2 = new int[] {3, 4};
    double result = medianTwoArray.findMedianSortedArrays(nums1, nums2);
    System.out.println(result);
  }

  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int length1 = nums1.length;
    int length2 = nums2.length;
    if (length1 == 0) {
      if (length2 % 2 == 1) {
        return nums2[length2 / 2];
      } else {
        return (Double.valueOf(nums2[length2 / 2]) + Double.valueOf(nums2[length2 / 2 - 1])) / 2;
      }
    }

    if (length2 == 0) {
      if (length1 % 2 == 1) {
        return nums1[length1 / 2];
      } else {
        return (Double.valueOf(nums1[length1 / 2]) + Double.valueOf(nums1[length1 / 2 - 1])) / 2;
      }
    }

    int totalLength = length1 + length2;
    if (nums1[length1 - 1] < nums2[0]) {
      int median = (length1 + length2) / 2;
      // get (n+m)/2 elem or avg 2 elems
    }

    boolean even = totalLength % 2 == 0;
    int[] middle = new int[2];
    int m = 0;
    int start = 0;
    int end = nums1.length;
    int k;
    do {
      if (m >= 2) {
        break;
      }
      k = (end - start) / 2;
      int number = nums1[start + k];
      int lowerNumber1 = numberOfLower(number, nums1);
      int lowerNumber2 = numberOfLower(number, nums2);
      int higherNumber1 = numberOfHigher(number, nums1);
      int higherNumber2 = numberOfHigher(number, nums2);
      int totalLeft = lowerNumber1 + lowerNumber2;
      int totalRight = higherNumber1 + higherNumber2;
      int equalNumber = totalLength - totalLeft - totalRight;
      if (Math.abs(totalLeft - totalRight) < equalNumber
        || even && Math.abs(totalLeft - totalRight) <= equalNumber) {
        if (m == 1) {
          int existing = middle[0];
          if (existing != number) {
            middle[m++] = number;
          }
        } else {
          middle[m++] = number;
        }
      }
      if (totalLeft > totalRight) {
        end -= k;
      } else {
        start += k;
      }
    } while (k > 0);

    start = 0;
    end = nums2.length;
    do {
      if (m >= 2) {
        break;
      }
      k = (end - start) / 2;
      int number = nums2[start + k];
      int lowerNumber1 = numberOfLower(number, nums1);
      int lowerNumber2 = numberOfLower(number, nums2);
      int higherNumber1 = numberOfHigher(number, nums1);
      int higherNumber2 = numberOfHigher(number, nums2);
      int totalLeft = lowerNumber1 + lowerNumber2;
      int totalRight = higherNumber1 + higherNumber2;
      int equalNumber = totalLength - totalLeft - totalRight;
      if (Math.abs(totalLeft - totalRight) < equalNumber
        || even && Math.abs(totalLeft - totalRight) <= equalNumber) {
        if (m == 1) {
          int existing = middle[0];
          if (existing != number) {
            middle[m++] = number;
          }
        } else {
          middle[m++] = number;
        }
      }
      if (totalLeft > totalRight) {
        end -= k;
      } else {
        start += k;
      }
    } while (k > 0);

    if (m == 1) {
      return middle[0];
    } else {
      return (Double.valueOf(middle[0]) + Double.valueOf(middle[1])) / 2;
    }
  }

  private int numberOfLower(int number, int[] nums) {
    int start = 0;
    int end = nums.length;
    while (end - start > 1) {
      int k = (end - start) / 2;
      if (nums[start + k] < number) {
        start += k;
      } else if (nums[start + k] >= number) {
        end -= k;
      }
    }
    if (nums[start] >= number) {
      return end - 1;
    }
    return end;
  }

  private int numberOfHigher(int number, int[] nums) {
    int start = 0;
    int end = nums.length;
    while (end - start > 1) {
      int k = (end - start) / 2;
      if (nums[start + k] <= number) {
        start += k;
      } else if (nums[start + k] > number) {
        end -= k;
      } else {
        return start + k;
      }
    }
    if (nums[start] > number) {
      return nums.length - end + 1;
    }
    return nums.length - end;
  }

}
