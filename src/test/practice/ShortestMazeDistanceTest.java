package practice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Tests for ShortestMazeDistance. Each test builds a small maze from rows of
 * text: 'S' marks the start as an ordinary open cell, 'E' marks the exit,
 * '.' marks the other open cells, and '#' marks walls. shortestDistance is
 * checked against the fewest number of moves; shortestPath is checked against
 * the route length (one more than the distance) and emptiness, since Position
 * is private to the class under test.
 */
public class ShortestMazeDistanceTest {

  // Builds a maze grid from one string per row.
  private char[][] maze(String... rows) {
    char[][] grid = new char[rows.length][];
    for (int i = 0; i < rows.length; i++) {
      grid[i] = rows[i].toCharArray();
    }
    return grid;
  }

  @Test
  public void findsFewestMovesAroundAWallBand() {
    char[][] grid = maze(
        "S..",
        "##.",
        "..E");
    assertEquals(4, ShortestMazeDistance.shortestDistance(grid));
  }

  @Test
  public void findsFewestMovesThroughACenterWall() {
    char[][] grid = maze(
        "S..",
        ".#.",
        "..E");
    assertEquals(4, ShortestMazeDistance.shortestDistance(grid));
  }

  @Test
  public void returnsMinusOneWhenExitIsWalledOff() {
    char[][] grid = maze(
        "S.#",
        "#.#",
        ".#E");
    assertEquals(-1, ShortestMazeDistance.shortestDistance(grid));
  }

  @Test
  public void returnsZeroWhenStartIsAlsoTheExit() {
    char[][] grid = maze("S");
    assertEquals(0, ShortestMazeDistance.shortestDistance(grid));
  }

  @Test
  public void returnsMinusOneWhenStartIsWall() {
    char[][] grid = maze(
        "#..",
        "...",
        "..E");
    assertEquals(-1, ShortestMazeDistance.shortestDistance(grid));
  }

  @Test
  public void pathLengthIsOneMoreThanTheDistance() {
    char[][] grid = maze(
        "S..",
        "##.",
        "..E");
    List<?> path = ShortestMazeDistance.shortestPath(grid);
    assertEquals(5, path.size());
  }

  @Test
  public void pathIsJustTheStartWhenStartIsAlsoTheExit() {
    char[][] grid = maze("S");
    List<?> path = ShortestMazeDistance.shortestPath(grid);
    assertEquals(1, path.size());
  }

  @Test
  public void pathIsEmptyWhenExitIsUnreachable() {
    char[][] grid = maze(
        "S.#",
        "#.#",
        ".#E");
    List<?> path = ShortestMazeDistance.shortestPath(grid);
    assertTrue(path.isEmpty());
  }

  @Test
  public void pathIsEmptyWhenStartIsWall() {
    char[][] grid = maze(
        "#..",
        "...",
        "..E");
    List<?> path = ShortestMazeDistance.shortestPath(grid);
    assertTrue(path.isEmpty());
  }
}
