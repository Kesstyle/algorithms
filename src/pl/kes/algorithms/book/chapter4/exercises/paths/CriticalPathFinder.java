package pl.kes.algorithms.book.chapter4.exercises.paths;

import pl.kes.algorithms.book.standard.In;
import pl.kes.algorithms.book.standard.StdOut;

public class CriticalPathFinder {

  public static void main(String...args) {
    In in = new In("works.txt");
    int N = in.readInt();
    in.readLine();
    EdgeWeightedDigraph g = new EdgeWeightedDigraph(2*N + 2);
    int s = 2*N, t = 2*N + 1;
    for (int i = 0; i < N; i++) {
      String[] str = in.readLine().split("\\s+");
      double weight = Double.parseDouble(str[0]);
      g.addEdge(new DirectedEdge(i, N + i, weight));
      g.addEdge(new DirectedEdge(s, i, 0));
      g.addEdge(new DirectedEdge(i + N, t, 0));
      for (int j = 1; j < str.length; j++) {
        g.addEdge(new DirectedEdge(N + i, Integer.parseInt(str[j]), 0));
      }
    }
    AcyclicLP acyclicLP = new AcyclicLP(g, s);
    StdOut.println("Works starts:");
    for (int i = 0; i < N; i ++) {
      StdOut.println(i + ":");
      for (DirectedEdge de: acyclicLP.pathTo(i)) {
        StdOut.println(de + ", ");
      }
      StdOut.println();
    }
    StdOut.printf("End: %5.1f\n", acyclicLP.distTo(t));
  }
}
