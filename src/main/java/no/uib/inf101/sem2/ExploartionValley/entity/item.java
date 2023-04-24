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
    private ArrayList<Rectangle> flowerBounds;
    public ArrayList<Rectangle> bushBounds;
    public Rectangle houseBound; //One house


    /**
     * Creates a new item object with the given game view.
     * Initializes the tree and house images and adds them to their respective bounds to the lists.
     * @param view The current game view.
     */

    public item(gameView view) {
        this.view = view;
        getItemImage();
        
        treeBounds = new ArrayList<Rectangle>(); //Tree bounds list to add all the trees into
        treeBounds.add(new Rectangle(300, 600, 100, 100)); 
        treeBounds.add(new Rectangle(200, 100, 100, 100));
        treeBounds.add(new Rectangle(1000, 500, 100, 100)); 
        treeBounds.add(new Rectangle(800, 100, 100, 100));

        bushBounds = new ArrayList<Rectangle>(); // Add bushes
        bushBounds.add(new Rectangle(800, 548, 40, 40));
        bushBounds.add(new Rectangle(486, 150, 40, 40));
        bushBounds.add(new Rectangle(100, 700, 40, 40));
        bushBounds.add(new Rectangle(395, 350, 40, 40));

        
        flowerBounds = new ArrayList<Rectangle>(); // Add flowers
        flowerBounds.add(new Rectangle(800, 200, 0, 0));
        flowerBounds.add(new Rectangle(658, 548, 0, 0));
        flowerBounds.add(new Rectangle(235, 398, 0, 0));
        flowerBounds.add(new Rectangle(900, 98, 0, 0));
        flowerBounds.add(new Rectangle(50, 50, 0, 0));
        flowerBounds.add(new Rectangle(1100 ,500, 0, 0));


        houseBound = new Rectangle(500, 200, 200, 200); 

        itemBounds = new ArrayList<Rectangle>(); // One list for all of the items
        itemBounds.addAll(treeBounds); //Add the trees
        itemBounds.addAll(bushBounds);
        itemBounds.addAll(flowerBounds);
        itemBounds.add(houseBound); // Add the houses
    }

    public void getItemImage() {
        try {
            tree = ImageIO.read(getClass().getResourceAsStream("/item/tree.png"));
            house = ImageIO.read(getClass().getResourceAsStream("/item/buildings/house.png"));
            stump =  ImageIO.read(getClass().getResourceAsStream("/item/stump.png"));
            bush =  ImageIO.read(getClass().getResourceAsStream("/item/bush.png"));
            flower = ImageIO.read(getClass().getResourceAsStream("/item/blueFlower.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Draws the trees and house onto the screen.
     * @param g2 The graphics2D used for drawing.
     */
    public void drawItem(Graphics2D g2) {
        for (Rectangle itemBound : itemBounds) {
            if (treeBounds.contains(itemBound)) {
                g2.drawImage(tree, itemBound.x, itemBound.y, itemBound.width, itemBound.height, null);
            } else if (bushBounds.contains(itemBound)) {
                g2.drawImage(bush, itemBound.x, itemBound.y, itemBound.width, itemBound.height, null);
            } else if (flowerBounds.contains(itemBound)) {
                g2.drawImage(flower, itemBound.x, itemBound.y, 16, 16, null);
            } else if (itemBound.equals(houseBound)) {
                g2.drawImage(house, itemBound.x, itemBound.y, itemBound.width, itemBound.height, null);
            }
        //g2.draw(itemBounds);
        }
    }
    

}
