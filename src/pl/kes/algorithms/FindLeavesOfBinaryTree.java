package pl.kes.algorithms;

import java.util.ArrayList;
import java.util.List;

public class FindLeavesOfBinaryTree {

  public static void main(String...args) {
    FindLeavesOfBinaryTree findLeavesOfBinaryTree = new FindLeavesOfBinaryTree();
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(5);
    root.right = new TreeNode(3);
    List<List<Integer>> res = findLeavesOfBinaryTree.findLeaves(root);
    for (int i = 0 ; i < res.size(); i++) {
      System.out.println();
      for (int j = 0; j < res.get(i).size(); j++) {
        System.out.print(res.get(i).get(j) + " ");
      }
    }
  }

  public List<List<Integer>> findLeaves(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    process(res, root);
    return res;
  }

  private int process(List<List<Integer>> res, TreeNode node) {
    if (node == null) {
      return 0;
    }
    int depth = 0;
    if (node.left != null || node.right != null) {
      depth = 1 + Math.max(process(res, node.left), process(res, node.right));
    }
    if (res.size() <= depth) {
      for (int i = res.size(); i <= depth; i++) {
        res.add(new ArrayList<>());
      }
    }
    res.get(depth).add(node.val);
    return depth;
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
