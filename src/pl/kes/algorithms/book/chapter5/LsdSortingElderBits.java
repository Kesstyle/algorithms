package pl.kes.algorithms.book.chapter5;

public class LsdSortingElderBits {

  public static void main(String...args) {
    StringBuilder builder = new StringBuilder();
    for (int j = 15; j >= 0; j--) {
      System.out.println((((int)Math.pow(2, 18) - 11) >> (16 + j)) & 1);
      builder.append(((2^19 - 50) >> (16 + j)) & 1);
    }
    System.out.println(builder);
  }

  public void sort(int[] numbers, int wElderBits) {
    sortBits(numbers, wElderBits);
    InsertionNumberSort insertionNumberSort = new InsertionNumberSort();
    insertionNumberSort.sort(numbers);
  }

  public void sortBits(int[] numbers, int wElderBits) {
    int n = numbers.length;
    String[] strs = new String[n];
    for (int i = 0; i < n; i++) {
      StringBuilder builder = new StringBuilder();
      for (int j = 15; j >= 0; j--) {
        builder.append((numbers[i] >> (wElderBits + j)) & 1);
      }
      strs[i] = builder.toString();
    }
    sort(strs, numbers, wElderBits - 1);
    System.out.println("///////////////////  After LSD bits sort  /////////////////");
    for (int i = 0; i < numbers.length; i++) {
      System.out.println(numbers[i] + " - or str as " + strs[i]);
    }
  }

  private void sort(String[] strs, int[] numbers, int d) {
    for (int k = d; k >= 0; k--) {
      int[] counts = new int[3];
      for (int i = 0; i < strs.length; i++) {
        counts[key(strs[i], k) + 1]++;
      }
      for (int i = 0; i < 2; i++) {
        counts[i + 1] += counts[i];
      }
      String[] auxStr = new String[strs.length];
      int[] aux = new int[numbers.length];
      for (int i = 0; i < numbers.length; i++) {
        int index = counts[key(strs[i], k)];
        auxStr[index] = strs[i];
        aux[index] = numbers[i];
        counts[key(strs[i], k)]++;
      }
      for (int i = 0; i < numbers.length; i++) {
        strs[i] = auxStr[i];
        numbers[i] = aux[i];
      }
    }
  }

  private int key(String s, int d) {
    return s.charAt(d) - '0';
  }
}
