package practice;

import java.util.List;
import java.util.ArrayList;

import stack.Stack;
import stack.ArrayStack;
import queue.Queue;
import queue.LinkedQueue;

/**
 * Finding a shortest route through a maze from the top-left cell to the
 * bottom-right cell. The maze is a rectangular grid of characters, where '#'
 * marks a wall and any other character marks an open cell. Movement is allowed
 * up, right, down, and left, and every move costs the same one step. Two
 * methods are provided: shortestDistance returns the fewest number of moves
 * (or -1 when the exit cannot be reached), and shortestPath returns the route
 * itself as a list of cells from start to exit. Both run a breadth-first sweep
 * with a queue; shortestPath additionally records, for each cell, the neighbor
 * it was first reached from, and reverses those links with a stack.
 */
public final class ShortestMazeDistance {

  private ShortestMazeDistance() {
    // This class should not be instantiated!
  }

  /** A cell in the maze: a row and a column. */
  private static class Position {
    int row;
    int col;

    Position(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }

  // Can we enter cell (row, col)? It must be inside the maze and not a wall.
  // The bounds checks come before reading maze[row][col] so a move off the
  // edge never accesses an invalid array index.
  private static boolean isOpen(char[][] maze, int row, int col) {
    return row >= 0
        && row < maze.length
        && col >= 0
        && col < maze[0].length
        && maze[row][col] != '#';
  }

  /**
   * Returns the fewest number of moves from the top-left cell (0, 0) to the
   * bottom-right cell, or -1 if the exit cannot be reached.
   *
   * @param maze the rectangular maze grid.
   * @return the fewest number of moves to the exit; -1 if it is unreachable.
   */
  public static int shortestDistance(char[][] maze) {
    if (!isOpen(maze, 0, 0)) {
      return -1;
    }

    int rows = maze.length;
    int cols = maze[0].length;
    int[][] distance = new int[rows][cols];
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        distance[row][col] = -1;  // not reached yet
      }
    }

    Queue<Position> toExplore = new LinkedQueue<>();
    toExplore.enqueue(new Position(0, 0));
    distance[0][0] = 0;

    int[] rowChange = {0, 1, 0, -1};
    int[] colChange = {1, 0, -1, 0};

    while (!toExplore.isEmpty()) {
      Position current = toExplore.front();
      toExplore.dequeue();

      if (current.row == rows - 1 && current.col == cols - 1) {
        return distance[current.row][current.col];
      }

      for (int i = 0; i < rowChange.length; i++) {
        int nextRow = current.row + rowChange[i];
        int nextCol = current.col + colChange[i];
        if (isOpen(maze, nextRow, nextCol)
            && distance[nextRow][nextCol] == -1) {
          distance[nextRow][nextCol] =
              distance[current.row][current.col] + 1;
          toExplore.enqueue(new Position(nextRow, nextCol));
        }
      }
    }

    return -1;
  }

  /**
   * Returns a shortest route from the top-left cell (0, 0) to the bottom-right
   * cell, in order from start to exit, or an empty list if the exit cannot be
   * reached.
   *
   * @param maze the rectangular maze grid.
   * @return the cells of a shortest route from start to exit; an empty list if
   *     the exit is unreachable.
   */
  public static List<Position> shortestPath(char[][] maze) {
    int rows = maze.length;
    int cols = maze[0].length;
    if (!isOpen(maze, 0, 0)) {
      return new ArrayList<>();  // empty: no route
    }

    Position[][] cameFrom = new Position[rows][cols];
    boolean[][] visited = new boolean[rows][cols];

    Queue<Position> toExplore = new LinkedQueue<>();
    toExplore.enqueue(new Position(0, 0));
    visited[0][0] = true;

    int[] rowChange = {0, 1, 0, -1};
    int[] colChange = {1, 0, -1, 0};

    while (!toExplore.isEmpty()) {
      Position current = toExplore.front();
      toExplore.dequeue();

      if (current.row == rows - 1 && current.col == cols - 1) {
        return reconstruct(cameFrom, current);
      }

      for (int i = 0; i < rowChange.length; i++) {
        int nextRow = current.row + rowChange[i];
        int nextCol = current.col + colChange[i];
        if (isOpen(maze, nextRow, nextCol) && !visited[nextRow][nextCol]) {
          visited[nextRow][nextCol] = true;
          cameFrom[nextRow][nextCol] = current;  // remember the way back
          toExplore.enqueue(new Position(nextRow, nextCol));
        }
      }
    }

    return new ArrayList<>();  // empty: the exit is unreachable
  }

  // Follows the cameFrom links back from the exit and reverses them with a
  // stack, so the returned list runs from start to exit.
  private static List<Position> reconstruct(Position[][] cameFrom, Position exit) {
    Stack<Position> stack = new ArrayStack<>();
    for (Position at = exit; at != null; at = cameFrom[at.row][at.col]) {
      stack.push(at);
    }

    List<Position> path = new ArrayList<>();
    while (!stack.isEmpty()) {
      path.add(stack.top());
      stack.pop();
    }
    return path;
  }
}
