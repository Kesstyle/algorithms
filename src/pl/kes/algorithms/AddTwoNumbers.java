package pl.kes.algorithms;

public class AddTwoNumbers {

  public static void main(String...args) {
    ListNode first = new ListNode(9);
    first.next = new ListNode(9);
    first.next.next = new ListNode(9);
    first.next.next.next = new ListNode(9);
    first.next.next.next.next = new ListNode(9);
    first.next.next.next.next.next = new ListNode(9);
    first.next.next.next.next.next.next = new ListNode(9);

    ListNode second = new ListNode(9);
    second.next = new ListNode(9);
    second.next.next = new ListNode(9);
    second.next.next.next = new ListNode(9);

    ListNode result = addTwoNumbers(first, second);
    while (result != null) {
      System.out.print(result.val + " -> ");
      result = result.next;
    }
  }

  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    int buffer = 0;
    if (l1 == null && l2 == null) {
      return null;
    }
    if (l1 == null) {
      return l2;
    }
    if (l2 == null) {
      return l1;
    }

    ListNode current1 = l1;
    ListNode current2 = l2;
    ListNode result = null;
    ListNode currentResultNode = null;
    while (current1 != null && current2 != null) {
      int nextNumber = current1.val + current2.val + buffer;
      if (nextNumber >= 10) {
        nextNumber -= 10;
        buffer = 1;
      } else {
        buffer = 0;
      }
      if (result == null) {
        result = new ListNode(nextNumber);
        currentResultNode = result;
      } else {
        currentResultNode.next = new ListNode(nextNumber);
        currentResultNode = currentResultNode.next;
      }
      current1 = current1.next;
      current2 = current2.next;
    }

    if (current1 != null) {
      while (current1 != null) {
        int nextNumber = current1.val + buffer;
        if (nextNumber >= 10) {
          buffer = 1;
          nextNumber -= 10;
        } else {
          buffer = 0;
        }
        currentResultNode.next = new ListNode(nextNumber);
        currentResultNode = currentResultNode.next;
        current1 = current1.next;
      }
    }
    if (current2 != null) {
      while (current2 != null) {
        int nextNumber = current2.val + buffer;
        if (nextNumber >= 10) {
          buffer = 1;
          nextNumber -= 10;
        } else {
          buffer = 0;
        }
        currentResultNode.next = new ListNode(nextNumber);
        currentResultNode = currentResultNode.next;
        current2 = current2.next;
      }
    }
    if (buffer != 0) {
      currentResultNode.next = new ListNode(buffer);
    }
    return result;
  }
}

class ListNode {
  int val;
  ListNode next;

  ListNode() {
  }

  ListNode(int val) {
    this.val = val;
  }

  ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }
}
