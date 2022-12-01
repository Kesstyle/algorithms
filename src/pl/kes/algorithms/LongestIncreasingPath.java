package pl.kes.algorithms;

public class LongestIncreasingPath {

  public static void main(String...args) {
    LongestIncreasingPath longestIncreasingPath = new LongestIncreasingPath();
    int[][] matrix = new int[][] {{1}};
    System.out.println(longestIncreasingPath.longestIncreasingPath(matrix));
  }

  int n;
  int m;
  int max;

  public int longestIncreasingPath(int[][] matrix) {
    n = matrix.length;
    m = matrix[0].length;
    max = 1;
    Integer[][] counts = new Integer[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (counts[i][j] == null) {
          recursive(matrix, counts, i, j);
        }
      }
    }
    return max;
  }

  private void recursive(int[][] matrix, Integer[][] counts, int i, int j) {
    counts[i][j] = 1;
    for (int k = i - 1, l = j; k <= i + 1; k += 2) {
      if (k >= 0 && l >= 0 && k < n && l < m && matrix[k][l] < matrix[i][j]) {
        if (counts[k][l] == null) {
          recursive(matrix, counts, k, l);
        }
        if (counts[i][j] < counts[k][l] + 1) {
          counts[i][j] = counts[k][l] + 1;
          if (max < counts[i][j]) {
            max = counts[i][j];
          }
        }
      }
    }

    for (int l = j - 1, k = i; l <= j + 1; l += 2) {
      if (k >= 0 && l >= 0 && k < n && l < m && matrix[k][l] < matrix[i][j]) {
        if (counts[k][l] == null) {
          recursive(matrix, counts, k, l);
        }
        if (counts[i][j] < counts[k][l] + 1) {
          counts[i][j] = counts[k][l] + 1;
          if (max < counts[i][j]) {
            max = counts[i][j];
          }
        }
      }
    }
  }
}
