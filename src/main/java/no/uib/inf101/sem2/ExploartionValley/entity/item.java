package no.uib.inf101.sem2.ExploartionValley.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import no.uib.inf101.sem2.ExploartionValley.view.gameView;

public class item extends entity {
    gameView view;
    public boolean collision = false;
    ArrayList<Rectangle> itemBounds;
    ArrayList<Rectangle> treeBounds;
    Rectangle houseBound;

    public item(gameView view) {
        this.view = view;
        getItemImage();
        treeBounds = new ArrayList<Rectangle>();
        treeBounds.add(new Rectangle(300, 500, 100, 100));
        treeBounds.add(new Rectangle(20, 20, 100, 100));
        houseBound = new Rectangle(500, 200, 200, 200);
        itemBounds = new ArrayList<Rectangle>();
        itemBounds.addAll(treeBounds);
        itemBounds.add(houseBound);
    }

    public void getItemImage() {
        try {
            tree = ImageIO.read(getClass().getResourceAsStream("/item/tree.png"));
            house = ImageIO.read(getClass().getResourceAsStream("/item/buildings/house.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawItem(Graphics2D g2) {
        for (Rectangle itemBound : treeBounds) {
            g2.drawImage(tree, itemBound.x, itemBound.y, itemBound.width, itemBound.height, null);
            g2.drawImage(house, 500, 200, 200, 200, null);
            // g2.draw(itemBound);
        }
    }

    public boolean checkCollision(Rectangle playerBounds) {
        for (Rectangle itemBound : itemBounds) {
            boolean collision = playerBounds.intersects(itemBound);
            while (collision) {
                System.out.println("Collision detected!");
                return true; // return true on the first collision
            }
        }
        return false; // return false if no collision is detected
    }
}
