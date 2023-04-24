package no.uib.inf101.sem2.ExploartionValley.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
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
    public ArrayList<Rectangle> itemBounds; //ArrayList of Rectangles for collisions
    public ArrayList<Rectangle> treeBounds; //ArrayList of Rectangles for collisions
    public ArrayList<BufferedImage> itemImages;
    Rectangle houseBound;


    /**
     * Creates a new item object with the given game view.
     * Initializes the tree and house images and adds them to their respective bounds to the lists.
     * @param view The current game view.
     */

    public item(gameView view) {
        this.view = view;
        itemImages = new ArrayList<BufferedImage>();
        getItemImage();
        
        treeBounds = new ArrayList<Rectangle>(); //Tree bounds list to add all the trees into
        treeBounds.add(new Rectangle(300, 600, 100, 100)); 
        treeBounds.add(new Rectangle(200, 100, 100, 100));
        treeBounds.add(new Rectangle(1000, 500, 100, 100)); 
        treeBounds.add(new Rectangle(800, 100, 100, 100));
        houseBound = new Rectangle(500, 200, 200, 200); 

        itemBounds = new ArrayList<Rectangle>(); // One list for all of the items
        itemBounds.addAll(treeBounds); //Add the trees
        itemBounds.add(houseBound); // Add the houses
    }

    public void getItemImage() {
        try {
            tree = ImageIO.read(getClass().getResourceAsStream("/item/tree.png"));
            house = ImageIO.read(getClass().getResourceAsStream("/item/buildings/house.png"));
            stump =  ImageIO.read(getClass().getResourceAsStream("/item/stump.png"));
            
            itemImages.add(tree);
            itemImages.add(house);
            itemImages.add(stump);

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
            //g2.draw(itemBound);
        }
    }

}
