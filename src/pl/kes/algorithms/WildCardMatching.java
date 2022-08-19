package pl.kes.algorithms;

public class WildCardMatching {

  public static void main(String... args) {
    WildCardMatching wildCardMatching = new WildCardMatching();
    String str = "bbbab";
    String pattern = "*??a?";
    System.out.println(wildCardMatching.isMatch(str, pattern));
  }

  public boolean isMatch(String s, String p) {
    StringBuilder refinedPatternBuilder = new StringBuilder();
    boolean asterixFound = false;
    for (int i = 0; i < p.length(); i++) {
      char next = p.charAt(i);
      if (next == '*') {
        if (!asterixFound) {
          asterixFound = true;
        } else {
          continue;
        }
      } else {
        asterixFound = false;
      }
      refinedPatternBuilder.append(next);
    }
    return isMatch(s, 0, refinedPatternBuilder.toString(), 0);
  }

  public boolean isMatch(String s, int beginS, String p, int beginP) {
    if (beginP >= p.length()) {
      return beginS >= s.length() ? true : false;
    }
    char nextPatternChar = p.charAt(beginP);
    if (nextPatternChar != '*') {
      if (beginS >= s.length()) {
        return false;
      }
      if (nextPatternChar == '?') {
        return isMatch(s, beginS + 1, p, beginP + 1);
      } else {
        return s.charAt(beginS) == nextPatternChar ? isMatch(s, beginS + 1, p, beginP + 1) : false;
      }
    }

    if (beginP == p.length() - 1) {
      return true;
    } else {
      if (beginS >= s.length()) {
        for (int j = beginP + 1; j < p.length(); j++) {
          if (p.charAt(j) != '*') {
            return false;
          }
        }
        return true;
      }
      char nextChar = p.charAt(beginP + 1);

      int buffer = 0;
      if (nextChar == '?') {
        buffer++;
        for (int i = beginP + 2; i < p.length(); i++) {
          if (p.charAt(i) == '*') {
            if (s.length() - beginS < buffer) {
              return false;
            }
            return isMatch(s, beginS + buffer, p, i);
          }
          if (p.charAt(i) != '?') {
            break;
          }
          buffer++;
        }
      }

      if (s.length() - beginS < buffer) {
        return false;
      }
      if (beginP + 1 + buffer >= p.length()) {
        return true;
      }
      nextChar = p.charAt(beginP + 1 + buffer);
      boolean match = false;
      for (int i = beginS; i < s.length(); i++) {
        if (s.charAt(i) == nextChar && i - buffer >= beginS) {
          boolean asterixResult = isMatch(s, i, p, beginP + 1 + buffer);
   //       System.out.println((i-buffer) + " " + (beginP + 1 + buffer) + " : " + asterixResult);
          match = match || asterixResult;
        }
      }
      return match;
    }
  }
}
