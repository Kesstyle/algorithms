package pl.kes.algorithms;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class PreorderInorderBinaryTree {

  public static void main(String... args) {
    PreorderInorderBinaryTree preorderInorderBinaryTree = new PreorderInorderBinaryTree();
    int[] preorder = new int[] {3, 9, 40, 6, 20, 15, 7};
    int[] inorder = new int[] {40, 9, 6, 3, 15, 20, 7};
    TreeNode res = preorderInorderBinaryTree.buildTree(preorder, inorder);

    PriorityQueue<TreeNode> queue = new PriorityQueue<>();
    queue.add(res);
    while (!queue.isEmpty()) {
      TreeNode next = queue.poll();
      if (next == null) {
        System.out.print("null, ");
        continue;
      }
      System.out.print(next.val + ", ");
      if (next.left != null) {
        queue.add(next.left);
      }
      if (next.right != null) {
        queue.add(next.right);
      }
    }
  }

  Map<Integer, Integer> inMap = new HashMap<>();

  public TreeNode buildTree(int[] preorder, int[] inorder) {
    for (int i = 0; i < inorder.length; i++) {
      inMap.put(inorder[i], i);
    }
    return build(preorder, 0, preorder.length, inorder, 0, inorder.length);
  }

  private TreeNode build(int[] pre, int sp, int ep, int[] in, int si, int ei) {
    if (ep <= sp) {
      return null;
    }
    TreeNode root = new TreeNode(pre[sp++]);
    if (ep == sp) {
      return root;
    }
    int inRootIdx = inMap.get(root.val);
    root.left = build(pre, sp, inRootIdx - si + sp, in, si, inRootIdx);
    root.right = build(pre, inRootIdx - si + sp, ep, in, inRootIdx + 1, ei);
    return root;
  }


  static class TreeNode implements Comparable<TreeNode> {
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

    @Override
    public int compareTo(TreeNode o) {
      return 0;
    }
  }
}
