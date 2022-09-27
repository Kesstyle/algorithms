package pl.kes.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CopyRandomPointerList {

  public static void main(String...args) {
    CopyRandomPointerList copyRandomPointerList = new CopyRandomPointerList();
    Node root = new Node(7);
    root.next = new Node(13);
    root.next.next = new Node(11);
    root.next.next.next = new Node(10);
    root.next.next.next.next = new Node(1);
    root.next.random = root;
    root.next.next.random = root.next.next.next.next;
    root.next.next.next.random = root.next.next;
    root.next.next.next.next.random = root;
    Node newHead = copyRandomPointerList.copyRandomList(root);
  }

  Map<Node, Pair> nodesToInt = new HashMap<>();
  List<Node> newNodes = new ArrayList<>();

  public Node copyRandomList(Node head) {
    if (head == null) {
      return null;
    }
    int index = 0;

    Node curr = head;
    do {
      nodesToInt.put(curr, new Pair(index++));
      curr = curr.next;
    } while (curr != null);

    curr = head;
    Node newCurr = null;
    Node newNode;
    do {
      Pair pair = nodesToInt.get(curr);
      pair.b = curr.random == null ? null : nodesToInt.get(curr.random).a;
      newNode = new Node(curr.val);
      if (newCurr != null) {
        newCurr.next = newNode;
      }
      newCurr = newNode;
      newNodes.add(newCurr);
      curr = curr.next;
    } while (curr != null);

    curr = head;
    newCurr = newNodes.get(0);
    do {
      Integer b = nodesToInt.get(curr).b;
      newCurr.random = b == null? null : newNodes.get(b);
      curr = curr.next;
      newCurr = newCurr.next;
    } while (curr != null);
    return newNodes.get(0);
  }

  class Pair {

    Integer a;
    Integer b;

    public Pair(Integer a) {
      this.a = a;
    }

    public Pair(Integer a, Integer b) {
      this.a = a;
      this.b = b;
    }
  }

  static class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
      this.val = val;
      this.next = null;
      this.random = null;
    }
  }
}
