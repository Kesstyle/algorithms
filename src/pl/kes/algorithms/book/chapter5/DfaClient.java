package pl.kes.algorithms.book.chapter5;

public class DfaClient {

  public static void main(String... args) {
    String pat = "aaaaa";
    String str = "aaaaaabaaabaaaaaaaaaaa";
    DFA dfa = new DFA(pat);
    BoyerMoore boyerMoore = new BoyerMoore(pat);
    RabinKarp rabinKarp = new RabinKarp(pat);
    BruteForceRL bruteForceRL = new BruteForceRL();
    System.out.println("(DFA) Position of " + pat + " in " + str + " is " + dfa.search(str) + " (N = " + str.length() + ")");
    System.out
        .println("(Boyer) Position of " + pat + " in " + str + " is " + boyerMoore.search(str) + " (N = " + str.length() + ")");
    System.out
        .println("(Rabin) Position of " + pat + " in " + str + " is " + rabinKarp.search(str) + " (N = " + str.length() + ")");
    System.out
        .println("(Brute Force Right) Position of " + pat + " in " + str + " is " + bruteForceRL.search(str, pat) + " (N = " + str.length() + ")");
    System.out.println("(Brute Force Right) searchAll of " + pat + " in " + str + " is " + bruteForceRL.searchAll(str, pat));
    System.out.println("(DFA) searchAll of " + pat + " in " + str + " is " + dfa.searchAll(str));
    System.out.println("(Boyer) searchAll of " + pat + " in " + str + " is " + boyerMoore.searchAll(str));
    System.out.println("(Rabin) searchAll of " + pat + " in " + str + " is " + rabinKarp.searchAll(str));

    WhitespacesSearch whitespacesSearch = new WhitespacesSearch();
    String str1 = "AVB   EE g   h  dd wf ";
    String str2 = "ABED S    DSADFWEWQE";
    int M = 4;
    System.out.println("For " + str1 + " " + M + " of length " + str1.length() + " spaces start from "
        + whitespacesSearch.searchWhitespaces(str1, M));
    System.out.println("For " + str2 + " " + M + " of length " + str1.length() + " spaces start from "
        + whitespacesSearch.searchWhitespaces(str2, M));
  }
}
