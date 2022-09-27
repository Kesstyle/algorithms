package pl.kes.algorithms;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeLevelOrderTraversal {

  public static void main(String...args) {
    BinaryTreeLevelOrderTraversal binaryTreeLevelOrderTraversal = new BinaryTreeLevelOrderTraversal();
    TreeNode root = new TreeNode(3);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.right.left = new TreeNode(15);
    root.right.right = new TreeNode(7);
    List<List<Integer>> result = binaryTreeLevelOrderTraversal.levelOrder(root);
    for (int i = 0; i < result.size(); i++) {
      for (int j = 0; j < result.get(i).size(); j++) {
        System.out.print(result.get(i).get(j) + " ");
      }
      System.out.println();
    }
  }

  public List<List<Integer>> levelOrder(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }
    List<List<Integer>> list = new ArrayList<>();
    list.add(new ArrayList<>(List.of(root.val)));
    traverse(root, list, 1);
    return list;
  }

  private void traverse(TreeNode node, List<List<Integer>> list, int index) {
    if (node == null) {
      return;
    }
    if ((node.left != null || node.right != null) && list.size() <= index) {
      list.add(new ArrayList<>());
    }
    if (node.left != null) {
      list.get(index).add(node.left.val);
      traverse(node.left, list, index + 1);
    }
    if (node.right != null) {
      list.get(index).add(node.right.val);
      traverse(node.right, list, index + 1);
    }
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
