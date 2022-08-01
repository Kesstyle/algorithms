package pl.kes.algorithms;

import java.util.ArrayList;
import java.util.List;
import pl.kes.algorithms.book.standard.StdOut;

public class SpiralMatrix {

  public static void main(String...args) {
    SpiralMatrix spiralMatrix = new SpiralMatrix();
    List<Integer> result = spiralMatrix.spiralOrder(new int[][] {{1,2,3,4},{5,6,7,8},{9,10,11,12}});
    StdOut.print(result);
  }

  public List<Integer> spiralOrder(int[][] matrix) {
    return spiralOrder(matrix, 0);
  }

  public List<Integer> spiralOrder(int[][] matrix, int delta) {
    int x = matrix.length - delta*2;
    int y = matrix[0].length - delta*2;
    if (x <= 0 || y <= 0) {
      return List.of();
    }
    if (x == 1 && y == 1) {
      return List.of(matrix[delta][delta]);
    }
    int sizeX = matrix.length;
    int sizeY = matrix[delta].length;
    List<Integer> result = new ArrayList<>();

    while (delta * 2  < sizeX && delta * 2 < sizeY) {
      for (int j = delta; j < sizeY - delta; j++) {
//        StdOut.println("1: " + delta + " " + j);
        result.add(matrix[delta][j]);
      }
      for (int i = delta + 1; i < sizeX - delta; i++) {
//        StdOut.println("2: " +i + " " + (sizeY - delta - 1));
        result.add(matrix[i][sizeY - delta - 1]);
      }

      if (sizeX - delta * 2 <= 1) {
        break;
      }
      for (int j = sizeY - delta - 2; j >= delta; j--) {
//        StdOut.println("3: " +(sizeX - delta - 1) + " " + j);
        result.add(matrix[sizeX - delta - 1][j]);
      }
      if (sizeY - delta * 2 <= 1) {
        break;
      }
      for (int i = sizeX - delta - 2; i > delta; i--) {
//        StdOut.println("4: " +i + " " + delta);
        result.add(matrix[i][delta]);
      }
      delta++;
    }

    return result;
  }
}
