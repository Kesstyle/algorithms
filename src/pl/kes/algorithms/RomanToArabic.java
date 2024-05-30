package pl.kes.algorithms;

import java.util.HashMap;
import java.util.Map;

public class RomanToArabic {

  Map<String, Integer> map = new HashMap<>() {{
    put("I", Integer.valueOf(1));
    put("V", Integer.valueOf(5));
      put("X", Integer.valueOf(10));
      put("L", Integer.valueOf(50));
      put("C", Integer.valueOf(100));
      put("D", Integer.valueOf(500));
      put("M", Integer.valueOf(1000));
      put("IV", Integer.valueOf(4));
      put("XL", Integer.valueOf(40));
      put("IX", Integer.valueOf(9));
      put("XC", Integer.valueOf(90));
      put("CD", Integer.valueOf(400));
      put("CM", Integer.valueOf(900)); }};

  public int romanToInt(String s) {
    int n = s.length();
    int i = 0;
    int sum = 0;
    while (i < n) {
      if (i == n - 1) {
        sum += map.get(s.substring(i, i + 1));
        break;
      }
      Integer next = map.get(s.substring(i, i + 2));
      if (next == null) {
        sum += map.get(s.substring(i, i + 1));
        i++;
      } else {
        sum += next;
        i += 2;
      }
    }
    return sum;
  }
}
