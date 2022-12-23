package pl.kes.algorithms;

public class MinimumCharsToRemove {

  public static void main(String...args) {
    MinimumCharsToRemove minimumCharsToRemove = new MinimumCharsToRemove();
    String test = "bbcebab";
    System.out.println(minimumCharsToRemove.minDeletions(test));
  }

  public int minDeletions(String s) {
    int[] chars = new int['z' - 'a' + 1];
    for (int i = 0; i < s.length(); i++) {
      chars[s.charAt(i) - 'a']++;
    }
    sort(chars);
    int sum = 0;
    int maxAllowed = s.length();
    for (int i = 'z' - 'a'; i >= 0 && chars[i] != 0; i--) {
      if (chars[i] > maxAllowed) {
        sum += chars[i] - maxAllowed;
        chars[i] = maxAllowed;
      }
      maxAllowed = Math.max(0, chars[i] - 1);
    }
    return sum;
  }

  private void sort(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
      for (int j = i; j > 0 && arr[j] < arr[j-1]; j--) {
        swap(arr, j - 1, j);
      }
    }
  }

  private void swap(int[] arr, int i, int j) {
    int tmp = arr[j];
    arr[j] = arr[i];
    arr[i] = tmp;
  }
}
