package pl.kes.algorithms.book.chapter3;

public class TreeNode<Key extends Comparable<Key>, Value> {

  Key key;
  Value value;
  TreeNode left;
  TreeNode right;
  Color color;
  int N;

  public TreeNode(Key key, Value value, Color color, int n) {
    this.key = key;
    this.value = value;
    this.color = color;
    N = n;
  }

  public TreeNode(Key key, Value value, TreeNode left, TreeNode right, Color color, int n) {
    this.key = key;
    this.value = value;
    this.left = left;
    this.right = right;
    this.color = color;
    N = n;
  }
}