package pl.kes.algorithms.book.chapter4.exercises.mod;

import pl.kes.algorithms.book.standard.StdOut;

public class WeightedGraphClient {

  public static void main(String...args) {
    EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(8);
    edgeWeightedGraph.addEdge(new Edge(4, 5, .35));
    edgeWeightedGraph.addEdge(new Edge(4, 7, .37));
    edgeWeightedGraph.addEdge(new Edge(5, 7, .28));
    edgeWeightedGraph.addEdge(new Edge(0, 7, .16));
    edgeWeightedGraph.addEdge(new Edge(1, 5, .32));
    edgeWeightedGraph.addEdge(new Edge(0, 4, .38));
    edgeWeightedGraph.addEdge(new Edge(2, 3, .17));
    edgeWeightedGraph.addEdge(new Edge(1, 7, .19));
    edgeWeightedGraph.addEdge(new Edge(0, 2, .26));
    edgeWeightedGraph.addEdge(new Edge(1, 2, .36));
    edgeWeightedGraph.addEdge(new Edge(1, 3, .29));
    edgeWeightedGraph.addEdge(new Edge(2, 7, .34));
    edgeWeightedGraph.addEdge(new Edge(6, 2, .40));
    edgeWeightedGraph.addEdge(new Edge(3, 6, .52));
    edgeWeightedGraph.addEdge(new Edge(6, 0, .58));
    edgeWeightedGraph.addEdge(new Edge(6, 4, .93));
    MST mst = new MST(edgeWeightedGraph);
    StdOut.println("MST: ");
    mst.edges().forEach(StdOut::println);

    PrimMST primMST = new PrimMST(edgeWeightedGraph);
    StdOut.println("Energic MST: ");
    primMST.edges().forEach(StdOut::println);

    StdOut.println("Cycle:");
    EdgeWeightedCycle edgeWeightedCycle = new EdgeWeightedCycle(edgeWeightedGraph);
    for (Edge e: edgeWeightedCycle.edgesCycle()) {
      StdOut.println(e.either() + " - " + e.other(e.either()) + ": " + e.weight());
    }

    EdgeWeightedModNewEdge modNewEdge = new EdgeWeightedModNewEdge(edgeWeightedGraph, new Edge(0, 1, 15.00));
    StdOut.println(modNewEdge.getMaxWeight());

    MSTByRemoval mstByRemoval = new MSTByRemoval(edgeWeightedGraph);
    StdOut.println("MST by removal: ");
    mstByRemoval.edges().forEach(StdOut::println);
  }
}
