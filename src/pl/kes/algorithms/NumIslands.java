package pl.kes.algorithms;

public class NumIslands {

  public static void main(String...args) {
    NumIslands numIslands = new NumIslands();
    char[][] grid = new char[][] {
        {'1','1','0','0','0'},
        {'1','1','0','0','0'},
        {'0','0','1','0','0'},
        {'0','0','0','1','1'}
    };
    int num = numIslands.numIslands(grid);
    System.out.println(num);
  }

  private int n;
  private int m;
  private int newIslandInt = 2;
  private char newIsland;

  public int numIslands(char[][] grid) {
    m = grid.length;
    n = grid[0].length;
    newIsland = (char)(newIslandInt + '0');

    for (int i = 0; i < m; i ++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == '1') {
          drawIsland(grid, i, j);
          newIslandInt++;
          newIsland = (char)(newIslandInt + '0');
        }
      }
    }
    return newIslandInt - 2;
  }

  private void drawIsland(char[][] grid, int x, int y) {
    if (x > 0) {
      chechForIsland(grid, x-1, y);
    }
    if (y > 0) {
      chechForIsland(grid, x, y-1);
    }
    if (x < m - 1) {
      chechForIsland(grid, x + 1, y);
    }
    if (y < n - 1) {
      chechForIsland(grid, x, y + 1);
    }
  }

  private void chechForIsland(char[][] grid, int x1, int y1) {
    if (grid[x1][y1] == '1') {
      grid[x1][y1] = newIsland;
      drawIsland(grid,x1, y1);
    }
  }
}
