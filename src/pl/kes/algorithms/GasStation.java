package pl.kes.algorithms;

public class GasStation {

  public static void main(String...args) {
    GasStation gasStation = new GasStation();
    int[] gas = new int[] {3,2};
    int[] cost = new int[] {2,3};
    System.out.print(gasStation.canCompleteCircuit(gas, cost));
  }

  public int canCompleteCircuit(int[] gas, int[] cost) {
    if (gas.length == 1) {
      return gas[0] - cost[0] >= 0? 0 : -1;
    }
    int start = 0;
    int end = 0;
    int sum = 0;
    while (true) {
      sum += gas[end] - cost[end];
 //     System.out.println("sum = " + sum + ", delta = " + delta[end] + " , start end " + start + " " + end);
      while (sum < 0) {
        if (start - end == 1) {
          return -1;
        }
        start--;
        if (start < 0) {
          start = gas.length - 1;
        }
        sum += gas[start] - cost[start];
  //      System.out.println("sum = " + sum + ", delta = " + delta[start] + " , start end " + start + " " + end);
      }
      if (start - end == 1 || end - start == gas.length - 1) {
        break;
      }
      end++;
      if (end == gas.length) {
        end = 0;
      }
    }
    if (sum < 0) {
      return -1;
    }
    return start;
  }
}
