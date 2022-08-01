package pl.kes.algorithms;

public class TrapRainWater {

  public static void main(String...args) {
    System.out.println(trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
  }

  public static int trap(int[] height) {
    return trap(height, 0, height.length - 1);
  }

  public static int trap(int[] height, int start, int end) {
    if (end - start <= 1) {
      // it will not collect anything...
      return 0;
    }

    // Skip increasing sequence in the beginning - it will result in 0
    int newStart = start;
    int localMax = height[start];
    for (int i = start + 1; i <= end; i++) {
      if (height[i] >= localMax) {
        newStart = i;
        localMax = height[i];
      } else {
        break;
      }
    }

    // Skip increasing sequence in the end - it will result in 0
    int newEnd = end;
    localMax = height[end];
    for (int i = end - 1; i >= newStart; i--) {
      if (height[i] >= localMax) {
        newEnd = i;
        localMax = height[i];
      } else {
        break;
      }
    }

    if (newStart != start || newEnd != end) {
      return trap(height, newStart, newEnd);
    }

    int startValue = height[start];
    int endValue = height[end];

    int maxInternalIndex = findMaxInternalIndex(height, start, end);
    if (maxInternalIndex >= 0) {
      return trap(height, start, maxInternalIndex) + trap(height, maxInternalIndex, end);
    }

    // looks like it's sinle container now
    int result = 0;
    int min = startValue;
    if (endValue < min) {
      min = endValue;
    }
    for (int i = start + 1; i < end; i++) {
      result += min - height[i];
    }
    return result;
  }

  private static int findMaxInternalIndex(int[] height, int start, int end) {
    System.out.println("start = " + start + ", end = " + end);
    if (height.length <= 2) {
      return -1;
    }
    int startValue = height[start];
    int endValue = height[end];
    int max = -1;
    int maxIndex = -1;
    int min = startValue;
    if (endValue < min) {
      min = endValue;
    }
    for (int i = start + 1; i < end; i++) {
      if (height[i] >= min && height[i] > max) {
        max = height[i];
        maxIndex = i;
      }
    }
    return maxIndex;
  }
}
