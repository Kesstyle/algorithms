package pl.kes.algorithms;

class MedianFinder {

  public static void main(String... args) {
    MedianFinder medianFinder = new MedianFinder();
    medianFinder.addNum(1);
    medianFinder.addNum(2);
    System.out.println(medianFinder.findMedian());
  }

  MinPQ high;
  MaxPQ low;

  public MedianFinder() {
      high = new MinPQ(50000);
      low = new MaxPQ(50000);
  }

  public void addNum(int num) {
    high.addNum(num);
    low.addNum(high.getMin());
    if (low.size() > high.size()) {
        high.addNum(low.getMax());
    }
  }

  public double findMedian() {
    int size = high.size() + low.size();
    if (size % 2 == 0) {
        return ((double) high.peek() + (double) low.peek()) / 2;
    } else {
        return high.peek();
    }
  }

  class MinPQ {

    int[] nums;
    int n;

    public MinPQ(int size) {
      nums = new int[size + 1];
    }

    public void addNum(int num) {
      nums[++n] = num;
      swim(n);
    }

    public int getMin() {
        int res = nums[1];
        nums[1] = nums[n--];
        sink(1);
        return res;
    }

    public int peek() {
        return nums[1];
    }

    public int size() {
        return n;
    }

    private void swim(int i) {
      int k = i / 2;
      int j = i;
      while (k > 0) {
        if (!less(j, k)) {
          break;
        }
        swap(j, k);
        j = k;
        k = j / 2;
      }
    }

    private void sink(int i) {
        int k = 2*i;
        int j = i;
        while (k <= n) {
            if (less(k + 1, k)) {
                k++;
            }
            if (!less(k, j)) {
                break;
            }
            swap(k, j);
            j = k;
            k = j * 2;
        }
    }

    private boolean less(int i, int j) {
      return nums[i] < nums[j];
    }

    private void swap(int i, int j) {
      int tmp = nums[i];
      nums[i] = nums[j];
      nums[j] = tmp;
    }
  }

    class MaxPQ {

        int[] nums;
        int n;

        public MaxPQ(int size) {
            nums = new int[size + 1];
        }

        public void addNum(int num) {
            nums[++n] = num;
            swim(n);
        }

        public int peek() {
            return nums[1];
        }

        public int getMax() {
            int res = nums[1];
            nums[1] = nums[n--];
            sink(1);
            return res;
        }

        public int size() {
            return n;
        }

        private void swim(int i) {
            int k = i / 2;
            int j = i;
            while (k > 0) {
                if (!less(k, j)) {
                    break;
                }
                swap(j, k);
                j = k;
                k = j / 2;
            }
        }

        private void sink(int i) {
            int k = 2*i;
            int j = i;
            while (k <= n) {
                if (less(k, k + 1)) {
                    k++;
                }
                if (!less(j, k)) {
                    break;
                }
                swap(k, j);
                j = k;
                k = j * 2;
            }
        }

        private boolean less(int i, int j) {
            return nums[i] < nums[j];
        }

        private void swap(int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */