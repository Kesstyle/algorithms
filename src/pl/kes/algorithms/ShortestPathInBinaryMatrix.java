package pl.kes.algorithms;

public class ShortestPathInBinaryMatrix {

  public static void main(String ...args) {
    ShortestPathInBinaryMatrix shortestPathInBinaryMatrix = new ShortestPathInBinaryMatrix();
    int[][] grid = new int[][] {{0,0,0,0,1},{1,0,0,0,0},{0,1,0,1,0},{0,0,0,1,1},{0,0,0,1,0}};
    System.out.println(shortestPathInBinaryMatrix.shortestPathBinaryMatrix(grid));
  }

  int[][] distances;
  int n;
  int m;
  IndexMinPQ minPQ;
  int[][] increments = new int[][] {{-1, 0}, {-1, -1}, {-1, 1}, {0, 1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}};

  public int shortestPathBinaryMatrix(int[][] grid) {
    n = grid.length;
    m = grid[0].length;
    if (grid[0][0] != 0 || grid[n-1][m-1] != 0) {
      return -1;
    }
    int totalSize = n * m;
    distances = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        distances[i][j] = Integer.MAX_VALUE;
      }
    }
    minPQ = new IndexMinPQ(totalSize);
    minPQ.add(0, 1);
    distances[0][0] = 1;
    while (!minPQ.isEmpty()) {
      relax(minPQ.getMin(), grid);
    }
    if (distances[n-1][m-1] == Integer.MAX_VALUE) {
      return -1;
    }
    return distances[n-1][m-1];
  }

  private void relax(int v, int[][] grid) {
    int i = v % n;
    int j = v / n;
    for (int[] incr: increments) {
      int nextI = i + incr[0];
      int nextJ = j + incr[1];
      if (isValidCoordinate(nextI, nextJ) && grid[nextI][nextJ] == 0
          && distances[nextI][nextJ] > distances[i][j] + 1) {
        distances[nextI][nextJ] = distances[i][j] + 1;
        int key = nextI + nextJ * n;
        if (minPQ.exists(key)) {
          minPQ.update(key, distances[nextI][nextJ]);
        } else {
          minPQ.add(key, distances[nextI][nextJ]);
        }
      }
    }
  }

  private boolean isValidCoordinate(int i, int j) {
    return i >= 0 && i < n && j >= 0 && j < m;
  }

  class IndexMinPQ {

    int[] values;
    int[] indexes;
    int[] backwardIndexes;
    int size;

    public IndexMinPQ(int n) {
      values = new int[n + 1];
      indexes = new int[n + 1];
      backwardIndexes = new int[n + 1];
    }

    public boolean isEmpty() {
      return size == 0;
    }

    public boolean exists(int k) {
      return backwardIndexes[k] != 0;
    }

    public void add(int i, int v) {
      values[++size] = v;
      indexes[size] = i;
      backwardIndexes[i] = size;
      swim(size);
    }

    public int getMin() {
      int index = indexes[1];
      swap(size--, 1);
      sink(1);
      backwardIndexes[index] = 0;
      return index;
    }

    public void update(int k, int v) {
      int index = backwardIndexes[k];
      values[index] = v;
      swim(index);
    }

    private void swim(int i) {
      int n = i;
      int k = n / 2;
      while (k > 0) {
        if (!less(n, k)) {
          break;
        }
        swap(n, k);
        n = k;
        k = n / 2;
      }
    }

    private void sink(int i) {
      int n = i;
      int k = n * 2;
      while (k <= size) {
        if (k + 1 <= size && less(k+1, k)) {
          k = k + 1;
        }
        if (!less(k, n)) {
          break;
        }
        swap(k, n);
        n = k;
        k = n * 2;
      }
    }

    private boolean less(int i, int j) {
      return values[i] < values[j];
    }

    private void swap(int i, int j) {
      int tmp = indexes[i];
      indexes[i] = indexes[j];
      indexes[j] = tmp;

      tmp = values[i];
      values[i] = values[j];
      values[j] = tmp;

      int key1 = indexes[i];
      int key2 = indexes[j];
      tmp = backwardIndexes[key1];
      backwardIndexes[key1] = backwardIndexes[key2];
      backwardIndexes[key2] = tmp;
    }
  }
}
