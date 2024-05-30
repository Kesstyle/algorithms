package pl.kes.algorithms;

import java.util.ArrayList;
import java.util.List;

public class DetonateBombs {

  public static void main(String...args) {
    DetonateBombs detonateBombs = new DetonateBombs();
    int[][] bombs = new int[][] {{38496,37528,4845},{46272,98187,1365},{70550,7578,3223},{77200,18005,7272},{7648,58155,7628},{95708,33470,1889},{20157,92266,9823},{52803,2765,6751},{50429,63049,3002},{72582,69729,2281},{49317,35327,1922},{715,8902,9620},{21154,58349,8544},{43935,46296,6868},{7881,24144,2372},{95258,97730,6554},{5525,56971,9191},{95762,81415,2027},{62518,75469,1330},{83660,4341,6817},{30268,38781,8309},{97922,20474,4047},{39466,40057,2061},{91983,24242,5451},{92380,31509,8446},{12436,8897,5279},{28386,8556,4702},{54672,88180,1106},{17843,95337,4420},{21956,49924,1839}};
    System.out.println(detonateBombs.maximumDetonation(bombs));
  }

  List<Integer>[] adj;
  boolean[] inLoop;

  public int maximumDetonation(int[][] bombs) {
    int n = bombs.length;
    adj = new List[n];
    for (int i = 0; i < n; i++) {
      adj[i] = new ArrayList<>();
      for (int j = 0; j < n; j++) {
        if (i == j) {
          continue;
        }
        long x1 = bombs[i][0];
        long y1 = bombs[i][1];
        long r = bombs[i][2];
        long x2 = bombs[j][0];
        long y2 = bombs[j][1];
        if (r * r - (x2 - x1) * (x2 - x1) >= (y2 - y1) * (y2 - y1)) {
          adj[i].add(j);
        }
      }
    }

    int max = 0;
    for (int i = 0; i < n; i++) {
      inLoop = new boolean[n];
      int next = dfs(i, bombs);
      if (max < next) {
        max = next;
      }
    }
    return max;
  }

  private int dfs(int i, int[][] bombs) {
    inLoop[i] = true;
    int sum = 1;
    for (int x: adj[i]) {
      if (!inLoop[x]) {
        sum += dfs(x, bombs);
      }
    }
    return sum;
  }
}
