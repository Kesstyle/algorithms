package pl.kes.algorithms;

import java.util.HashMap;
import java.util.Map;

public class LongestConsequtiveSequence {

  public static void main(String...args) {
    LongestConsequtiveSequence longestConsequtiveSequence = new LongestConsequtiveSequence();
    int[] nums = new int[] {0,3,7,2,5,8,4,6,0,1};
    int res = longestConsequtiveSequence.longestConsecutive(nums);
    System.out.println(res);
  }

  public int longestConsecutive(int[] nums) {
    if (nums.length <= 1) {
      return nums.length;
    }
    Map<Integer, Node> nextMap = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      nextMap.put(nums[i], new Node(nums[i], null));
    }
    for (Map.Entry<Integer, Node> entry: nextMap.entrySet()) {
      Node node = entry.getValue();
      Node nextNode = nextMap.get(entry.getKey() + 1);
      node.next = nextNode;
    }
    int max = -1;
    for (Map.Entry<Integer, Node> entry: nextMap.entrySet()) {
      if (entry.getValue().getSize() > max) {
        max = entry.getValue().getSize();
      }
    }
    return max;
  }

  class Node {
    int val;
    Node next;
    Integer size;

    public Node(int val, Node next) {
      this.val = val;
      this.next = next;
    }

    public int getSize() {
      if (size != null) {
        return size;
      }
      size = 1 + (next == null ? 0 : next.getSize());
      return size;
    }
  }
}
