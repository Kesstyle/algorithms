package pl.kes.algorithms;

import java.util.ArrayList;
import java.util.List;

public class CourseSchedule2 {

  public static void main(String... args) {
    CourseSchedule2 courseSchedule2 = new CourseSchedule2();
    int numCourses = 1;
    int[][] prerequisites = new int[][] {};
    int[] result = courseSchedule2.findOrder(numCourses, prerequisites);
    for (int i = 0; i < result.length; i++) {
      System.out.print(result[i] + " ");
    }
  }

  Node[] sequence;
  int[] answer;
  int answerIndex;

  public int[] findOrder(int numCourses, int[][] prerequisites) {
    sequence = new Node[numCourses];
    answer = new int[numCourses];
    for (int i = 0; i < numCourses; i++) {
      sequence[i] = new Node(i);
    }
    for (int i = 0; i < prerequisites.length; i++) {
      int course = prerequisites[i][0];
      int prereq = prerequisites[i][1];
      sequence[course].prereqs.add(prereq);
    }

    for (int i = 0; i < sequence.length; i++) {
      boolean allGood = checkCourse(i);
      if (!allGood) {
        return new int[] {};
      }
    }
    for (int i = 0; i < sequence.length; i++) {
      if (!sequence[i].visited) {
        answer[answerIndex++] = i;
      }
    }
    return answer;
  }

  private boolean checkCourse(int course) {
    if (sequence[course] == null) {
      return true;
    }
    Node node = sequence[course];
    if (node.visited) {
      return true;
    }
    node.visited = true;
    node.recorded = true;
    for (int i = 0; i < node.prereqs.size(); i++) {
      int neigh = node.prereqs.get(i);
      Node neighNode = sequence[neigh];
      if (neighNode.recorded) {
        return false; // loop
      }
      if (!checkCourse(neigh)) {
        return false; // loop
      }
    }
    answer[answerIndex++] = course;
    node.recorded = false;
    return true;
  }

  class Node {

    int course;
    List<Integer> prereqs = new ArrayList<>();
    boolean visited;
    boolean recorded;

    public Node(int course, int prereq) {
      this.course = course;
      prereqs.add(prereq);
    }

    public Node(int course) {
      this.course = course;
    }
  }
}
