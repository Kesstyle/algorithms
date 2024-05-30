package pl.kes.algorithms;

class SegmentTree {
    int[] treeArray;
    int size;

    public SegmentTree(int[] nums) {
        size = nums.length;
        treeArray = new int[2 * size];
        build(nums);
    }

    private void build(int[] nums) {
        for (int i = 0; i < size; i++) {
            treeArray[size + i] = nums[i];
        }
        for (int i = size - 1; i > 0; i--) {
            treeArray[i] = treeArray[2 * i] + treeArray[2 * i + 1];
        }
    }

    public void update(int index, int val) {
        index += size;
        treeArray[index] = val;
        while (index > 0) {
            int left = index;
            int right = index;
            if (index % 2 == 0) {
                right = index + 1;
            } else {
                left = index - 1;
            }
            treeArray[index / 2] = treeArray[left] + treeArray[right];
            index /= 2;
        }
    }

    public int query(int l, int r) {
        l += size;
        r += size;
        int sum = 0;
        while (l <= r) {
            if ((l % 2) == 1) {
                sum += treeArray[l];
                l++;
            }
            if ((r % 2) == 0) {
                sum += treeArray[r];
                r--;
            }
            l /= 2;
            r /= 2;
        }
        return sum;
    }
}
