package maze;

/**
 * A small demo of the maze search routines, grounded in the chapter's 4x4
 * dead-end maze: 'S' marks the start (top-left), 'E' the exit (bottom-right),
 * '#' a wall, and '.' an open cell. Each MazeSolver method answers a different
 * question about the same maze.
 */
public class Main {

  public static void main(String[] args) {
    // The 4x4 maze traced in the chapter. Start at the top-left, exit at the
    // bottom-right. '#' cells are walls the search must route around.
    char[][] maze = {
      {'S', '.', '.', '#'},
      {'.', '#', '.', '#'},
      {'.', '#', '#', '.'},
      {'.', '.', '.', 'E'},
    };

    // Print the maze, one row per line.
    System.out.println("Maze:");
    for (int row = 0; row < maze.length; row++) {
      System.out.println(new String(maze[row]));
    }
    System.out.println();

    // Each method explores the same maze a different way, but they agree on
    // whether the exit is reachable. shortestDistance reports how many steps
    // the shortest route takes.
    System.out.println("Recursive search reaches the exit: " + MazeSolver.hasPath(maze));
    System.out.println("Stack search reaches the exit: " + MazeSolver.hasPathWithStack(maze));
    System.out.println("Queue search reaches the exit: " + MazeSolver.hasPathWithQueue(maze));
    System.out.println("Shortest distance to the exit: " + MazeSolver.shortestDistance(maze));
  }
}
