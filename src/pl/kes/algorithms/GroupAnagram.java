package pl.kes.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagram {

  public static void main(String...args) {
    GroupAnagram groupAnagram = new GroupAnagram();
    String[] strs = new String[] {"eat","tea","tan","ate","nat","bat"};
    List<List<String>> result = groupAnagram.groupAnagrams(strs);
    for (int i = 0; i < result.size(); i++) {
      for (int j = 0; j < result.get(i).size(); j++) {
        System.out.print(result.get(i).get(j) + " ");
      }
      System.out.println();
    }
  }

  public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> result = new HashMap<>();
    for (int i = 0; i < strs.length; i++) {
      String str = strs[i];
      String alignedStr = alignStr(str);
      if (result.get(alignedStr) == null) {
        result.put(alignedStr, new ArrayList<>());
      }
      result.get(alignedStr).add(str);
    }
    return new ArrayList<>(result.values());
  }

  private String alignStr(String str) {
    char[] charArray = str.toCharArray();
    Arrays.sort(charArray);
    return new String(charArray);
  }

//  class Str {
//
//    String str;
//
//    public Str(String str) {
//      this.str = str;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//      Str str = (Str) o;
//      if (str == null || str.str == null) {
//        return false;
//      }
//      if (str.str.length() != this.str.length()) {
//        return false;
//      }
//      char[] a = this.str.toCharArray();
//      char[] b = str.str.toCharArray();
//      Arrays.sort(a);
//      Arrays.sort(b);
//      for (int i = 0; i < a.length; i++) {
//        if (a[i] != b[i]) {
//          return false;
//        }
//      }
//      return true;
//    }
//
//    @Override
//    public int hashCode() {
//      char[] a = this.str.toCharArray();
//      int hash = 0;
//      for (int i = 0; i < a.length; i++) {
//        hash += a[i];
//      }
//      return hash;
//    }
//  }
}
