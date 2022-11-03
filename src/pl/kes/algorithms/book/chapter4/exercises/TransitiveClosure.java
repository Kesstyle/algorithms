package pl.kes.algorithms.book.chapter4.exercises;

public class TransitiveClosure {

  private DirectedDFSKes directedDFSKes;
  private Digraph closure;

  public TransitiveClosure(Digraph g) {
    closure = new Digraph(g.V());
    for (int i = 0; i < g.V(); i++) {
      directedDFSKes = new DirectedDFSKes(g, i);
      for (int j: directedDFSKes.allMarkedExceptSource()) {
        closure.addEdge(i, j);
      }
    }
  }

  public Digraph transitiveClosure() {
    return closure;
  }
}
