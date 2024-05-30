package pl.kes.algorithms;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class RangeModule {

  public static void main(String...args) {
    RangeModule rangeModule = new RangeModule();
    rangeModule.addRange(6, 8);
    rangeModule.removeRange(7, 8);
    rangeModule.removeRange(8, 9);
    rangeModule.addRange(8, 9);
    rangeModule.removeRange(1, 3);
    rangeModule.addRange(1, 8);
    rangeModule.queryRange(2, 4); // return True,(Every number in [10, 14) is being tracked)
    rangeModule.queryRange(2, 9); // return False,(Numbers like 14, 14.03, 14.17 in [13, 15) are not being tracked)
    rangeModule.queryRange(4, 6); // return True, (The number 16 in [16, 17) is still being tracked, despite the remove operation)
  }

  private Set<int[]> intervals;

  public RangeModule() {
    intervals = new TreeSet<>((a, b) -> compare(a, b));
  }

  public void addRange(int left, int right) {
    int[] range = new int[] {left, right};
    Iterator<int[]> iter = intervals.iterator();
    while (iter.hasNext()) {
      int[] interval = iter.next();
      if (interval[0] > range[1]) {
        break;
      }
      if (intersects(range, interval)) {
        range[0] = Math.min(range[0], interval[0]);
        range[1] = Math.max(range[1], interval[1]);
        iter.remove();
      }
    }
    intervals.add(range);
  }

  public boolean queryRange(int left, int right) {
    int[] range = new int[] {left, right};
    for (int[] interval: intervals) {
      if (fullyBelongs(range, interval)) {
        return true;
      }
    }
    return false;
  }

  public void removeRange(int left, int right) {
    int[] range = new int[] {left, right};
    Iterator<int[]> iter = intervals.iterator();
    int[] addInter = null;
    while (iter.hasNext()) {
      int[] interval = iter.next();
      if (interval[0] > range[1]) {
        break;
      }
      if (!intersects(range, interval)) {
        continue;
      }
      boolean firstPlus = range[0] >= interval[0];
      boolean secondPlus = range[1] > interval[1];
      if (!firstPlus && secondPlus) {
        iter.remove();
      }
      if (!firstPlus && !secondPlus) {
        interval[0] = range[1];
      }
      if (firstPlus && secondPlus) {
        interval[1] = range[0];
      }
      if (firstPlus && !secondPlus) {
        addInter = new int[] {range[1], interval[1]};
        interval[1] = range[0];
        break;
      }
    }
    if (addInter != null) {
      intervals.add(addInter);
    }
  }

  private boolean fullyBelongs(int[] first, int[] second) {
    return first[0] >= second[0] && first[1] <= second[1];
  }

  private boolean intersects(int[] first, int[] second) {
    return !(first[0] < second[0] && first[1] < second[0] || second[0] < first[0] && second[1] < first[0]);
  }

  private int compare(int[] first, int[] second) {
    if (first[0] != second[0]) {
      return first[0] - second[0];
    }
    return first[1] - second[1];
  }
}
