package pl.kes.algorithms;

public class SecondMinimumElement {

  public static void main(String...args) {
    SecondMinimumElement test = new SecondMinimumElement();
    SecondMinimumElement.TreeNode root = new SecondMinimumElement.TreeNode(2);
    root.left = new TreeNode(2);
    root.right = new TreeNode(5);
    root.right.left = new TreeNode(5);
    root.right.right = new TreeNode(7);
    System.out.println(test.findSecondMinimumValue(root));
  }

  public int findSecondMinimumValue(TreeNode root) {
    if (root.left == null) {
      return -1;
    }

    int result = findMin(root, root.val);
    if (result == root.val) {
      return -1;
    }
    return result;
  }

  private int findMin(TreeNode root, int banned) {
    if (root.left == null) {
      return root.val;
    }
    int leftMin = findMin(root.left, banned);
    int rightMin = findMin(root.right, banned);
    if (leftMin == banned) {
      return rightMin;
    } else if (rightMin == banned) {
      return leftMin;
    }
    return Math.min(leftMin, rightMin);
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
