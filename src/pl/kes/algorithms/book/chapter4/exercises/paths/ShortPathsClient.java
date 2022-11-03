package pl.kes.algorithms.book.chapter4.exercises.paths;

import pl.kes.algorithms.book.standard.In;
import pl.kes.algorithms.book.standard.StdOut;

public class ShortPathsClient {

  public static void main(String... args) {
    EdgeWeightedDigraph g = new EdgeWeightedDigraph(new In("edgeWeightedNegativeDigraph.txt"));
    StdOut.println(g);

    for (int i = 0; i < g.V(); i++) {
      DeixtraSP deixtraSP = new DeixtraSP(g, i);
      for (int j = 0; j < g.V(); j++) {
        if (i == j) {
          continue;
        }
        StdOut.println("Path from " + i + " to " + j);
        deixtraSP.pathTo(j).forEach(StdOut::println);
        StdOut.println();
      }
    }

//    EdgeWeightedDigraph g = new EdgeWeightedDigraph(new In("edgeWeightedDigraph.txt"));
//    StdOut.println(g);

    // Deixtra
//    int v = 2;
//    DeixtraSP deixtraSP = new DeixtraSP(g, v);
//    for (int i = 0; i < g.V(); i++) {
//      if (i == v) {
//        continue;
//      }
//      StdOut.println("Path to " + i);
//      deixtraSP.pathTo(i).forEach(StdOut::println);
//      StdOut.println();
//    }
//
//    StdOut.println();
//    StdOut.println("Negative");
    EdgeWeightedDigraph gNegative = new EdgeWeightedDigraph(new In("edgeWeightedNegativeDigraph.txt"));
    BellmanFordEdgeWeightedDigraphSP digraphSP = new BellmanFordEdgeWeightedDigraphSP(gNegative, 0);
    if (digraphSP.negativeCycle() == null) {
      digraphSP.pathTo(1).forEach(StdOut::println);
    } else {
      digraphSP.negativeCycle().forEach(StdOut::println);
    }
  }
}
