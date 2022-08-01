package pl.kes.algorithms;

import java.util.HashMap;
import java.util.Map;

public class MatchingSubsequences {

  public static void main(String...args) {
    System.out.println(numMatchingSubseq("abcde", new String[] {"a","bb","acd","ace"}));
  }

  public static int numMatchingSubseq(String s, String[] words) {
    Map<String, Integer> wordsMap = new HashMap<>();
    for (int i = 0; i < words.length; i++) {
      int count = 1;
      if (wordsMap.containsKey(words[i])) {
        count = wordsMap.get(words[i]) + 1;
      }
      wordsMap.put(words[i], count);
    }

    int count = 0;
    char[] sourceWord = s.toCharArray();
    for (Map.Entry<String, Integer> wordEntry: wordsMap.entrySet()) {
      int wordIterator = 0;
      String word = wordEntry.getKey();
      for (int j = 0; j < s.length(); j++) {
        if (sourceWord[j] == word.charAt(wordIterator)) {
          wordIterator++;
        }
        if (wordIterator == word.length()) {
          count += wordEntry.getValue();
          break;
        }
      }
    }
    return count;
  }
}
