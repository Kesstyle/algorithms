package pl.kes.algorithms;

import java.util.Random;

public class InsertDeleteGetRandom {

  public static void main(String...args) {
    RandomizedSet randomizedSet = new RandomizedSet();
//    System.out.println(randomizedSet.insert(2)); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
//    System.out.println(randomizedSet.insert(0)); // Returns false as 2 does not exist in the set.
//    System.out.println(randomizedSet.getRandom()); // Inserts 2 to the set, returns true. Set now contains [1,2].
//    System.out.println(randomizedSet.insert(-2)); // getRandom() should return either 1 or 2 randomly.
//    System.out.println(randomizedSet.insert(-2)); // getRandom() should return either 1 or 2 randomly.
//    System.out.println(randomizedSet.remove(3)); // Removes 1 from the set, returns true. Set now contains [2].
//    System.out.println(randomizedSet.remove(3)); // Removes 1 from the set, returns true. Set now contains [2].
//    System.out.println(randomizedSet.remove(1)); // Removes 1 from the set, returns true. Set now contains [2].

    String[] commands = new String[] {"insert","getRandom","getRandom","getRandom","insert","insert","insert","insert","insert","getRandom","getRandom","insert","getRandom","insert","insert","getRandom","getRandom","getRandom","getRandom","remove","insert","getRandom","getRandom","insert","remove","remove","insert","getRandom","getRandom","insert","insert","getRandom","remove","remove","insert","remove","getRandom","getRandom","remove","getRandom","insert","insert","getRandom","remove","remove","remove","getRandom","insert","insert","insert","insert","getRandom","insert","getRandom","remove","insert","insert","insert","getRandom","getRandom","insert","getRandom","insert","insert","getRandom","getRandom","remove","getRandom","remove","insert","getRandom","insert","insert","insert","getRandom","insert","insert","getRandom","insert","getRandom","insert","getRandom","getRandom","getRandom","insert","getRandom","getRandom","insert","insert","insert","getRandom","remove","insert","insert","getRandom","insert","insert","insert","insert"};
    String[] values = new String[] {"-7","","","","6","7","10","3","8","","","-8","","6","-8","","","","","2","2","","","5","-5","-8","-8","","","-4","10","","7","-1","8","-6","","","9","","7","0","","-10","-4","-3","","-4","-5","8","-2","","-9","","7","-2","7","4","","","-6","","-8","2","","","9","","-1","3","","0","-3","-1","","-8","-10","","3","","4","","","","-10","","","0","-2","5","","-2","5","10","","9","0","7","-2"};
    String[] result = new String[10000];
    for (int i = 0; i < commands.length; i++) {
      if (commands[i].equals("insert")) {
        String resStr = String.valueOf(randomizedSet.insert(Integer.parseInt(values[i])));
        result[i] = resStr;
        System.out.println(resStr);
      } else if (commands[i].equals("remove")) {
        String resStr = String.valueOf(randomizedSet.remove(Integer.parseInt(values[i])));
        result[i] = resStr;
        System.out.println(resStr);
      } else {
        String resStr = String.valueOf(randomizedSet.getRandom());
        result[i] = resStr;
        System.out.println(randomizedSet.getRandom());
      }
    }

    //[null,true,true,false,true,true,false,true,true,true,true,false,true,false,false,false,-2,false,false,true,false]
  }

  public static class RandomizedSet {

    Random random = new Random();

    Integer[] array;
    int n;

    public RandomizedSet() {
      array = new Integer[16];
      n = 0;
    }

    public boolean insert(int val) {
      if (n > array.length / 4) {
        increaseSize();
      }
      int hash = hash(val);
      Integer valInPlace = array[hash];
      if (valInPlace == null) {
        array[hash] = val;
        n++;
        return true;
      }
      while (array[hash] != null && array[hash] != val) {
        hash = (hash + 1) % array.length;
      }
      if (array[hash] != null && array[hash] == val) {
        return false;
      }
      array[hash] = val;
      n++;
      return true;
    }

    public boolean remove(int val) {
      if (n < array.length / 16) {
        decreaseSize();
      }
      int hash = hash(val);
      while (array[hash] != null && array[hash] != val) {
        hash = (hash + 1) % array.length;
      }
      if (array[hash] == null) {
        return false;
      }
      array[hash] = null;
      n--;
      hash = (hash + 1) % array.length;
      while (array[hash] != null) {
        int tmpVal = array[hash];
        array[hash] = null;
        insert(tmpVal);
        hash = (hash + 1) % array.length;
      }
      return true;
    }

    public int getRandom() {
      int index;
      do {
        index = random.nextInt(array.length);
      } while (array[index] == null);
      return array[index];
    }

    private void increaseSize() {
      int m = array.length;
      Integer[] tmp = array;
      array = new Integer[2*m];
      for (int i = 0; i < m; i++) {
        if (tmp[i] != null) {
          insert(tmp[i]);
        }
      }
    }

    private void decreaseSize() {
      int m = array.length;
      Integer[] tmp = array;
      array = new Integer[m/2];
      for (int i = 0; i < m; i++) {
        if (tmp[i] != null) {
          insert(tmp[i]);
        }
      }
    }

    private int hash(int val) {
      return Math.abs(val % array.length);
    }
  }
}
