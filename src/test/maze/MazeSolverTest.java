package maze;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests for MazeSolver. Each reachability scenario is checked against all three
 * searches: the recursive-with-visited search, the stack search, and the queue
 * search must agree on every maze. The distance scenarios check the queue-based
 * shortest distance.
 *
 * <p>The naive recursive search with no visited set is deliberately omitted from
 * the code under test because it loops forever on any maze with a cycle.
 *
 * <p>A maze is a char[][]; '#' is a wall and any other char is open. The start is
 * the top-left cell (0, 0) and the exit is the bottom-right cell (rows-1, cols-1).
 */
public class MazeSolverTest {

  @Test
  public void findsPathAroundDeadEndInFourByFour() {
    char[][] m = maze("S..#", ".#.#", ".##.", "...E");
    assertTrue(MazeSolver.hasPath(m));
    assertTrue(MazeSolver.hasPathWithStack(m));
    assertTrue(MazeSolver.hasPathWithQueue(m));
  }

  @Test
  public void findsPathInThreeByThreeWithCenterWall() {
    char[][] m = maze("S..", ".#.", "..E");
    assertTrue(MazeSolver.hasPath(m));
    assertTrue(MazeSolver.hasPathWithStack(m));
    assertTrue(MazeSolver.hasPathWithQueue(m));
  }

  @Test
  public void returnsFalseWhenStartIsWall() {
    char[][] m = maze("#..", "...", "..E");
    assertFalse(MazeSolver.hasPath(m));
    assertFalse(MazeSolver.hasPathWithStack(m));
    assertFalse(MazeSolver.hasPathWithQueue(m));
  }

  @Test
  public void returnsFalseWhenExitIsWall() {
    char[][] m = maze("S..", "...", "..#");
    assertFalse(MazeSolver.hasPath(m));
    assertFalse(MazeSolver.hasPathWithStack(m));
    assertFalse(MazeSolver.hasPathWithQueue(m));
  }

  @Test
  public void findsTrivialPathInSingleOpenCell() {
    char[][] m = maze("S");
    assertTrue(MazeSolver.hasPath(m));
    assertTrue(MazeSolver.hasPathWithStack(m));
    assertTrue(MazeSolver.hasPathWithQueue(m));
  }

  @Test
  public void returnsFalseWhenExitIsWalledOff() {
    char[][] m = maze("S.#", "..#", "##E");
    assertFalse(MazeSolver.hasPath(m));
    assertFalse(MazeSolver.hasPathWithStack(m));
    assertFalse(MazeSolver.hasPathWithQueue(m));
  }

  @Test
  public void measuresShortestDistanceInThreeByThree() {
    char[][] m = maze("S..", ".#.", "..E");
    assertEquals(4, MazeSolver.shortestDistance(m));
  }

  @Test
  public void measuresDistanceAroundWalls() {
    char[][] m = maze("S..", "##.", "..E");
    assertEquals(4, MazeSolver.shortestDistance(m));
  }

  @Test
  public void returnsNegativeOneWhenUnreachable() {
    char[][] m = maze("S.#", "#.#", ".#E");
    assertEquals(-1, MazeSolver.shortestDistance(m));
  }

  @Test
  public void returnsZeroForSingleOpenCell() {
    char[][] m = maze("S");
    assertEquals(0, MazeSolver.shortestDistance(m));
  }

  @Test
  public void returnsNegativeOneWhenStartIsWall() {
    char[][] m = maze("#..", "...", "..E");
    assertEquals(-1, MazeSolver.shortestDistance(m));
  }

  private char[][] maze(String... rows) {
    char[][] grid = new char[rows.length][];
    for (int i = 0; i < rows.length; i++) {
      grid[i] = rows[i].toCharArray();
    }
    return grid;
  }
}
