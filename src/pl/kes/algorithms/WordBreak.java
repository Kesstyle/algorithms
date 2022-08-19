package pl.kes.algorithms;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class WordBreak {

  public static void main(String... args) {
    WordBreak wordBreak = new WordBreak();
 //   String s =
//        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
//    List<String> wordDict =
//        List.of("aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa", "ba");
    String s = "catsanddog";
    List<String> wordDict = List.of("cats","dog","sand","and","cat");
    System.out.println(wordBreak.wordBreak(s, wordDict));
  }

  Boolean[] matches;

  public boolean wordBreak(String s, List<String> wordDict) {
    matches = new Boolean[s.length() + 1];
    Set<String> cleanedWords = new HashSet<>();
    for (int i = 0; i < wordDict.size(); i++) {
      String word = wordDict.get(i);
      if (s.contains(word)) {
        cleanedWords.add(word);
      }
    }
    return wordBreak(s, cleanedWords, 0, s.length());
  }

  private boolean wordBreak(String s, Set<String> wordDict, int begin, int end) {
    if (begin >= end) {
      return true;
    }
    if (matches[begin] != null) {
      return matches[begin];
    }
    for (int i = begin + 1; i <= end; i++) {
      if (wordDict.contains(s.substring(begin, i)) && wordBreak(s, wordDict, i, end)) {
        return matches[begin] = true;
      }
    }
    return matches[begin] = false;
  }
//
//  public boolean wordBreak(String s, List wordDict) {
//    // Init set
//    HashSet set = new HashSet<>();
//    // Init length of word
//    int n = s.length();
//    // Add all words to set
//    for (String str : wordDict) {
//      set.add(str);
//    }
//    // init indicative array
//    int[] dp = new int[n];
//    // Set default values
//    Arrays.fill(dp, -1);
//    // if for index=0 value is 1 - then return true
//    if (func(0, s.length(), s, set, dp) == 1) {
//      return true;
//    }
//    // if index != 0 return false
//    return false;
//  }
//
//  public static int func(int index, int end, String s, HashSet set, int[] dp) {
//    // if start == end return 1
//    if (index == end) {
//      return 1;
//    }
//    // if we initialized value - return it
//    if (dp[index] != -1) {
//      return dp[index];
//    }
//    // for i from start to end
//    for (int i = index + 1; i <= end; i++) {
//      // if there is word containing from start to end
//      if (set.contains(s.substring(index, i))) {
//        // check then for higher index
//        if (func(i, end, s, set, dp) == 1) {
//          // if so - we can create word from the beginning
//          return dp[index] = 1;
//        }
//      }
//    }
//    // else return 0
//    return dp[index] = 0;
//  }
}
