package pl.kes.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PacificAtlanticOceans {

  public static void main(String... args) {
    PacificAtlanticOceans pacificAtlanticOceans = new PacificAtlanticOceans();

  }

  private List<List<Integer>> resList;
  private boolean[][] atlantic;
  private boolean[][] pacific;
  int[][] increments = new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
  private int m;
  private int n;

  public List<List<Integer>> pacificAtlantic(int[][] heights) {
    m = heights.length;
    n = heights[0].length;
    atlantic = new boolean[m][n];
    pacific = new boolean[m][n];
    resList = new ArrayList<>();
    for (int i = 0; i < m; i++) {
      dfs(heights, pacific, i, 0);
    }
    for (int i = 0; i < n; i++) {
      dfs(heights, pacific, 0, i);
    }

    for (int i = 0; i < m; i++) {
      dfs(heights, atlantic, i, n - 1);
    }
    for (int i = 0; i < n; i++) {
      dfs(heights, atlantic, m - 1, i);
    }

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (pacific[i][j] && atlantic[i][j]) {
          resList.add(Arrays.asList(i, j));
        }
      }
    }
    return resList;
  }

  private void dfs(int[][] heights, boolean[][] resArr, int x, int y) {
    if (!withinBorders(x, y)) {
      return;
    }
    resArr[x][y] = true;
    for (int i = 0; i < increments.length; i++) {
      int newX = x + increments[i][0];
      int newY = y + increments[i][1];
      if (withinBorders(newX, newY) && heights[x][y] <= heights[newX][newY] && !resArr[newX][newY]) {
        dfs(heights, resArr, newX, newY);
      }
    }
  }

  private boolean withinBorders(int x, int y) {
    return x >= 0 && x < m && y >= 0 && y < n;
  }
}
