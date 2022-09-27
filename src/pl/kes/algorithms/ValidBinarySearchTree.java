package pl.kes.algorithms;

public class ValidBinarySearchTree {

  public static void main(String...args) {
    ValidBinarySearchTree validBinarySearchTree = new ValidBinarySearchTree();
    TreeNode root = new TreeNode(5);
    root.left = new TreeNode(4);
    root.right = new TreeNode(6);
    root.right.right = new TreeNode(7);
    root.right.left = new TreeNode(3);
    System.out.println(validBinarySearchTree.isValidBST(root));
  }

  public boolean isValidBST(TreeNode root) {
    if (root == null) {
      return true;
    }
    if (root.left != null && root.left.val >= root.val) {
      return false;
    }
    if (root.right != null && root.right.val <= root.val) {
      return false;
    }
    return isValidBST(root.left, null, root.val) && isValidBST(root.right, root.val, null);
  }

  private boolean isValidBST(TreeNode node, Integer min, Integer max) {
    if (node == null) {
      return true;
    }
    int val = node.val;
    if ((min != null && val <= min) || (max != null && val >= max)) {
      return false;
    }
    return isValidBST(node.left, min, max == null? val : Math.min(max, val))
        && isValidBST(node.right, min == null? val : Math.max(min, val), max);
  }

  public static class TreeNode {
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
