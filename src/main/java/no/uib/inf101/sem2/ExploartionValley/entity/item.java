package no.uib.inf101.sem2.ExploartionValley.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import no.uib.inf101.sem2.ExploartionValley.view.gameView;

public class item extends entity {

    gameView view;

    ArrayList<Rectangle> treeBounds;

    public item(gameView view) {
        this.view = view;
        getItemImage();
        treeBounds = new ArrayList<Rectangle>();

        Rectangle tree1Bounds = new Rectangle(300, 500, 100, 100);
        tree1Bounds.translate(-16, -16);
        Rectangle tree2Bounds = new Rectangle(20, 20, 100, 100);
        treeBounds.add(tree1Bounds);
        treeBounds.add(tree2Bounds);
    }
    



    public void getItemImage() {
        try {
            tree = ImageIO.read(getClass().getResourceAsStream("/item/tree.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawItem(Graphics2D g2) {
        for (Rectangle treeBound : treeBounds) {
            g2.drawImage(tree, treeBound.x, treeBound.y,treeBound.width, treeBound.height, null);
            g2.draw(treeBound);
        }
    }

    public boolean checkCollision(Rectangle playerBounds) {
        for (Rectangle treeBound : treeBounds) {
            boolean collision = playerBounds.intersects(treeBound);
            if (collision) {
                System.out.println("Collision detected!");
                System.out.println("treeBound: " + treeBound.toString());
                return true; // return true on the first collision
            }
        }
        return false; // return false if no collision is detected
    }
    
}
