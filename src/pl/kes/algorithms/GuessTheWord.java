package pl.kes.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GuessTheWord {

  public static void main(String...args) {
    GuessTheWord guessTheWord = new GuessTheWord();
    String[] words = new String[] {"wichbx","oahwep","tpulot","eqznzs","vvmplb","eywinm","dqefpt",
        "kmjmxr","ihkovg","trbzyb","xqulhc","bcsbfw","rwzslk","abpjhw","mpubps","viyzbc",
        "kodlta","ckfzjh","phuepp","rokoro","nxcwmo","awvqlr","uooeon","hhfuzz","sajxgr",
        "oxgaix","fnugyu","lkxwru","mhtrvb","xxonmg","tqxlbr","euxtzg","tjwvad","uslult","rtjosi",
        "hsygda","vyuica","mbnagm","uinqur","pikenp","szgupv","qpxmsw","vunxdn","jahhfn","kmbeok","biywow",
        "yvgwho","hwzodo","loffxk","xavzqd","vwzpfe","uairjw","itufkt","kaklud","jjinfa","kqbttl","zocgux",
        "ucwjig","meesxb","uysfyc","kdfvtw","vizxrv","rpbdjh","wynohw","lhqxvx","kaadty","dxxwut","vjtskm",
        "yrdswc","byzjxm","jeomdc","saevda","himevi","ydltnu","wrrpoc","khuopg","ooxarg","vcvfry","thaawc",
        "bssybb","ccoyyo","ajcwbj","arwfnl","nafmtm","xoaumd","vbejda","kaefne","swcrkh","reeyhj","vmcwaf",
        "chxitv","qkwjna","vklpkp","xfnayl","ktgmfn","xrmzzm","fgtuki","zcffuv","srxuus","pydgmq"};

  }

  Map<Integer, List<String>> wordsMap = new HashMap<>();

  public void findSecretWord(String[] words, Master master) {
    guess(Arrays.asList(words), master);
  }

  private void guess(List<String> words, Master master) {
    if (words.size() == 1) {
      master.guess(words.get(0));
      return;
    }
    String our = words.get(new Random().nextInt(words.size()));
    populateMap(our, words);
    int round = master.guess(our);
    if (round == 6) {
      return;
    }
    guess(wordsMap.get(round), master);
  }

  private void populateMap(String word, List<String> words) {
    wordsMap.clear();
    for (int i = 0; i < words.size(); i++) {
      String next = words.get(i);
      if (next.equals(word)) {
        continue;
      }
      int matches = matches(word, next);
      if (wordsMap.get(matches) == null) {
        wordsMap.put(matches, new ArrayList<>());
      }
      wordsMap.get(matches).add(next);
    }
  }

  private int matches(String first, String second) {
    int sum = 0;
    for (int i = 0; i < first.length(); i++) {
      if (first.charAt(i) == second.charAt(i)) {
        sum++;
      }
    }
    return sum;
  }

  interface Master {
    int guess(String word);
  }
}
