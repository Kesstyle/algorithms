package pl.kes.algorithms;

class MinStack {

  private Node top = null;
  private Node mins = null;

  public static void main(String...args) {
    MinStack minStack = new MinStack();
    minStack.push(0);
    minStack.push(1);
    minStack.push(0);
    System.out.println(minStack.getMin());
    minStack.pop();
    System.out.println(minStack.getMin());
  }

  public MinStack() {

  }

  public void push(int val) {
    if (top == null) {
      top = new Node(val);
      mins = new Node(val);
    } else {
      top.next = new Node(val, top);
      top = top.next;
      if (val <= mins.value) {
        mins.next = new Node(val, mins);
        mins = mins.next;
      }
    }
  }

  public void pop() {
    if (top.value == mins.value) {
      mins = mins.previous;
    }
    top = top.previous;
  }

  public int top() {
    return top.value;
  }

  public int getMin() {
    return mins.value;
  }

  class Node {
    Node next;
    Node previous;
    int value;

    Node(int value) {
      this.value = value;
    }

    Node(int value, Node previous) {
      this.value = value;
      this.previous = previous;
    }
  }
}