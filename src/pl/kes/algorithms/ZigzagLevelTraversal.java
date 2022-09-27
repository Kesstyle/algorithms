package pl.kes.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class ZigzagLevelTraversal {

  public static void main(String...args) {
    ZigzagLevelTraversal zigzagLevelTraversal = new ZigzagLevelTraversal();
    TreeNode root = new TreeNode(3, null, null);
    root.left = new TreeNode(9, null, null);
    root.right = new TreeNode(20, null, null);
    root.right.right = new TreeNode(7, null, null);
    root.right.left = new TreeNode(15, null, null);
    List<List<Integer>> result = zigzagLevelTraversal.zigzagLevelOrder(root);
    for (int i = 0; i < result.size(); i++) {
      for (int j = 0; j < result.get(i).size(); j++) {
        System.out.print(result.get(i).get(j) + ", ");
      }
      System.out.println();
    }
  }

  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }
    boolean left = false;
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);
    List<List<Integer>> result = new ArrayList<>();
    result.add(List.of(root.val));
    while (!stack.isEmpty()) {
      List<TreeNode> next = new ArrayList<>();
      while (!stack.isEmpty()) {
        next.addAll(traverse(stack.pop(), left));
      }
      for (int i = 0; i < next.size(); i++) {
        stack.push(next.get(i));
      }
      left = !left;
      if (!next.isEmpty()) {
        result.add(new ArrayList<>());
        int n = result.size() - 1;
        for (int i = 0; i < next.size(); i++) {
          result.get(n).add(next.get(i).val);
        }
      }
    }
    return result;
  }

  private List<TreeNode> traverse(TreeNode node, boolean left) {
    List<TreeNode> list = new ArrayList<>();
    if (node.left == null && node.right == null) {
      return list;
    }
    if (left) {
      if (node.left != null) {
        list.add(node.left);
      }
      if (node.right != null) {
        list.add(node.right);
      }
    } else {
      if (node.right != null) {
        list.add(node.right);
      }
      if (node.left != null) {
        list.add(node.left);
      }
    }
    return list;
  }

    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
}
