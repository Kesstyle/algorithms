package pl.kes.algorithms;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {

  public static void main(String...args) {
    generate(5);
  }

  public static List<List<Integer>> generate(int numRows) {
    List<List<Integer>> result = new ArrayList<>(List.of(new ArrayList<>(List.of(1))));
    if (numRows == 1) {
      return result;
    }

    List<Integer> previousList = List.of(1);
    for (int j = 2; j <= numRows; j++) {
      List<Integer> listToAdd = new ArrayList<>();
      for (int i = 0; i < j; i++) {
        if (i == 0 || i == j - 1) {
          listToAdd.add(1);
        } else {
          listToAdd.add(previousList.get(i) + previousList.get(i - 1));
        }
      }
      result.add(listToAdd);
      previousList = listToAdd;
    }
    return result;
  }
}
