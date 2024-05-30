package pl.kes.algorithms.book.chapter5;

import java.util.Arrays;
import java.util.List;
import edu.princeton.cs.algs4.NFA;

public class RegexClient {

  public static void main(String...args) {
    final String regex = "AB{15}";
    System.out.println("################### Regexp #########################");
    Regexp regexp = new Regexp(regex);
    List<String> tries = Arrays.asList("AB", "ABCDE", "ACE", "ABABABA", "ABBBBBBBBBBBBBB");
    tries.forEach(s -> System.out.println(s + " matches " + regex + ": " + regexp.matches(s)));

    System.out.println("################### NFA #########################");
    NFA nfa = new NFA(regex);
    tries.forEach(s -> System.out.println(s + " matches " + regex + ": " + nfa.recognizes(s)));
  }
}
