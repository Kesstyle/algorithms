package pl.kes.algorithms;

import java.util.HashMap;
import java.util.Map;

public class KFrequentElements {

  public static void main(String...args) {
    KFrequentElements elements = new KFrequentElements();
    int[] nums = new int[] {1,1,1,2,2,3};
    int k = 2;
    int[] result = elements.topKFrequent(nums, k);
    for (int i = 0; i < result.length; i++) {
      System.out.println(result[i]);
    }
  }

  public int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Node> counts = new HashMap<>();
    for (int i = 0; i < nums.length; i ++) {
      if (counts.containsKey(nums[i])) {
        Node count = counts.get(nums[i]);
        count.count = count.count + 1;
      } else {
        Node count = new Node(nums[i], 1);
        counts.put(nums[i], count);
      }
    }
    PyramideSort pyramideSort = new PyramideSort(counts.size());
    for (Node node: counts.values()) {
      pyramideSort.insert(node);
    }
    int[] result = new int[k];
    for (int i = 0; i < k; i++) {
      result[i] = pyramideSort.removeMax().val;
    }
    return result;
  }

  class Node implements Comparable<Node> {
    int val;
    int count;

    public Node(int val, int count) {
      this.val = val;
      this.count = count;
    }


    @Override
    public int compareTo(Node o) {
      int count2 = o.count;
      if (count > count2) {
        return 1;
      } else if (count < count2) {
        return -1;
      }
      return 0;
    }
  }

  class PyramideSort {
    Node[] pyramide;
    int n;

    public PyramideSort(int n) {
      pyramide = new Node[n+1];
      this.n = 0;
    }

    public PyramideSort(Node[] source) {
      pyramide = new Node[source.length + 1];
      for (int i = 0; i < source.length; i++) {
        pyramide[i+1] = source[i];
      }
      this.n = source.length;
    }

    public Node removeMax() {
      Node max = pyramide[1];
      exch(1, n--);
      sink(1);
      return max;
    }

    public void insert(Node node) {
      pyramide[++n] = node;
      swim(n);
    }

    private void exch(int i, int j) {
      Node tmp = pyramide[i];
      pyramide[i] = pyramide[j];
      pyramide[j] = tmp;
    }

    private boolean less(int i, int j) {
      return pyramide[i].compareTo(pyramide[j]) < 0;
    }

    private void sink(int i) {
      int k = 2*i;
      while (k <= n) {
        if (less(k, k+1) && k + 1 <= n) {
          k++;
        }
        if (!less(i, k)) {
          break;
        }
        exch(i, k);
        i = k;
        k *= 2;
      }
    }

    private void swim(int i) {
      int k = i / 2;
      while (k >= 1) {
        if (!less(k, i)) {
          break;
        }
        exch(k, i);
        i = k;
        k = i / 2;
      }
    }
  }
}
