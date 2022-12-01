package pl.kes.algorithms;

import java.util.List;

public class CounterOfSmallerNumbers {

  public static void main(String...args) {
    CounterOfSmallerNumbers counterOfSmallerNumbers = new CounterOfSmallerNumbers();
    int[] nums = new int[] {5,2,6,1};
    List<Integer> res = counterOfSmallerNumbers.countSmaller(nums);
    for (int i = 0; i < res.size(); i++) {
      System.out.print(res.get(i) + " ");
    }
  }

  Integer[] res;

  public List<Integer> countSmaller(int[] nums) {
    int n = nums.length;
    res = new Integer[n];
    for (int i = 0; i < n; i++) {
      res[i] = 0;
    }
    ReadBlackBST tree = new ReadBlackBST();
    // O(N log N)
    for (int i = n - 1; i >= 0; i--) {
       tree.put(nums[i], i);
    }
    return List.of(res);
  }

  enum Color {
    BLACK, RED;
  }

  class ReadBlackBST {
    TreeNode root;

    public void put(int val, int i) {
      root = put(root, val, i);
    }

    private TreeNode put(TreeNode node, int val, int i) {
      if (node == null) {
        return new TreeNode(val);
      }

      // O (log N)
      if (val > node.val) {
        res[i] += node.strength + node.countStr;
        node.right = put(node.right, val, i);
      } else if (val < node.val) {
        node.strength++;
        node.left = put(node.left, val, i);
      } else {
        node.countStr++;
        res[i] += node.strength;
      }

      // O(1)
      if (isRed(node.right) && !isRed(node.left)) {
        node = rotateLeft(node);
      }
      // O(1)
      if (isRed(node.left) && isRed(node.left.left)) {
        node = rotateRight(node);
      }
      // O(1)
      if (isRed(node.left) && isRed(node.right)) {
        flipColors(node);
      }
      return node;
    }

    private void flipColors(TreeNode node) {
      node.color = Color.RED;
      node.left.color = Color.BLACK;
      node.right.color = Color.BLACK;
    }

    private TreeNode rotateLeft(TreeNode node) {
      TreeNode x = node.right;
      node.right = x.left;
      x.left = node;
      x.color = node.color;
      node.color = Color.RED;
      return x;
    }

    private TreeNode rotateRight(TreeNode node) {
      TreeNode x = node.left;
      node.left = x.right;
      x.right = node;
      x.color = node.color;
      node.color = Color.RED;
      return x;
    }

    private boolean isRed(TreeNode node) {
      if (node == null) {
        return false;
      }
      return node.color == Color.RED;
    }

    private int strength(int val) {
      if (root == null) {
        return 0;
      }
      int str = 0;
      TreeNode iter = root;
      while(true) {
        if (val < iter.val) {
          iter.strength++;
          iter = iter.left;
        } else if (val > iter.val) {
          str += iter.strength + iter.countStr;
          iter = iter.right;
        } else {
          return str + iter.strength;
        }
      }
    }
  }

  class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    int strength;
    int countStr = 1;
    Color color;

    public TreeNode(int val) {
      this.val = val;
    }
  }
}
