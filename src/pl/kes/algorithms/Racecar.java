package pl.kes.algorithms;

import java.util.HashSet;
import java.util.Set;

public class Racecar {

  public static void main(String... args) {
    Racecar racecar = new Racecar();
    int target = 330;
    System.out.println(racecar.racecar(target));
  }

  public int racecar(int target) {
    Set<int[]> positionsAndSpeeds = new HashSet<>();
    Queue<int[]> queue = new Queue<>();
    queue.add(new int[] {0, 0, 1});
    positionsAndSpeeds.add(new int[] {0, 1});
    while (!queue.isEmpty()) {
      int[] nextSituation = queue.get();
      int moves = nextSituation[0];
      int pos = nextSituation[1];
      int speed = nextSituation[2];
      int aPos = pos + speed;
      int aSpeed = speed * 2;
      if (aPos == target) {
        return moves + 1;
      }
      if (!positionsAndSpeeds.contains(new int[] {aPos, aSpeed})) {
        queue.add(new int[] {moves + 1, aPos, aSpeed});
      }
      if (aPos > target && aSpeed > 0 || aPos < target && aSpeed < 0) {
        int rPos = pos;
        int rSpeed = speed > 0 ? -1 : 1;
        if (!positionsAndSpeeds.contains(new int[] {rPos, rSpeed})) {
          queue.add(new int[] {moves + 1, rPos, rSpeed});
        }
      }
    }
    return 0;
  }

  class Queue<T> {

    T[] arr;
    int size;
    int start;

    public Queue() {
      arr = (T[]) new Object[16];
    }

    public int size() {
      return size;
    }

    public boolean isEmpty() {
      return size() == 0;
    }

    public void add(T i) {
      arr[(start + size) % arr.length] = i;
      size++;
      if (size > arr.length / 2) {
        resize();
      }
    }

    public T get() {
      T res = arr[start];
      size--;
      start = (start + 1) % arr.length;
      return res;
    }

    private void resize() {
      T[] newArr = (T[]) new Object[arr.length * 2];
      for (int i = 0; i < size; i++) {
        newArr[i] = arr[(start + i) % arr.length];
      }
      start = 0;
      arr = newArr;
    }
  }
}
