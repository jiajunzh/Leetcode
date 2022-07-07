package problem;

import java.util.HashSet;
import java.util.Set;

/**
 * 1. Problem 
 * You are controlling a robot that is located somewhere in a room. The room is modeled as an m x n binary grid where 
 * 0 represents a wall and 1 represents an empty slot.
 *
 * The robot starts at an unknown location in the room that is guaranteed to be empty, and you do not have access to 
 * the grid, but you can move the robot using the given API Robot.
 *
 * You are tasked to use the robot to clean the entire room (i.e., clean every empty cell in the room). The robot with 
 * the four given APIs can move forward, turn left, or turn right. Each turn is 90 degrees.
 *
 * When the robot tries to move into a wall cell, its bumper sensor detects the obstacle, and it stays on the current cell.
 *
 * Design an algorithm to clean the entire room using the following APIs:
 *
 * interface Robot {
 *   // returns true if next cell is open and robot moves into the cell.
 *   // returns false if next cell is obstacle and robot stays on the current cell.
 *   boolean move();
 *
 *   // Robot will stay on the same cell after calling turnLeft/turnRight.
 *   // Each turn will be 90 degrees.
 *   void turnLeft();
 *   void turnRight();
 *
 *   // Clean the current cell.
 *   void clean();
 * }
 * Note that the initial direction of the robot will be facing up. You can assume all four edges of the grid are all 
 * surrounded by a wall.
 *
 * Custom testing:
 *
 * The input is only given to initialize the room and the robot's position internally. You must solve this problem 
 * "blindfolded". In other words, you must control the robot using only the four mentioned APIs without knowing the 
 * room layout and the initial robot's position.
 *
 * 2. Examples
 * Example 1
 * Input: room = [[1,1,1,1,1,0,1,1],[1,1,1,1,1,0,1,1],[1,0,1,1,1,1,1,1],[0,0,0,1,0,0,0,0],[1,1,1,1,1,1,1,1]], row = 1, col = 3
 * Output: Robot cleaned all rooms.
 * Explanation: All grids in the room are marked by either 0 or 1.
 * 0 means the cell is blocked, while 1 means the cell is accessible.
 * The robot initially starts at the position of row=1, col=3.
 * From the top left corner, its position is one row below and three columns right.
 * 
 * Example 2
 * Input: room = [[1]], row = 0, col = 0
 * Output: Robot cleaned all rooms.
 *
 * 3. Constraints
 * m == room.length
 * n == room[i].length
 * 1 <= m <= 100
 * 1 <= n <= 200
 * room[i][j] is either 0 or 1.
 * 0 <= row < m
 * 0 <= col < n
 * room[row][col] == 1
 * All the empty cells can be visited from the starting position.
 */
public class RobotRoomCleaner {

  /**
   * 1. Approach 
   * Backtracking.
   * 
   * 1) Keep track of a visited set to maintain all cells that has been cleaned up
   * 2) Traverse all directions by DFS to the very end and then go back to the position where there are directions not
   * explored so far.
   * 
   * 2. Complexity 
   * - O(N - M) where N is the number of cells in rooms and M is the number of obstacles in rooms
   * - O(N - M) 
   */
  private final static int[][] DIRS = new int[][]{{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

  public void cleanRoom(Robot robot) {
    final Set<Node> visited = new HashSet<>();
    backtrack(robot, 0, 0, 0, visited);
  }

  private void backtrack(Robot robot, int row, int col, int d, Set<Node> visited) {
    visited.add(new Node(row, col));
    robot.clean();
    for (int i = 0; i < 4; i++) {
      int[] dir = DIRS[(i + d) % 4];
      int newRow = row + dir[0];
      int newCol = col + dir[1];
      if (!visited.contains(new Node(newRow, newCol)) && robot.move()) {
        backtrack(robot, newRow, newCol, (i + d) % 4, visited);
        goBack(robot);
      }
      robot.turnRight();
    }
  }

  public void goBack(Robot robot) {
    robot.turnRight();
    robot.turnRight();
    robot.move();
    robot.turnRight();
    robot.turnRight();
  }

  class Node {
    int row;
    int col;
    Node(int row, int col) {
      this.row = row;
      this.col = col;
    }
    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof Node)) {
        return false;
      }
      Node node = (Node)obj;
      return row == node.row && col == node.col;
    }
    
    @Override
    public int hashCode(){
      int res = 17;
      res = res * 31+ row;
      res = res * 31+ col;
      return res;
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
}
