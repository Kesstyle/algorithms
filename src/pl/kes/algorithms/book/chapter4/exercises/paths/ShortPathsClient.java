package pl.kes.algorithms.book.chapter4.exercises.paths;

import pl.kes.algorithms.book.standard.In;
import pl.kes.algorithms.book.standard.StdOut;

public class ShortPathsClient {

  public static void main(String... args) {
    EdgeWeightedDigraph g = new EdgeWeightedDigraph(new In("edgeWeightedDigraph.txt"));
    StdOut.println(g);

    int src = 0;
    DeixtraSP deixtraSP = new DeixtraSP(g, src);
    for (int j = 0; j < g.V(); j++) {
      StdOut.println("Path to " + j);
      deixtraSP.pathTo(j).forEach(StdOut::println);
      StdOut.println();
    }

    DeixtraSeveralSourcesSP deixtraSeveralSourcesSP = new DeixtraSeveralSourcesSP(g, new int[] {0, 1, 2, 3});
    for (int i = 0; i < g.V(); i++) {
      StdOut.println("Path to " + i);
      deixtraSeveralSourcesSP.pathTo(i).forEach(StdOut::println);
      StdOut.println();
    }

    int source = 0;
    int sink = 6;
    DeixtraSourceSinkSP deixtraSourceSinkSP = new DeixtraSourceSinkSP(g, source, sink);
    StdOut.println("Path from " + source + " to " + sink);
    deixtraSourceSinkSP.pathToSink().forEach(StdOut::println);
    StdOut.println();

    int[] sources = new int[] {0, 1, 2};
    int[] sinks = new int[] {5, 6, 7};
    DeixtraFromSToTSP deixtraFromSToTSP = new DeixtraFromSToTSP(g, sources, sinks);
    StdOut.println("Path from S to T");
    deixtraFromSToTSP.pathToSink().forEach(StdOut::println);
    StdOut.println();

    int sourceSat = 0;
    EdgeWeightedSaturatedDigraph saturatedDigraph = new EdgeWeightedSaturatedDigraph(new In("edgeWeightedDigraph.txt"));
    DeixtraSaturatedSP deixtraSaturatedSP = new DeixtraSaturatedSP(saturatedDigraph, sourceSat);
    for (int j = 0; j < g.V(); j++) {
      StdOut.println("Path to " + j);
      deixtraSaturatedSP.pathTo(j).forEach(StdOut::println);
      StdOut.println();
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
//    EdgeWeightedDigraph gNegative = new EdgeWeightedDigraph(new In("edgeWeightedNegativeDigraph.txt"));
//    BellmanFordEdgeWeightedDigraphSP digraphSP = new BellmanFordEdgeWeightedDigraphSP(gNegative, 0);
//    if (digraphSP.negativeCycle() == null) {
//      digraphSP.pathTo(1).forEach(StdOut::println);
//    } else {
//      digraphSP.negativeCycle().forEach(StdOut::println);
//    }
  }
}
