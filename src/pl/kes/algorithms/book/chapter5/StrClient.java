package pl.kes.algorithms.book.chapter5;

import java.util.Random;
import edu.princeton.cs.algs4.Alphabet;

public class StrClient {

  public static void main(String... args) {
    // Distributive
    System.out.println("############################### Distributive ########################");
    DistributiveCountSort distributiveCountSort = new DistributiveCountSort();
    int n = 10;
    Entry<Integer, String>[] a = new Entry[n];
    for (int i = 0; i < n; i++) {
      a[i] = new Entry<>(new Random().nextInt(n / 3), randomStr(7));
    }
    distributiveCountSort.sort(a);
    for (int i = 0; i < n; i++) {
      System.out.println(a[i]);
    }
    System.out.println("############################### LSD ########################");

    // LSD
    LSDSorting lsdSorting = new LSDSorting();
    n = 50;
    int w = 10;
    String[] strs = new String[n];
    for (int i = 0; i < n; i++) {
      strs[i] = randomStr(w);
    }
    lsdSorting.sort(strs, w);
    for (int i = 0; i < n; i++) {
      System.out.println(strs[i]);
    }

    System.out.println("################################# MSD #######################");
    // MSD
    MSDSorting msdSorting = new MSDSorting();
    n = 100;
    w = 5;
    strs = new String[n];
    for (int i = 0; i < n; i++) {
      strs[i] = randomStr(w);
    }

    msdSorting.sort(strs);
    for (int i = 0; i < n; i++) {
      System.out.println(strs[i]);
    }

    System.out.println("################################# Three-part QSort #######################");
    // QSort
    ThreePartSorting threePartSorting = new ThreePartSorting();
    n = 100;
    w = 5;
    strs = new String[n];
    for (int i = 0; i < n; i++) {
      strs[i] = randomStr(w);
    }

    threePartSorting.sort(strs);
    for (int i = 0; i < n; i++) {
      System.out.println(strs[i]);
    }

    System.out.println("################################# LSD variable #######################");
    // QSort
    LsdVariableSorting lsdVariableSorting = new LsdVariableSorting();
    n = 100;
    w = 3;
    strs = new String[n];
    for (int i = 0; i < n; i++) {
      strs[i] = randomStr(w, 2*w);
    }

    lsdVariableSorting.sort(strs, w * 2);
    for (int i = 0; i < n; i++) {
      System.out.println(strs[i]);
    }

    System.out.println("################################# MSD queue-based #######################");
    // QSort
    MSDQueueBasedSorting msdQueueBasedSorting = new MSDQueueBasedSorting();
    n = 100;
    w = 3;
    strs = new String[n];
    for (int i = 0; i < n; i++) {
      strs[i] = randomStr(w, 2*w);
    }

    msdQueueBasedSorting.sort(strs);
    for (int i = 0; i < n; i++) {
      System.out.println(strs[i]);
    }

    System.out.println("################################# Three-part for arrays #######################");
    // QSort
    ThreePartNumbersArraySorting threePartNumbersArraySorting = new ThreePartNumbersArraySorting();
    n = 100;
    w = 3;
    int maxNumber = 1000;
    int minNumber = -1000;
    int[][] numberArrays = new int[n][];
    for (int i = 0; i < n; i++) {
      numberArrays[i] = randomArr(w, 2*w, minNumber, maxNumber);
    }

    threePartNumbersArraySorting.sort(numberArrays);
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < numberArrays[i].length; j++) {
        System.out.print(numberArrays[i][j] + " ");
      }
      System.out.println();
    }

    System.out.println("################################# LSD bits #######################");
    // QSort
    LsdSortingElderBits lsdSortingElderBits = new LsdSortingElderBits();
    w = 100;
    minNumber = (int) Math.pow(2, 16) + 3;
    maxNumber = (int) Math.pow(2, 31);
    int[] arr = randomArr(w, 2*w, minNumber, maxNumber);

    lsdSortingElderBits.sort(arr, 16);
    int prev = 0;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] >= prev) {
        System.out.println(arr[i] + " >= " + prev);
        prev = arr[i];
      } else {
        System.out.println("Oops! " + arr[i] + " < " + prev);
      }
      System.out.println(arr[i] + " ");
    }
    System.out.println();
  }

  private static int[] randomArr(int min, int max, int minNumber, int maxNumber) {
    int[] arr = new int[min + new Random().nextInt(max - min)];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = minNumber + new Random().nextInt(maxNumber - minNumber);
    }
    return arr;
  }

  private static String randomStr(int length) {
    char[] arr = new char[length];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (char) ('a' + new Random().nextInt('z' - 'a' + 1));
    }
    return new String(arr);
  }

  private static String randomStr(int minLength, int maxLength) {
    char[] arr = new char[minLength + new Random().nextInt(maxLength - minLength)];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (char) ('a' + new Random().nextInt('z' - 'a' + 1));
    }
    String s = new String(arr);
//    System.out.println(s);
    return s;
  }
}
