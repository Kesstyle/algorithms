package pl.kes.algorithms;

public class WiggleSort {

  public static void main(String...args) {
    WiggleSort wiggleSort = new WiggleSort();
    int[] nums = new int[] {1,3,2,2,3,1};
    wiggleSort.wiggleSort(nums);
    for (int i = 0; i < nums.length; i++) {
      System.out.print(nums[i] + " ");
    }
  }

  public void wiggleSort(int[] nums) {
    int n = nums.length;
    PyramideSort pyramideSort = new PyramideSort(n);
    for (int i = 0; i < n; i++) {
      pyramideSort.add(nums[i]);
    }
    for (int i = 1; i < n; i = i + 2) {
      nums[i] = pyramideSort.getMax();
    }
    for (int i = 0; i < n; i = i + 2) {
      nums[i] = pyramideSort.getMax();
    }
  }

  class PyramideSort {

    int[] array;
    int n = 0;

    public PyramideSort(int size) {
      array = new int[size+1];
    }

    public void add(int val) {
      array[++n] = val;
      swim(n);
    }

    public int getMax() {
      int max = array[1];
      swap(1, n--);
      sink(1);
      return max;
    }

    private void swim(int i) {
      int k = i / 2;
      int j = i;
      while (k > 0) {
        if (!less(j, k)) {
          swap(j, k);
          j = k;
          k = j / 2;
        } else {
          break;
        }
      }
    }

    private void sink(int i) {
      int k = 2*i;
      int j = i;
      while (k <= n) {
        if (k + 1 <= n && less(k, k+1)) {
          k++;
        }
        if (!less(j, k)) {
          break;
        }
        swap(j, k);
        j = k;
        k = 2 * j;
      }
    }

    private boolean less(int i, int j) {
      return array[i] < array[j];
    }

    private void swap(int i, int j) {
      int tmp = array[i];
      array[i] = array[j];
      array[j] = tmp;
    }
  }
}
