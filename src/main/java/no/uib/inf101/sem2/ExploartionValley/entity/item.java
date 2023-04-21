package no.uib.inf101.sem2.ExploartionValley.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import no.uib.inf101.sem2.ExploartionValley.view.gameView;

public class item extends entity {

    gameView view;
    Rectangle treeBounds;

    public item(gameView view) {
        this.view = view;
        getItemImage();
        treeBounds = new Rectangle(20, 20, 100, 100); 
    }



    public void getItemImage() {
        try {
            tree = ImageIO.read(getClass().getResourceAsStream("/item/tree.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawItem(Graphics2D g2) {
        g2.drawImage(tree, 20, 20, 100, 100, null);
        // draw the tree image at its position
    }

    public boolean checkCollision(Rectangle playerBounds) {
        // check if the player's rectangle intersects with the tree's rectangle
        boolean collision = playerBounds.intersects(treeBounds);
        if (collision) {
            System.out.println("Collision detected!"); // optional
        }
        return collision;
    }
    
}
