package pl.kes.algorithms;

import java.util.ArrayList;
import java.util.List;

public class CoursesSchedule {

  public static void main(String...args) {
    CoursesSchedule coursesSchedule = new CoursesSchedule();
    int numCourses = 2;
    int[][] prereqs = new int[][] {{1,0}};
    System.out.println(coursesSchedule.canFinish(numCourses, prereqs));
  }

  Node[] nodesArr;

  public boolean canFinish(int numCourses, int[][] prerequisites) {
    nodesArr = new Node[2000];
    if (prerequisites.length == 0) {
      return true;
    }
    for (int i = 0; i < prerequisites.length; i++) {
      int val = prerequisites[i][0];
      int neighbour = prerequisites[i][1];
      if (val == neighbour) {
        return false;
      }
      Node node = nodesArr[val];
      if (node == null) {
        node = new Node(val);
        nodesArr[val] = node;
      }
      if (nodesArr[neighbour] == null) {
        nodesArr[neighbour] = new Node(neighbour);
      }
      node.neighbours.add(neighbour);
    }

    for (int i = 0; i < nodesArr.length; i++) {
      if (nodesArr[i] != null && checkForLoop(nodesArr[i])) {
        return false;
      }
    }
    return true;
  }

  private boolean checkForLoop(Node node) {
    if (node.visited) {
      return false;
    }
    node.visited = true;
    node.rec = true;
    boolean loop = false;
    for (int neighbour: node.neighbours) {
      Node neigh = nodesArr[neighbour];
      if (neigh.rec) {
        return true;
      }
      if (checkForLoop(neigh)) {
        return true;
      }
    }
    node.rec = false;
    return loop;
  }

  class Node {
    int val;
    List<Integer> neighbours = new ArrayList<>();
    boolean visited;
    boolean rec;

    public Node(int val) {
      this.val = val;
    }
  }
}
