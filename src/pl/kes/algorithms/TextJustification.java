package pl.kes.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextJustification {

  public static void main(String...args) {
    TextJustification textJustification = new TextJustification();
    String[] input = new String[] {"This", "is", "an", "example", "of", "text", "justification."};
    int maxWidth = 16;
    List<String> res = textJustification.fullJustify(input, maxWidth);
    res.stream().forEach(System.out::println);
  }

  List<String> nextWords = new ArrayList<>();
  Map<Integer, String> whitespaces = new HashMap<>();

  public List<String> fullJustify(String[] words, int maxWidth) {
    String whitespace = "";
    whitespaces.put(0, whitespace);
    for (int i = 1; i <= maxWidth; i++) {
      whitespace = whitespace + " ";
      whitespaces.put(i, whitespace);
    }

    List<String> result = new ArrayList<>();
    int length = 0;
    int i = 0;
    while (i < words.length) {
      if (length + words[i].length() + nextWords.size() <= maxWidth) {
        nextWords.add(words[i]);
        length += words[i++].length();
      } else {
        result.add(prepareNext(nextWords, maxWidth, length));
        length = 0;
        nextWords.clear();
      }
    }
    if (!nextWords.isEmpty()) {
      StringBuilder sb = new StringBuilder();
      int rem = maxWidth;
      for (int j = 0; j < nextWords.size() - 1; j++) {
        sb.append(nextWords.get(j)).append(" ");
        rem -= nextWords.get(j).length() + 1;
      }
      sb.append(nextWords.get(nextWords.size() - 1));
      rem -= nextWords.get(nextWords.size() - 1).length();
      if (rem != 0) {
        sb.append(whitespaces.get(rem));
      }
      result.add(sb.toString());
    }
    return result;
  }

  private String prepareNext(List<String> words, int maxWidth, int wordsLength) {
    int numberOfWhitespacesSlots = words.size() - 1;
    int numberOfWhitespaces = maxWidth - wordsLength;
    if (numberOfWhitespacesSlots == 0) {
      return words.get(0) + whitespaces.get(numberOfWhitespaces);
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < numberOfWhitespacesSlots; i++) {
      int whitespacesForNext = numberOfWhitespaces / (numberOfWhitespacesSlots - i);
      if (numberOfWhitespaces % (numberOfWhitespacesSlots - i) != 0) {
        whitespacesForNext++;
      }
      numberOfWhitespaces -= whitespacesForNext;
      sb.append(words.get(i)).append(whitespaces.get(whitespacesForNext));
    }
    sb.append(words.get(numberOfWhitespacesSlots));
    return sb.toString();
  }

}
