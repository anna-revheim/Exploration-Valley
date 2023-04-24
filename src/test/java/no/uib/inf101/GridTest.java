package no.uib.inf101;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.ExploartionValley.grid.CellPosition;
import no.uib.inf101.sem2.ExploartionValley.grid.Grid;
import no.uib.inf101.sem2.ExploartionValley.grid.GridCell;
import no.uib.inf101.sem2.ExploartionValley.grid.IGrid;

/**
* Testing the class Grid
*/
public class GridTest {
  
  @Test
  void gridTestGetRowsAndCols() {
    IGrid<Integer> grid = new Grid<>(3, 2);
    assertEquals(3, grid.rows());
    assertEquals(2, grid.cols());
  }
  
  @Test
  void gridSanityTest() {
    String defaultValue = "x";
    IGrid<String> grid = new Grid<>(3, 2, defaultValue);
    
    assertEquals(3, grid.rows());
    assertEquals(2, grid.cols());
    
    assertEquals("x", grid.get(new CellPosition(0, 0)));
    assertEquals("x", grid.get(new CellPosition(2, 1)));
    
    grid.set(new CellPosition(1, 1), "y");
    
    assertEquals("y", grid.get(new CellPosition(1, 1)));
    assertEquals("x", grid.get(new CellPosition(0, 1)));
    assertEquals("x", grid.get(new CellPosition(1, 0)));
    assertEquals("x", grid.get(new CellPosition(2, 1)));
  }
  
  @Test
  void gridCanHoldNull() {
    String defaultValue = "x";
    IGrid<String> grid = new Grid<>(3, 2, defaultValue);
    
    assertEquals("x", grid.get(new CellPosition(0, 0)));
    assertEquals("x", grid.get(new CellPosition(2, 1)));
    
    grid.set(new CellPosition(1, 1), null);
    
    assertEquals(null, grid.get(new CellPosition(1, 1)));
    assertEquals("x", grid.get(new CellPosition(0, 1)));
    assertEquals("x", grid.get(new CellPosition(1, 0)));
    assertEquals("x", grid.get(new CellPosition(2, 1)));
  }
  
  @Test
  void gridNullsInDefaultConstructor() {
    IGrid<String> grid = new Grid<>(3, 2);
    
    assertEquals(null, grid.get(new CellPosition(0, 0)));
    assertEquals(null, grid.get(new CellPosition(2, 1)));
    
    grid.set(new CellPosition(1, 1), "y");
    
    assertEquals("y", grid.get(new CellPosition(1, 1)));
    assertEquals(null, grid.get(new CellPosition(0, 1)));
    assertEquals(null, grid.get(new CellPosition(1, 0)));
    assertEquals(null, grid.get(new CellPosition(2, 1)));
  }
  
  @Test
  void coordinateIsOnGridTest() {
    IGrid<Double> grid = new Grid<>(3, 2, 0.9);
    
    assertTrue(grid.positionIsOnGrid(new CellPosition(2, 1)));
    assertFalse(grid.positionIsOnGrid(new CellPosition(3, 1)));
    assertFalse(grid.positionIsOnGrid(new CellPosition(2, 2)));
    
    assertTrue(grid.positionIsOnGrid(new CellPosition(0, 0)));
    assertFalse(grid.positionIsOnGrid(new CellPosition(-1, 0)));
    assertFalse(grid.positionIsOnGrid(new CellPosition(0, -1)));
  }
  
  @Test
  void throwsExceptionWhenCoordinateOffGrid() {
    IGrid<String> grid = new Grid<>(3, 2, "x");
    
    try {
      @SuppressWarnings("unused")
      String x = grid.get(new CellPosition(3, 1));
      fail();
    } catch (IndexOutOfBoundsException e) {
      // Test passed
    }
  }
}

