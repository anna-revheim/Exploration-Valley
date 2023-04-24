package no.uib.inf101;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.ExploartionValley.grid.CellPosition;
import no.uib.inf101.sem2.ExploartionValley.grid.GridCell;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
* Testing the class GridCell
*/
public class GridCellTest {
    
    @Test
    void sanityTest() {
        String item = "Test";
        CellPosition pos = new CellPosition(4, 2);
        GridCell<String> gridCell = new GridCell<>(pos, item);
        
        assertEquals(pos, gridCell.pos());
        assertEquals(item, gridCell.value());
    }
    
    @Test
    void gridCellEqualityAndHashCodeTest() {
        String item = "Test";
        CellPosition pos = new CellPosition(4, 2);
        GridCell<String> gridCell = new GridCell<>(pos, item);
        
        String item2 = "Test";
        CellPosition pos2 = new CellPosition(4, 2);
        GridCell<String> gridCell2 = new GridCell<>(pos2, item2);
        
        assertTrue(gridCell2.equals(gridCell));
        assertTrue(gridCell.equals(gridCell2));
        assertTrue(Objects.equals(gridCell, gridCell2));
        assertTrue(gridCell.hashCode() == gridCell2.hashCode());
    }
    
    @Test
    void gridCellInequalityTest() {
        String item = "Test";
        CellPosition pos = new CellPosition(4, 2);
        GridCell<String> gridCell = new GridCell<>(pos, item);
        
        String item2 = "Test2";
        CellPosition pos2 = new CellPosition(2, 4);
        
        GridCell<String> gridCell2 = new GridCell<>(pos2, item);
        GridCell<String> gridCell3 = new GridCell<>(pos, item2);
        
        assertFalse(gridCell2.equals(gridCell));
        assertFalse(gridCell.equals(gridCell2));
        assertFalse(gridCell.equals(gridCell3));
        assertFalse(gridCell2.equals(gridCell3));
        assertFalse(Objects.equals(gridCell, gridCell2));
        assertFalse(Objects.equals(gridCell, gridCell3));
        assertFalse(Objects.equals(gridCell2, gridCell3));
    }
}

