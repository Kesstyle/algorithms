package pl.kes.algorithms;

class Solution {

  private final int mod = 1000000007;

  public static void main(String... args) {
    Solution solution = new Solution();

  }

  public int maximumSumSubsequence(int[] nums, int[][] queries) {
    int n = nums.length;
    int adjSize = 1;
    while (adjSize < n) {
      adjSize *= 2;
    }
    int[] adj = new int[2 * adjSize];
    for (int i = 0; i < n; i++) {
      adj[i] = nums[i];
    }
    SegmentTree segmentTree = new SegmentTree(adj);
    long sum = 0L;
    for (int[] query : queries) {
      segmentTree.update(query[0], query[1]);
      sum += segmentTree.query();
      sum %= mod;
    }
    return (int) sum;
  }

  // 0 - l
  // 1 - r
  // 2 - lr
  // 3 - none
  class SegmentTree {
    int[][] tree;
    int n;

    public SegmentTree(int[] nums) {
      n = nums.length;
      tree = new int[2 * n][4];
      buildTree(nums);
    }

    private void buildTree(int[] nums) {
      for (int i = 0; i < n; i++) {
        tree[n + i][0] = Integer.MIN_VALUE;
        tree[n + i][1] = Integer.MIN_VALUE;
        tree[n + i][2] = nums[i];
        tree[n + i][3] = 0;
      }
      for (int i = n - 1; i > 0; i--) {
        int[] i2 = tree[2 * i];
        int[] i2p1 = tree[2 * i + 1];
        setUp(tree[i], i2, i2p1);
      }
    }

    private void setUp(int[] target, int[] i2, int[] i2p1) {
      int left = Integer.MIN_VALUE;
      if (i2[0] != Integer.MIN_VALUE) {
        if (i2p1[0] != Integer.MIN_VALUE) {
          left = Math.max(left, i2[0] + i2p1[0]);
        }
        if (i2p1[3] != Integer.MIN_VALUE) {
          left = Math.max(left, i2[0] + i2p1[3]);
        }
      }
      if (i2[2] != Integer.MIN_VALUE) {
        if (i2p1[3] != Integer.MIN_VALUE) {
          left = Math.max(left, i2[2] + i2p1[3]);
        }
      }

      target[0] = left;

      int right = Integer.MIN_VALUE;
      if (i2p1[1] != Integer.MIN_VALUE) {
        if (i2[1] != Integer.MIN_VALUE) {
          right = Math.max(right, i2p1[1] + i2[1]);
        }
        if (i2[3] != Integer.MIN_VALUE) {
          right = Math.max(right, i2p1[1] + i2[3]);
        }
      }
      if (i2p1[2] != Integer.MIN_VALUE) {
        if (i2[3] != Integer.MIN_VALUE) {
          right = Math.max(right, i2p1[2] + i2[3]);
        }
      }
      target[1] = right;

      int lr = Integer.MIN_VALUE;
      if (i2[0] != Integer.MIN_VALUE) {
        if (i2p1[1] != Integer.MIN_VALUE) {
          lr = Math.max(lr, i2[0] + i2p1[1]);
        }
        if (i2p1[2] != Integer.MIN_VALUE) {
          lr = Math.max(lr, i2[0] + i2p1[2]);
        }
      }
      if (i2[2] != Integer.MIN_VALUE) {
        if (i2p1[1] != Integer.MIN_VALUE) {
          lr = Math.max(lr, i2[2] + i2p1[1]);
        }
      }

      target[2] = lr;

      int none = Integer.MIN_VALUE;
      if (i2[3] != Integer.MIN_VALUE) {
        if (i2p1[0] != Integer.MIN_VALUE) {
          none = Math.max(none, i2[3] + i2p1[0]);
        }
        if (i2p1[3] != Integer.MIN_VALUE) {
          none = Math.max(none, i2[3] + i2p1[3]);
        }
      }
      if (i2[1] != Integer.MIN_VALUE) {
        if (i2p1[3] != Integer.MIN_VALUE) {
          none = Math.max(none, i2[1] + i2p1[3]);
        }
      }
      target[3] = none;
    }

    public void update(int index, int val) {
      index += n;
      tree[index][2] = val;
      while (index > 0) {
        int left = index;
        int right = index;
        if (index % 2 == 0) {
          right = index + 1;
        } else {
          left = index - 1;
        }
        setUp(tree[index / 2], tree[left], tree[right]);
        index /= 2;
      }
    }

    public int query() {
      int res = tree[1][0];
      res = Math.max(res, tree[1][1]);
      res = Math.max(res, tree[1][2]);
      res = Math.max(res, tree[1][3]);
      return res;
    }
  }
}