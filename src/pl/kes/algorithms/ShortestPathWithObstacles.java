package pl.kes.algorithms;

import java.util.HashSet;
import java.util.Set;

public class ShortestPathWithObstacles {

  public static void main(String... args) {
    ShortestPathWithObstacles obstacles = new ShortestPathWithObstacles();
    int[][] grid = new int[][] {{0,1,0,1,0,1,0,0,0,1,0,1,1,1,1,0,1,1,0,1,0,1,1,1,0,0,0,1},
        {1,1,0,1,1,0,0,0,0,0,1,1,0,0,0,0,1,0,1,1,1,0,0,1,1,1,1,0},
        {1,0,0,1,1,1,0,1,0,1,0,1,1,0,1,1,0,1,0,1,0,1,0,1,1,1,0,1},
        {1,0,0,0,0,0,0,0,1,1,1,0,1,1,0,1,1,0,1,0,1,0,0,1,1,0,0,1},
        {1,0,0,0,0,0,1,0,1,0,1,0,0,0,0,1,0,1,0,1,0,0,0,1,1,1,0,1},
        {0,0,0,0,1,1,0,0,1,1,0,0,0,0,1,1,1,0,0,0,0,1,0,0,1,0,0,0},
        {0,0,1,1,0,0,0,0,0,0,1,1,0,1,1,1,1,0,0,1,1,0,1,0,0,0,0,0},
        {1,0,1,0,1,1,1,0,1,1,1,1,1,0,0,0,1,0,1,1,0,0,1,0,0,0,1,0},
        {0,0,1,1,0,0,0,0,1,0,1,0,0,0,0,1,1,1,0,0,1,1,1,0,1,1,0,1},
        {1,0,0,0,0,1,1,0,0,1,1,0,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,0,1,0,0,0,0,1,1,0,1,0,0,1,0,0,0,0,1,1,0,1,0,0,0},{1,0,0,1,0,1,1,0,0,0,0,0,0,0,1,1,0,1,1,1,1,1,1,0,1,0,1,0},{1,1,1,1,0,1,1,0,0,1,0,1,1,1,1,1,0,1,0,0,1,1,1,0,0,0,1,1},{1,0,1,0,0,1,0,1,1,1,0,0,1,0,1,1,0,0,0,0,0,1,1,0,0,1,0,0},{1,0,0,0,1,0,1,1,0,0,0,0,0,1,0,0,0,1,0,1,0,1,1,0,1,1,1,0},{1,0,0,0,1,1,0,1,1,0,1,1,1,1,1,0,0,0,1,0,0,0,0,1,1,0,0,0},{0,0,1,0,1,0,0,1,1,0,1,0,1,1,1,0,1,1,1,0,0,1,1,0,1,0,1,0}};
    int k = 249;
    System.out.println(obstacles.shortestPath(grid, k));
    System.out.println(obstacles.shortestPathQueue(grid, k));
  }

  Set<Integer> posSet;
  Queue<State> positions;
  Heap<State> heap;
  int X;
  int Y;

  public int shortestPath(int[][] grid, int k) {
    X = grid.length;
    Y = grid[0].length;
    heap = new Heap<>();
    posSet = new HashSet<>();
    heap.add(new State(0, 0, k, 0, manhattanPath(0, 0, X - 1, Y - 1)));
    posSet.add(2000 * k);
    int res = Integer.MAX_VALUE;
    while (!heap.isEmpty()) {
      State next = heap.getMin();
      int x = next.x;
      int y = next.y;
      int remK = next.k;
      int n = next.n;


      if (manhattanPath(x, y, X - 1, Y - 1) <= remK) {
        return next.est;
      }
      processForHeap(grid, x + 1, y, remK, n + 1);
      processForHeap(grid, x - 1, y, remK, n + 1);
      processForHeap(grid, x, y + 1, remK, n + 1);
      processForHeap(grid, x, y - 1, remK, n + 1);
    }
    return res == Integer.MAX_VALUE ? -1 : res;
  }

  public int shortestPathQueue(int[][] grid, int k) {
    X = grid.length;
    Y = grid[0].length;
    positions = new Queue<>();
    posSet = new HashSet<>();
    positions.add(new State(0, 0, k, 0));
    posSet.add(2000 * k);
    int res = Integer.MAX_VALUE;
    while (!positions.isEmpty()) {
      State next = positions.get();
      int x = next.x;
      int y = next.y;
      int remK = next.k;
      int n = next.n;

      if (x == X - 1 && y == Y - 1 && res > n) {
        return n;
      }
      process(grid, x + 1, y, remK, n + 1);
      process(grid, x - 1, y, remK, n + 1);
      process(grid, x, y + 1, remK, n + 1);
      process(grid, x, y - 1, remK, n + 1);
    }
    return res == Integer.MAX_VALUE ? -1 : res;
  }

  private int manhattanPath(int x0, int y0, int x1, int y1) {
    return y1 - y0 + x1 - x0;
  }

  private void processForHeap(int[][] grid, int x, int y, int k, int n) {
    if (x >= X || x < 0) {
      return;
    }
    if (y >= Y || y < 0) {
      return;
    }
    if (k < grid[x][y]) {
      return;
    }
    if (posSet.contains(x + X * y + X * Y * (k - grid[x][y]))) {
      return;
    }
    heap.add(new State(x, y, k - grid[x][y], n, manhattanPath(x, y, X - 1, Y - 1)));
    posSet.add(x + X * y + X * Y * (k - grid[x][y]));
  }

  private void process(int[][] grid, int x, int y, int k, int n) {
    if (x >= X || x < 0) {
      return;
    }
    if (y >= Y || y < 0) {
      return;
    }
    if (k < grid[x][y]) {
      return;
    }
    if (posSet.contains(x + X * y + X * Y * (k - grid[x][y]))) {
      return;
    }
    positions.add(new State(x, y, k - grid[x][y], n));
    posSet.add(x + X * y + X * Y * (k - grid[x][y]));
  }

  class State implements Comparable<State> {
    int x;
    int y;
    int k;
    int n;
    int est;

    public State(final int x, final int y, final int k, final int n) {
      this.x = x;
      this.y = y;
      this.k = k;
      this.n = n;
    }

    public State(final int x, final int y, final int k, final int n, final int est) {
      this.x = x;
      this.y = y;
      this.k = k;
      this.n = n;
      this.est = est + n;
    }

    @Override
    public int compareTo(final State o) {
      return est - o.est;
    }
  }

  class Heap<T extends Comparable<T>> {

    T[] arr;
    int size;

    public Heap() {
      arr = (T[]) new Comparable[16];
    }

    public void add(T elem) {
      arr[++size] = elem;
      if (size * 2 > arr.length) {
        resize();
      }
      swim(size);
    }

    public boolean isEmpty() {
      return size == 0;
    }

    public T getMin() {
      T res = arr[1];
      arr[1] = arr[size--];
      sink(1);
      return res;
    }

    private void swim(int i) {
      int n = i;
      int k = i / 2;
      while (k >= 1) {
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
      int k = 2 * n;
      while (k <= size) {
        if (k + 1 <= size && less(k + 1, k)) {
          k = k + 1;
        }
        swap(n, k);
        n = k;
        k = 2 * n;
      }
    }

    private void swap(int x, int y) {
      T tmp = arr[x];
      arr[x] = arr[y];
      arr[y] = tmp;
    }

    private boolean less(int x, int y) {
      if (arr[x] == null || arr[y] == null) {
        return false;
      }
      return arr[x].compareTo(arr[y]) < 0;
    }

    private void resize() {
      T[] tmp = (T[]) new Comparable[arr.length * 2];
      for (int i = 0; i < arr.length; i++) {
        tmp[i] = arr[i];
      }
      arr = tmp;
    }
  }

  class Queue<T> {

    T[] elems;
    int size;
    int start;

    public Queue() {
      elems = (T[]) new Object[8];
    }

    public void add(T elem) {
      elems[(start + size) % elems.length] = elem;
      size++;
      if (size * 2 > elems.length) {
        resize();
      }
    }

    public T get() {
      T res = elems[start];
      start = (start + 1) % elems.length;
      size--;
      return res;
    }

    public int size() {
      return size;
    }

    public boolean isEmpty() {
      return size() == 0;
    }

    private void resize() {
      T[] tmp = (T[]) new Object[elems.length * 2];
      for (int i = 0; i < size; i++) {
        tmp[i] = elems[(start + i) % elems.length];
      }
      start = 0;
      elems = tmp;
    }
  }
}
