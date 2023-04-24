package no.uib.inf101;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.ExploartionValley.grid.CellPosition;

/**
* Testing the class CellPosition
*/
public class CellPositionTest {

    @Test
    void sanityTest() {
        CellPosition cp = new CellPosition(4, 3);
        assertEquals(4, cp.row());
        assertEquals(3, cp.col());
    }

    @Test
    void coordinateEqualityTest() {
    CellPosition a = new CellPosition(2, 3);
    CellPosition b = new CellPosition(2, 3);
    
    assertFalse(a == b);
    assertTrue(a.equals(b));
    assertTrue(b.equals(a));
    assertTrue(Objects.equals(a, b));
    }

    @Test
    void coordinateInequalityTest() {
        CellPosition a = new CellPosition(2, 3);
        CellPosition b = new CellPosition(3, 2);
    
        assertFalse(a == b);
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
        assertFalse(Objects.equals(a, b));
    }

    @Test
    void coordinateHashcodeTest() {
        CellPosition a = new CellPosition(2, 3);
        CellPosition b = new CellPosition(2, 3);
        assertTrue(a.hashCode() == b.hashCode());
        
        CellPosition c = new CellPosition(100, 100);
        CellPosition d = new CellPosition(100, 100);
        assertTrue(c.hashCode() == d.hashCode());
    }
}

