package no.uib.inf101;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Rectangle;
import java.util.ArrayList;

public class CollisionTest {


    @Test
    public void testCheckCollision() {
        Rectangle player = new Rectangle(5, 5, 10, 10);

        Rectangle bound1 = new Rectangle(0, 0, 20, 20);
        Rectangle bound2 = new Rectangle(10, 10, 20, 20);
        Rectangle bound3 = new Rectangle(0, 10, 20, 20);
        ArrayList<Rectangle> gameBounds = new ArrayList<>();
        gameBounds.add(bound1);
        gameBounds.add(bound2);
        gameBounds.add(bound3);

        boolean collision = checkCollision(player, gameBounds);
        assertTrue(collision);

        Rectangle bound4 = new Rectangle(100, 100, 20, 20);
        Rectangle bound5 = new Rectangle(50, 0, 20, 20);
        ArrayList<Rectangle> gameBounds2 = new ArrayList<>();
        gameBounds2.add(bound4);
        gameBounds2.add(bound5);

        collision = checkCollision(player, gameBounds2);
        assertFalse(collision);
    }

    public boolean checkCollision(Rectangle playerBounds, ArrayList<Rectangle> gameBounds) {
        for (Rectangle gameBound : gameBounds) {
            boolean collision = playerBounds.intersects(gameBound);
            if (collision) {
                return true; // return true on the first collision
            }
        }
        return false; // return false if no collision is detected
    }
}
