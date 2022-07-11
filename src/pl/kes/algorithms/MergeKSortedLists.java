package pl.kes.algorithms;

import java.util.Arrays;
import pl.kes.algorithms.util.ListNode;

public class MergeKSortedLists {

  public static void main(String...args) {
    ListNode first = new ListNode(1);
    first.next = new ListNode(4);
    first.next.next = new ListNode(5);

    ListNode second = new ListNode(1);
    second.next = new ListNode(3);
    second.next.next = new ListNode(4);

    ListNode third = new ListNode(2);
    third.next = new ListNode(6);

    ListNode result = mergeKLists(new ListNode[] {first, second, third});
    ListNode tmp = result;
    while (tmp != null) {
 //     System.out.print(tmp.val + ", ");
      tmp = tmp.next;
    }
  }

  public static ListNode mergeKLists(ListNode[] lists) {
    if (lists.length == 0) {
      return null;
    }
    int number = lists.length;

    while (number > 1) {
      int i = 0;
      int j = number - 1;
      while (i < j) {
        lists[i] = merge2Lists(lists[i], lists[j]);
        i++;
        j--;
        number--;
      }
    }
    return lists[0];
  }

  private static ListNode merge2Lists(ListNode first, ListNode second) {
    if (first == null) {
      return second;
    }
    if (second == null) {
      return first;
    }
    ListNode result = null;
    ListNode resultTail = null;
    ListNode firstNext = first;
    ListNode secondNext = second;
    while (firstNext != null && secondNext != null) {
 //     System.out.println("Comparing " + firstNext.val + " and " + secondNext.val);
      if (firstNext.val < secondNext.val) {
        if (result == null) {
          result = firstNext;
          resultTail = firstNext;
        } else {
          resultTail.next = firstNext;
          resultTail = resultTail.next;
        }
 //       System.out.println("Adding " + firstNext.val);
        firstNext = firstNext.next;
      } else {
        if (result == null) {
          result = secondNext;
          resultTail = secondNext;
        } else {
          resultTail.next = secondNext;
          resultTail = resultTail.next;
        }
 //       System.out.println("Adding " + secondNext.val);
        secondNext = secondNext.next;
      }
    }

    while (firstNext != null) {
      resultTail.next = firstNext;
      resultTail = resultTail.next;
      firstNext = firstNext.next;
    }

    while (secondNext != null) {
      resultTail.next = secondNext;
      resultTail = resultTail.next;
      secondNext = secondNext.next;
    }
    return result;
  }
}
