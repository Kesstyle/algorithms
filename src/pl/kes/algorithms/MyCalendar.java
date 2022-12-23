package pl.kes.algorithms;

public class MyCalendar {

  public static void main(String...args) {
    MyCalendar myCalendar = new MyCalendar();
    myCalendar.book(10, 20);
    myCalendar.book(15, 25);
    myCalendar.book(20, 30);
    System.out.println(myCalendar.tree.size);
  }

  Tree tree;

  public MyCalendar() {
    tree = new Tree();
  }

  public boolean book(int start, int end) {
    return tree.add(new TimeFrame(start, end));
  }

  class Tree {

    TreeNode root;
    int size = 0;

    public boolean add(TimeFrame elem) {
      int tmp = size;
      root = add(elem, root);
      return size - tmp > 0;
    }

    private TreeNode add(TimeFrame elem, TreeNode node) {
      if (node == null) {
        size++;
        return new TreeNode(elem);
      }
      if (node.val.intersects(elem)) {
        return node;
      }
      if (elem.compareTo(node.val) > 0) {
        node.right = add(elem, node.right);
      } else if (elem.compareTo(node.val) < 0) {
        node.left = add(elem, node.left);
      } else {
        return node;
      }

      if (isRed(node.right) && !isRed(node.left)) {
        node = rotateLeft(node);
      }
      if (isRed(node.left) && isRed(node.left.left)) {
        node = rotateRight(node);
      }

      if (isRed(node.left) && isRed(node.right)) {
        flipColors(node);
      }
      return node;
    }

    private TreeNode rotateLeft(TreeNode node) {
      TreeNode r = node.right;
      node.right = r.left;
      r.left = node;
      r.color = node.color;
      node.color = true;
      return r;
    }

    private TreeNode rotateRight(TreeNode node) {
      TreeNode r = node.left;
      node.left = r.right;
      r.right = node;
      r.color = node.color;
      node.color = true;
      return r;
    }

    private void flipColors(TreeNode node) {
      node.color = true;
      node.left.color = false;
      node.right.color = false;
    }

    private boolean isRed(TreeNode node) {
      if (node == null) {
        return false;
      }
      return node.color;
    }

    class TreeNode {
      TimeFrame val;
      TreeNode left;
      TreeNode right;
      boolean color;

      public TreeNode(TimeFrame elem) {
        val = elem;
        color = true;
      }
    }
  }

  class TimeFrame implements Comparable<TimeFrame> {
    int start;
    int end;

    public TimeFrame(final int start, final int end) {
      this.start = start;
      this.end = end;
    }

    public boolean intersects(TimeFrame t) {
      int s = t.start;
      int e = t.end;
      if (s < start && e <= start || s >= end && e > end) {
        return false;
      }
      return true;
    }

    @Override
    public int compareTo(final TimeFrame o) {
      return start - o.start;
    }
  }
}