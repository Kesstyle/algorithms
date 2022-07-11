package pl.kes.algorithms;

public class Container {

  public static void main(String...args) {
    final int[] length = new int[] {1,8,6,2,5,4,8,3,7};
    System.out.println(maxArea(length));
  }

  public static int maxArea(int[] arr) {
    final int n = arr.length;
    if (n <= 1) {
      return 0;
    }
    if (n == 2) {
      return Math.min(arr[0], arr[1]);
    }

    int max = 0;
    int i = 0;
    int j = arr.length - 1;
    while (j - i != 0) {
      int localMulti = Math.min(arr[i], arr[j]) * (j - i);
      if (localMulti > max) {
        max = localMulti;
      }
      if (arr[i] > arr[j]) {
        j--;
      } else {
        i++;
      }
    }
    return max;
  }
}
