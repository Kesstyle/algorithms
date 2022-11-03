package pl.kes.algorithms.book.chapter4.exercises;

import java.util.Arrays;
import pl.kes.algorithms.book.standard.StdOut;

public class DigraphTestClient {

  public static void main(String...args) {
    final int V = 12;
    final Digraph g1 = new Digraph(V);
 //   g1.addEdge(8, 4);
 //   g1.addEdge(2, 3);
    g1.addEdge(1, 11);
    g1.addEdge(0, 6);
    g1.addEdge(3, 6);
//    g1.addEdge(10, 3);
    g1.addEdge(7, 11);
    g1.addEdge(7, 8);
    g1.addEdge(11, 8);
    g1.addEdge(2, 0);
//    g1.addEdge(6, 2);
    g1.addEdge(5, 2);
    g1.addEdge(5, 10);
    g1.addEdge(3, 10);
 //   g1.addEdge(8, 1);
    g1.addEdge(4, 1);

    StdOut.println(g1);

    int w = 5;
    StdOut.print(w + ": ");
    for (Integer v: g1.adj(w)) {
      StdOut.print(v + ", ");
    }
    StdOut.println();
    final Digraph g2 = new Digraph(g1);
    StdOut.println(g2);
    TopologicalOrder topologicalOrder = new TopologicalOrder(g1);
    Iterable<Integer> topological = topologicalOrder.topologicalOrder();
 //   directedCycle.cycle().forEach(System.out::println);
    StdOut.println("Topological for g1:");
    topological.forEach(i -> StdOut.print(i + " "));
    StdOut.println();
    topologicalOrder = new TopologicalOrder(g1.reverse());
    Iterable<Integer> postReverse = topologicalOrder.postOrder();
    StdOut.println("Post for g1 reversed:");
    postReverse.forEach(i -> StdOut.print(i + " "));
    StdOut.println();
    StdOut.println("Topological for g1 reversed:");
    topologicalOrder.topologicalOrder().forEach(i -> StdOut.print(i + " "));
    StdOut.println();

//    StdOut.println(g1.isTopology(topological));

    Digraph trans = new Digraph(10);
    trans.addEdge(3, 7);
    trans.addEdge(1, 4);
    trans.addEdge(7, 8);
    trans.addEdge(0, 5);
    trans.addEdge(5, 2);
    trans.addEdge(3, 8);
    trans.addEdge(2, 9);
    trans.addEdge(0, 6);
    trans.addEdge(4, 9);
    trans.addEdge(2, 6);
    trans.addEdge(6, 4);
    StdOut.println("Transitive closure:");
    StdOut.println(new TransitiveClosure(trans).transitiveClosure());

    StrongComponents strongComponents = new StrongComponents(g1);
    StdOut.println(strongComponents);

    Digraph cycled = new Digraph(3);
    cycled.addEdge(0, 1);
    cycled.addEdge(1, 0);
    cycled.addEdge(0, 2);
    cycled.addEdge(2, 1);
    strongComponents = new StrongComponents(cycled);
    StdOut.println(strongComponents);
  }
}
