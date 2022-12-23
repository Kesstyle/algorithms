package pl.kes.algorithms;

import java.util.Arrays;
import java.util.List;

public class MinimumTimeDifference {

  public static void main(String...args) {
    MinimumTimeDifference minimumTimeDifference = new MinimumTimeDifference();
    String[] time = new String[] {"00:00","23:59","00:00"};
    System.out.println(minimumTimeDifference.findMinDifference(Arrays.asList(time)));
  }

  public int findMinDifference(List<String> timePoints) {
    String[] times = timePoints.toArray(new String[timePoints.size()]);
    sort(times, 0, times.length - 1);
    int minDiff = Integer.MAX_VALUE;
    for (int i = 1; i < times.length; i++) {
      int diff = Math.abs(compareTime(times[i], times[i - 1]));
      if (diff < minDiff) {
        minDiff = diff;
      }
    }
    return Math.min(minDiff, compareTime(add24h(times[0]), times[times.length - 1]));
  }

  private String add24h(String time) {
    int hour = Integer.parseInt(time.substring(0, 2));
    int min = Integer.parseInt(time.substring(3, 5));
    return (hour + 24) + ":" + (min > 9 ? min : "0" + min);
  }

  private void sort(String[] timePoints, int begin, int end) {
    if (begin >= end) {
      return;
    }
    String pivot = timePoints[begin];
    int lt = begin;
    int gt = end + 1;
    while (lt < gt) {
      while (compareTime(timePoints[++lt], pivot) < 0) {
        if (lt == end) {
          break;
        }
      }
      while (compareTime(pivot, timePoints[--gt]) < 0) {
        if (gt == begin) {
          break;
        }
      }
      if (lt >= gt) {
        break;
      }
      swap(timePoints, lt, gt);
    }
    swap(timePoints, begin, gt);
    sort(timePoints, begin, gt - 1);
    sort(timePoints, gt + 1, end);
  }

  private void swap(String[] arr, int i, int j) {
    String tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  private int compareTime(String time1, String time2) {
    int hour1 = Integer.parseInt(time1.substring(0, 2));
    int hour2 = Integer.parseInt(time2.substring(0, 2));
    int min1 = Integer.parseInt(time1.substring(3, 5));
    int min2 = Integer.parseInt(time2.substring(3, 5));
    return 60 * (hour1 - hour2) + min1 - min2;
  }
}
