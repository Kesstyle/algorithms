package pl.kes.algorithms;

import java.util.Stack;

public class KthSmallesInBinarySearchTree {

  public static void main(String...args) {
    KthSmallesInBinarySearchTree test = new KthSmallesInBinarySearchTree();
    TreeNode root = new TreeNode(5);
    root.left = new TreeNode(3);
    root.left.right = new TreeNode(4);
    root.right = new TreeNode(6);
    root.left.left = new TreeNode(2);
    root.left.left.left = new TreeNode(1);
    int k = 3;
    System.out.println(test.kthSmallest(root, k));
  }

  public int kthSmallest(TreeNode root, int k) {
    Stack<TreeNode> nodesStack = new Stack<>();
    nodesStack.add(root);
    TreeNode node = root;
    while (node.left != null) {
      node = node.left;
      nodesStack.add(node);
    }
    node = nodesStack.pop();
    int treeSize = count(node, 0);
    int prevTreeSize = 0;
    while (k > treeSize) {
      node = nodesStack.pop();
      prevTreeSize = treeSize;
      treeSize = count(node, treeSize);
    }
    // We will look only to right from root
    k -= prevTreeSize;
    if (k == 1) {
      return node.val;
    }
    return kthSmallest(node.right, k - 1);
  }

  private int count(TreeNode node) {
    if (node == null) {
      return 0;
    }
    return count(node.left) + count(node.right) + 1;
  }

  private int count(TreeNode node, int numberLeft) {
    return numberLeft + count(node.right) + 1;
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
