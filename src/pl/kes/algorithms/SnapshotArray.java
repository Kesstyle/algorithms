package pl.kes.algorithms;

import java.util.HashMap;
import java.util.Map;

class SnapshotArray {

  public static void main(String... args) {
    SnapshotArray snapshotArray = new SnapshotArray(1);
    snapshotArray.set(0, 15);
    snapshotArray.snap();
    snapshotArray.snap();
    snapshotArray.snap();
    snapshotArray.set(0, 6);
    System.out.println(snapshotArray.get(0, 0));
  }

  Map<Integer, Integer>[] array;
  int snap;

  public SnapshotArray(int length) {
    array = new Map[length];
    for (int i = 0; i < length; i++) {
      array[i] = new HashMap<>();
    }
  }

  public void set(int index, int val) {
    array[index].put(snap, val);
  }

  public int snap() {
    return snap++;
  }

  public int get(int index, int snap_id) {
    for (int i = snap_id; i >= 0; i --) {
      if (array[index].get(i) != null) {
        return array[index].get(i);
      }
    }
    return 0;
  }

  class Tree {
    TreeNode root;

    public void add(int index, int val) {
      root = add(root, index, val);
    }

    public int get(int index) {
      TreeNode res = get(root, index);
      if (res != null) {
        return res.val;
      }
      return 0;
    }

    private TreeNode get(TreeNode s, int index) {
      if (s == null) {
        return null;
      }
      if (index < s.index) {
        return get(s.left, index);
      } else if (index > s.index) {
        TreeNode res = get(s.right, index);
        if (res == null) {
          return s;
        } else {
          return res;
        }
      } else {
        return s;
      }
    }

    private TreeNode add(TreeNode s, int index, int val) {
      if (s == null) {
        return new TreeNode(index, val);
      }
      if (index < s.index) {
        s.left = add(s.left, index, val);
      } else if (index > s.index) {
        s.right = add(s.right, index, val);
      } else {
        s.val = val;
        return s;
      }

      if (isRed(s.right) && !isRed(s.left)) {
        s = rotateToLeft(s);
      }
      if (isRed(s.left) && isRed(s.left.left)) {
        s = rotateToRight(s);
      }
      if (isRed(s.left) && isRed(s.right)) {
        flipColors(s);
      }
      return s;
    }

    private TreeNode rotateToLeft(TreeNode s) {
      TreeNode h = s.right;
      s.right = h.left;
      h.left = s;
      h.isRed = s.isRed;
      s.isRed = true;
      return h;
    }

    private TreeNode rotateToRight(TreeNode s) {
      TreeNode h = s.left;
      s.left = h.right;
      h.right = s;
      h.isRed = s.isRed;
      s.isRed = true;
      return h;
    }

    private void flipColors(TreeNode node) {
      node.isRed = true;
      if (node.left != null) {
        node.left.isRed = false;
      }
      if (node.right != null) {
        node.right.isRed = false;
      }
    }

    private boolean isRed(TreeNode node) {
      if (node == null) {
        return false;
      }
      return node.isRed;
    }
  }

  class TreeNode {
    int index;
    int val;
    boolean isRed;
    TreeNode left;
    TreeNode right;

    public TreeNode(int index, int val) {
      this.index = index;
      this.val = val;
    }
  }
}