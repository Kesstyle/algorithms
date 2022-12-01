package pl.kes.algorithms;

public class DeleteNode {


  public void deleteNode(ListNode node) {
    if (node.next.next == null) {
      node.val = node.next.val;
      node.next = null;
    } else {
      node.val = node.next.val;
      deleteNode(node.next);
    }
  }
}
