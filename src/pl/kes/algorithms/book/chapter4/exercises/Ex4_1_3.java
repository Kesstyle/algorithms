package pl.kes.algorithms.book.chapter4.exercises;

import pl.kes.algorithms.book.standard.StdOut;

public class Ex4_1_3 {

  public static void main(String...args) {
    Graph graph = new Graph(5);
    graph.addEdge(1, 2);
    graph.addEdge(0, 2);
    graph.addEdge(1, 4);

    graph.print();
    StdOut.println("==============================================");
    Graph newGraph = new Graph(graph);
    newGraph.print();

    graph.addEdge(0, 4);
    graph.addEdge(0, 1);

    StdOut.println("==============================================");
    graph.print();

    StdOut.println("==============================================");
    newGraph.print();
    StdOut.println("==============================================");
    StdOut.println(graph.hasEdge(0, 4));
    StdOut.println(newGraph.hasEdge(0, 4));
    StdOut.println("==============================================");
    StdOut.println("==============================================");
    int i = 0, j = 2;
    StdOut.println("Connected " + i + " and " + j + "?");
    StdOut.println(new SearchUF(graph, i).marked(j));
    StdOut.println("==============================================");
    StdOut.println("==============================================");
    Graph exGraph = new Graph(12);
    exGraph.addEdge(8, 4);
    exGraph.addEdge(2, 3);
    exGraph.addEdge(1, 11);
    exGraph.addEdge(0, 6);
    exGraph.addEdge(3, 6);
    exGraph.addEdge(10, 3);
    exGraph.addEdge(7, 11);
    exGraph.addEdge(7, 8);
    exGraph.addEdge(11, 8);
    exGraph.addEdge(2, 0);
    exGraph.addEdge(6, 2);
    exGraph.addEdge(5, 2);
    exGraph.addEdge(5, 10);
    exGraph.addEdge(3, 10);
    exGraph.addEdge(8, 1);
    exGraph.addEdge(4, 1);
    GraphProperties graphProperties = new GraphProperties(exGraph);
    int k = 0;
    StdOut.println("Eccentricity of " + k + " is " + graphProperties.eccentricity(k)
        + ", center is " + graphProperties.center() + ", radius is " + graphProperties.radius() + ", diameter = " +
        graphProperties.diameter() + ", shortest loop = " + graphProperties.girth());
  }


}
