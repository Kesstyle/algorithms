package pl.kes.algorithms;

public class TreeStepByStepDirections {

  public static void main(String...args) {
    TreeStepByStepDirections step = new TreeStepByStepDirections();
    TreeNode root = new TreeNode(5);
    root.left = new TreeNode(1);
    root.right = new TreeNode(2);
    root.left.left = new TreeNode(3);
    root.right.left = new TreeNode(6);
    root.right.right = new TreeNode(4);

    int start = 3;
    int dest = 6;
    System.out.println(step.getDirections(root, start, dest));
  }

  public String getDirections(TreeNode root, int startValue, int destValue) {
    String start = find(root, startValue);
    String end = find(root, destValue);
    return createResult(start, end);
  }

  private String find(TreeNode node, int value) {
    if (node == null) {
      return null;
    }
    if (node.val == value) {
      return "";
    }
    String left = find(node.left, value);
    if (left != null) {
      return 'L' + left;
    }
    String right = find(node.right, value);
    if (right != null) {
      return 'R' + right;
    }
    return null;
  }

  private String createResult(String start, String end) {
    int ss = start.length();
    int es = end.length();
    int i = 0, j = 0;
    while (i < ss && j < es && start.charAt(i) == end.charAt(j)) {
      i ++;
      j ++;
    }
    String res = new String();
    for (int k = 0; k < ss - i; k++) {
      res = res + "U";
    }
    return res + end.substring(j, es);
  }

  static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }
}
