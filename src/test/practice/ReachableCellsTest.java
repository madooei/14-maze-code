package practice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for ReachableCells.reachableCount. Each test builds a small maze from
 * rows of text and checks how many open cells the flood reaches from the
 * top-left cell. The letter 'S' marks the start as an ordinary open cell; '.'
 * marks the other open cells and '#' marks walls.
 */
public class ReachableCellsTest {

  // Builds a maze grid from one string per row.
  private char[][] maze(String... rows) {
    char[][] grid = new char[rows.length][];
    for (int i = 0; i < rows.length; i++) {
      grid[i] = rows[i].toCharArray();
    }
    return grid;
  }

  @Test
  public void countsAllReachableOpenCells() {
    char[][] grid = maze(
        "S.#",
        "..#",
        "#..");
    assertEquals(6, ReachableCells.reachableCount(grid));
  }

  @Test
  public void stopsAtWallsThatSealOffRegions() {
    char[][] grid = maze(
        "S#.",
        ".#.",
        ".#.");
    assertEquals(3, ReachableCells.reachableCount(grid));
  }

  @Test
  public void returnsZeroWhenStartIsWall() {
    char[][] grid = maze(
        "#..",
        "...",
        "...");
    assertEquals(0, ReachableCells.reachableCount(grid));
  }

  @Test
  public void countsSingleOpenCell() {
    char[][] grid = maze("S");
    assertEquals(1, ReachableCells.reachableCount(grid));
  }
}
