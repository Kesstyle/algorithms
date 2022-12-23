package pl.kes.algorithms;

import java.util.HashSet;
import java.util.Set;

public class RobotCleaner {

  public static void main(String...args) {
    int[][] grid = new int[][] {{1,1,1,1,1,0,1,1},{1,1,1,1,1,0,1,1},{1,0,1,1,1,1,1,1},{0,0,0,1,0,0,0,0},{1,1,1,1,1,1,1,1}};
    Robot robot = new RobotImpl(grid);
    RobotCleaner robotCleaner = new RobotCleaner();
    robotCleaner.cleanRoom(robot);
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        System.out.print(grid[i][j]);
      }
      System.out.println();
    }
  }

  Set<Integer> visited = new HashSet<>();
  int xIncrement = 1;
  int yIncrement = 0;
  int currentX = 0;
  int currentY = 0;

  public void cleanRoom(Robot robot) {
    robot.clean();
    visited.add(buildIndex(currentX, currentY));
    cleanAroundCell(robot, currentX, currentY);
  }

  private void cleanAroundCell(Robot robot, int xSource, int ySource) {
    int x = currentX;
    int y = currentY;
    robot.clean();
    visited.add(buildIndex(currentX, currentY));

    int fails = 0;
    while (fails != 4) {
      boolean move = moveIfNeeded(robot);
      if (move) {
        cleanAroundCell(robot, x, y);
        fails = 0;
      } else {
        fails++;
      }
      turnLeft(robot);
    }
    returnToSource(robot, xSource, ySource);
  }

  private void returnToSource(Robot robot, int xSource, int ySource) {
    if (xSource == currentX && ySource == currentY) {
      return;
    }
    while (currentX + xIncrement != xSource || currentY + yIncrement != ySource) {
      turnLeft(robot);
    }
    robot.move();
    currentX = xSource;
    currentY = ySource;
  }

  private boolean moveIfNeeded(Robot robot) {
    if (!visited(currentX + xIncrement, currentY + yIncrement) && robot.move()) {
      currentX = currentX + xIncrement;
      currentY = currentY + yIncrement;
      return true;
    }
    return false;
  }

  private boolean visited(int x, int y) {
    return visited.contains(buildIndex(x, y));
  }

  private void turnRight(Robot robot) {
    robot.turnRight();
    if (xIncrement == -1) {
      xIncrement = 0;
      yIncrement = 1;
    } else if (yIncrement == -1) {
      xIncrement = -1;
      yIncrement = 0;
    } else if (xIncrement == 1) {
      yIncrement = -1;
      xIncrement = 0;
    } else {
      xIncrement = 1;
      yIncrement = 0;
    }
  }

  private void turnLeft(Robot robot) {
    robot.turnLeft();
    if (xIncrement == -1) {
      xIncrement = 0;
      yIncrement = -1;
    } else if (yIncrement == -1) {
      xIncrement = 1;
      yIncrement = 0;
    } else if (xIncrement == 1) {
      yIncrement = 1;
      xIncrement = 0;
    } else {
      xIncrement = -1;
      yIncrement = 0;
    }
  }

  private int buildIndex(int x, int y) {
    return 300 * x + y;
  }

  private int getX(int index) {
    return index / 300;
  }

  private int getY(int index) {
    return index % 300;
  }
}

interface Robot {
  // Returns true if the cell in front is open and robot moves into the cell.
  // Returns false if the cell in front is blocked and robot stays in the current cell.
  public boolean move();

  // Robot will stay in the same cell after calling turnLeft/turnRight.
  // Each turn will be 90 degrees.
  public void turnLeft();

  public void turnRight();

  // Clean the current cell.
  public void clean();
}

class RobotImpl implements Robot {

  int xIncrement = 1;
  int yIncrement = 0;
  int currentX = 1;
  int currentY = 3;

  int[][] grid;

  public RobotImpl(int[][] grid) {
    this.grid = grid;
  }

  @Override
  public boolean move() {
    int nextX = currentX + xIncrement;
    int nextY = currentY + yIncrement;
    if (nextX >= grid.length || nextY >= grid[0].length || nextX < 0 || nextY < 0) {
      return false;
    }
    if (grid[currentX + xIncrement][currentY + yIncrement] == 0) {
      return false;
    }
    currentX = nextX;
    currentY = nextY;
    return true;
  }

  public void turnRight() {
    if (xIncrement == -1) {
      xIncrement = 0;
      yIncrement = 1;
    } else if (yIncrement == -1) {
      xIncrement = -1;
      yIncrement = 0;
    } else if (xIncrement == 1) {
      yIncrement = -1;
      xIncrement = 0;
    } else {
      xIncrement = 1;
      yIncrement = 0;
    }
  }

  public void turnLeft() {
    if (xIncrement == -1) {
      xIncrement = 0;
      yIncrement = -1;
    } else if (yIncrement == -1) {
      xIncrement = 1;
      yIncrement = 0;
    } else if (xIncrement == 1) {
      yIncrement = 1;
      xIncrement = 0;
    } else {
      xIncrement = -1;
      yIncrement = 0;
    }
  }

  @Override
  public void clean() {
    grid[currentX][currentY] = 2;
  }

}