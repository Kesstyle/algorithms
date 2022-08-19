package pl.kes.algorithms;

public class OddEvenLinkedList {

  public static void main(String...args) {
    OddEvenLinkedList oddEvenLinkedList = new OddEvenLinkedList();
    ListNode head = null;

    int[] nums = new int[] {2,1,3,5,6,4,7};

    ListNode tmp = null;
    for (int i = 0; i < nums.length; i++) {
      if (head == null) {
        head = new ListNode(nums[i]);
        tmp = head;
        continue;
      } else {
        tmp.next = new ListNode(nums[i]);
        tmp = tmp.next;
      }
    }

    ListNode result = oddEvenLinkedList.oddEvenList(head);
    while (result != null) {
      System.out.println(result.val);
      result = result.next;
    }
  }

  public ListNode oddEvenList(ListNode head) {
    if (head == null) {
      return null;
    }
    if (head.next == null || head.next.next == null) {
      return head;
    }
    int i = 2;
    ListNode odd = head;
    ListNode even = null;
    ListNode evenHead = null;
    ListNode next = head.next;
    while (next != null) {
      if (i++ % 2 == 0) {
        if (even == null) {
          even = next;
          evenHead = next;
        } else {
          even.next = next;
          even = even.next;
        }
      } else {
        odd.next = next;
        odd = odd.next;
      }
      next = next.next;
    }
    odd.next = evenHead;
    even.next = null;
    return head;
  }

}
