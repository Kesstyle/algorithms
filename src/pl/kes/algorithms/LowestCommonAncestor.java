package pl.kes.algorithms;

import java.util.Stack;

public class LowestCommonAncestor {

  public static void main(String... args) {
    LowestCommonAncestor lowestCommonAncestor = new LowestCommonAncestor();
    TreeNode root = new TreeNode(3);
    root.left = new TreeNode(5);
    root.left.left = new TreeNode(6);
    root.left.right = new TreeNode(2);
    root.left.right.left = new TreeNode(7);
    root.left.right.right = new TreeNode(4);
    root.right = new TreeNode(1);
    root.right.left = new TreeNode(0);
    root.right.right = new TreeNode(8);
    TreeNode result = lowestCommonAncestor.lowestCommonAncestor(root, root.left, root.right);
    System.out.println(result.val);
  }

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    return recursive(root, p.val, q.val);
  }

  private TreeNode recursive(TreeNode root, int a, int b) {
    if (root == null) {
      return null;
    }
    TreeNode left = recursive(root.left, a, b);
    TreeNode right = recursive(root.right, a, b);
    if (left != null && right != null) {
      return root;
    }
    if (root.val == a || root.val == b) {
      return root;
    }
    if (left != null) {
      return left;
    }
    return right;
  }

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }
}
