package pl.kes.algorithms.book.chapter5;

public class DfaClient {

  public static void main(String...args) {
    String pat = "aaaaaaa";
    String str = "aaaaaabaaabaaaaaaaaaaa";
    DFA dfa = new DFA(pat);
    BoyerBoore boyerBoore = new BoyerBoore(pat);
    RabinKarp rabinKarp = new RabinKarp(pat);
    System.out.println("(DFA) Position of " + pat + " in " + str + " is " + dfa.search(str) + " (N = " + str.length() + ")");
    System.out.println("(Boyer) Position of " + pat + " in " + str + " is " + boyerBoore.find(str) + " (N = " + str.length() + ")");
    System.out.println("(Rabin) Position of " + pat + " in " + str + " is " + rabinKarp.search(str) + " (N = " + str.length() + ")");
  }
}
