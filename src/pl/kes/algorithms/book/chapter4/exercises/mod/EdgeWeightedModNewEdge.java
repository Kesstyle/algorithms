package pl.kes.algorithms.book.chapter4.exercises.mod;

public class EdgeWeightedModNewEdge {

  private PrimMST mst;
  private double maxWeight;

  public EdgeWeightedModNewEdge(EdgeWeightedGraph g, Edge e) {
    PrimMST mst = new PrimMST(g);
    EdgeWeightedGraph g1 = new EdgeWeightedGraph(g.V());
    for (Edge ed: mst.edges()) {
      g1.addEdge(ed);
    }
    g1.addEdge(e);
    EdgeWeightedCycle edgeWeightedCycle = new EdgeWeightedCycle(g1);
    double maxW = Double.NEGATIVE_INFINITY;
    for (Edge ed: edgeWeightedCycle.edgesCycle()) {
      if (ed.equals(e)) {
        continue;
      }
      if (ed.weight() > maxW) {
        maxW = ed.weight();
      }
    }
    maxWeight = maxW;
  }

  public double getMaxWeight() {
    return maxWeight;
  }
}
