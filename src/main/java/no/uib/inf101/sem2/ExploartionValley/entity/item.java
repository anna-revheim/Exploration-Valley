package no.uib.inf101.sem2.ExploartionValley.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import no.uib.inf101.sem2.ExploartionValley.view.gameView;

/**
 * Represents an itemclass in the game and handles interactions with other entities.
 * This class is a subclass of the entity class. 
*/

public class item extends entity {
    gameView view;
    public boolean collision = false;
    ArrayList<Rectangle> itemBounds; //ArrayList of Rectangles for collisions
    ArrayList<Rectangle> treeBounds; //ArrayList of Rectangles for collisions
    Rectangle houseBound;


     /**
     * Creates a new item object with the given game view.
     * Initializes the tree and house images and adds them to their respective bounds to the lists.
     * @param view The current game view.
     */

    public item(gameView view) {
        this.view = view;
        getItemImage();
        treeBounds = new ArrayList<Rectangle>(); //Tree bounds list to add all the trees into
        treeBounds.add(new Rectangle(300, 500, 100, 100)); // a tree
        treeBounds.add(new Rectangle(200, 100, 100, 100)); // Second tree
        houseBound = new Rectangle(500, 200, 200, 200); // A house
        itemBounds = new ArrayList<Rectangle>(); // One list for all of the items
        itemBounds.addAll(treeBounds); //Add the trees
        itemBounds.add(houseBound); // Add the houses
    }

    public void getItemImage() {
        try {
            tree = ImageIO.read(getClass().getResourceAsStream("/item/tree.png"));
            house = ImageIO.read(getClass().getResourceAsStream("/item/buildings/house.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Draws the trees and house onto the screen.
     * @param g2 The graphics2D used for drawing.
     */
    public void drawItem(Graphics2D g2) {
        for (Rectangle itemBound : treeBounds) {
            g2.drawImage(tree, itemBound.x, itemBound.y, itemBound.width, itemBound.height, null);
            g2.drawImage(house, 500, 200, 200, 200, null);
            // g2.draw(itemBound);
        }
    }

    /*
     * Checks if there is a collision between the player and any of the items (trees or houses).
     * @param playerBounds the bounds of the player
     * @return true if there is a collision between the player and any of the items, false otherwise
     */
    
    public boolean checkCollision(Rectangle playerBounds) {
        for (Rectangle itemBound : itemBounds) {
            boolean collision = playerBounds.intersects(itemBound);
            while (collision) {
                System.out.println("Collision detected! with an item");
                return true; 
            }
        }
        return false; 
    }
}
