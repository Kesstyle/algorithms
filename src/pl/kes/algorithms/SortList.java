package pl.kes.algorithms;

public class SortList {

  public static void main(String... args) {
    SortList sortList = new SortList();
    int[] vals = new int[] {-1, 1, 13, 4, 10, 5, 0};
    ListNode head = null;
    ListNode x = null;
    for (int i = 0; i < vals.length; i++) {
      if (head == null) {
        head = new ListNode(vals[i]);
        x = head;
      } else {
        x.next = new ListNode(vals[i]);
        x = x.next;
      }
    }
    sortList.sortList(head);
    x = head;
    while (x != null) {
      System.out.println(x.val);
      x = x.next;
    }
  }

  public ListNode sortList(ListNode head) {
    if (head == null) {
      return null;
    }
    int i = 1;
    ListNode x = head;
    while (x.next != null) {
      i++;
      x = x.next;
    }
    ListNode result = sortPartOfList(head, i);
    return result;
  }

  private ListNode sortPartOfList(ListNode head, int size) {
    if (size == 1) {
      head.next = null;
      return head;
    }
    ListNode head2 = head;
    for (int i = 0; i < size / 2; i++) {
      head2 = head2.next;
    }
    ListNode head1 = sortPartOfList(head, size / 2);
    head2 = sortPartOfList(head2, size - size / 2);
    ListNode result = mergeTwoLists(head1, size / 2, head2, size - size / 2);
    return result;
  }

  private ListNode mergeTwoLists(ListNode first, int sizeFirst, ListNode second, int sizeSecond) {
    int i = sizeFirst;
    int j = sizeSecond;
    ListNode resultHead = null;
    ListNode xy = null;
    ListNode x = first;
    ListNode y = second;
    while (i > 0 && j > 0) {
      int a = x.val;
      int b = y.val;
      if (a < b) {
        i--;
        if (resultHead == null) {
          resultHead = x;
          xy = x;
        } else {
          xy.next = x;
          xy = xy.next;
        }
        x = x.next;
      } else {
        j--;
        if (resultHead == null) {
          resultHead = y;
          xy = y;
        } else {
          xy.next = y;
          xy = xy.next;
        }
        y = y.next;
      }
    }
    while (j > 0) {
      xy.next = y;
      xy = xy.next;
      y = y.next;
      j--;
    }
    while (i > 0) {
      xy.next = x;
      xy = xy.next;
      x = x.next;
      i--;
    }
    xy.next = null;
    return resultHead;
  }
}
