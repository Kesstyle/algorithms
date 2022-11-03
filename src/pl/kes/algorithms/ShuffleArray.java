package pl.kes.algorithms;

public class ShuffleArray {

  int[] original;
  int[] shuffled;

  public ShuffleArray(int[] nums) {
    this.original = nums;
    shuffled = new int[nums.length];
    reset();
  }

  public int[] reset() {
    for (int i = 0; i < original.length; i++) {
      shuffled[i] = original[i];
    }
    return original;
  }

  public int[] shuffle() {
    int n = shuffled.length;
    for (int i = 0; i < n; i++) {
      int j = (int) (Math.random() * (i + 1));
      swap(i, j);
    }
    return shuffled;
  }

  private void swap(int i, int j) {
    int tmp = shuffled[i];
    shuffled[i] = shuffled[j];
    shuffled[j] = tmp;
  }
}
