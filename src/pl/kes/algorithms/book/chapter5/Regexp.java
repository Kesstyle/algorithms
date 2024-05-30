package pl.kes.algorithms.book.chapter5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;
import pl.kes.algorithms.book.chapter4.exercises.Digraph;
import pl.kes.algorithms.book.chapter4.exercises.DirectedDFSKes;

public class Regexp {

  private String regex;
  private Digraph g;

  public Regexp(String template) {
    if (template.contains("{")) {
      regex = multipliedPattern(template);
    } else {
      regex = template;
    }
    int n = regex.length();
    g = new Digraph(n + 1);
    Stack<Integer> regexStack = new Stack<>();
    for (int i = 0; i < n; i++) {
      int lp = i;
      char c = regex.charAt(i);
      if (c == '(' || c == '|') {
        regexStack.push(i);
      } else if (c == ')') {
        int or = regexStack.pop();
        if (regex.charAt(or) == '|') {
          lp = regexStack.pop();
          g.addEdge(lp, or + 1);
          g.addEdge(or, i);
        } else {
          lp = or;
        }
      }
      if (i < n - 1 && regex.charAt(i + 1) == '*') {
        g.addEdge(i + 1, lp);
        g.addEdge(lp, i + 1);
      }
      if (i < n - 1 && regex.charAt(i + 1) == '+') {
        g.addEdge(i + 1, lp);
      }
      if (c == ')' || c == '(' || c == '*' || c == '+') {
        g.addEdge(i, i + 1);
      }
    }
  }

  private String multipliedPattern(String source) {
    Stack<Integer> brackets = new Stack<>();
    String res = new String(source);
    int n = source.length();
    for (int i = 0; i < n; i++) {
      boolean isBracket = false;
      int lp = i;
      if (source.charAt(i) == '(') {
        brackets.push(i);
      } else if (source.charAt(i) == ')') {
        isBracket = true;
        lp = brackets.pop() + 1;
      }
      if (i < n - 1 && source.charAt(i + 1) == '{') {
        int k = i + 1;
        while (source.charAt(k - 1) == ')') {
          k--;
        }
        String toCopy = source.substring(lp, k);
        int repeats = 0;
        int j = i + 2;
        while (source.charAt(j) != '}') {
          repeats = repeats * 10 + source.charAt(j++) - '0';
        }
        int lo = lp;
        if (isBracket) {
          lo--;
        }
        int hi = j + 1;
        res = res.replace(res.substring(lo, hi), toCopy.repeat(repeats));
      }
    }
    return res;
  }

  public boolean matches(final String txt) {
    DirectedDFSKes dfs = new DirectedDFSKes(g, 0);
    List<Integer> vertices = new ArrayList<>();
    for (int v : dfs.allMarked()) {
      vertices.add(v);
    }
    for (int i = 0; i < txt.length(); i++) {
      char c = txt.charAt(i);
      if (c == '*' || c == '(' || c == ')' || c == '|' || c == '+') {
        throw new IllegalArgumentException("We don't support metasymbols for now");
      }
      List<Integer> match = new ArrayList<>();
      for (int v : vertices) {
        if (v == regex.length()) {
          continue;
        }
        if (c == regex.charAt(v) || regex.charAt(v) == '.') {
          match.add(v + 1);
        }
      }
      if (match.isEmpty()) {
        continue;
      }
      dfs = new DirectedDFSKes(g, match);
      vertices = new ArrayList<>();
      for (int v : dfs.allMarked()) {
        vertices.add(v);
      }
    }
    for (int v : vertices) {
      if (v == regex.length()) {
        return true;
      }
    }
    return false;
  }
}
