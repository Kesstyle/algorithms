package pl.kes.algorithms;

import java.util.PriorityQueue;

public class WeakCharactersInGame {

  public static void main(String... args) {
    WeakCharactersInGame weakCharactersInGame = new WeakCharactersInGame();
    int[][] properties = new int[][] {{7,9},{10,7},{6,9},{10,4},{7,5},{7,10}};
    System.out.println(weakCharactersInGame.numberOfWeakCharacters(properties));
  }

  public int numberOfWeakCharacters(int[][] properties) {
    PriorityQueue<Pair> priorityQueue = new PriorityQueue<>((p1, p2) -> p2.attack - p1.attack);
    for (int i = 0; i < properties.length; i++) {
      priorityQueue.add(new Pair(properties[i][0], properties[i][1]));
    }

    Pair first = priorityQueue.poll();
    int prevAttack = first.attack;
    int maxDef = Integer.MIN_VALUE;
    int maxIntermediateDef = first.def;
    int count = 0;
    while (!priorityQueue.isEmpty()) {
      Pair next = priorityQueue.poll();
      if (next.attack < prevAttack) {
        maxDef = Math.max(maxDef, maxIntermediateDef);
      }
      maxIntermediateDef = Math.max(maxIntermediateDef, next.def);
      if (next.def < maxDef) {
        count++;
      }
      prevAttack = next.attack;
    }
    return count;
  }

  class Pair implements Comparable<Pair> {
    int attack;
    int def;

    public Pair(int attack, int def) {
      this.attack = attack;
      this.def = def;
    }

    public int compareTo(Pair other) {
      return attack - other.attack;
    }

    public boolean equals(Object other) {
      Pair oth = (Pair) other;
      return attack == oth.attack;
    }

    public long hashcode() {
      return 397 * attack + def;
    }

    public String toString() {
      return "(" + attack + ", " + def + ")";
    }
  }
}
