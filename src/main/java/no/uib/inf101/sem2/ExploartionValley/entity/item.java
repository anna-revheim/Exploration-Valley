package no.uib.inf101.sem2.ExploartionValley.entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import no.uib.inf101.sem2.ExploartionValley.view.gameView;

public class item extends entity {
    gameView view;
    public boolean collision = false;
    public ArrayList<Rectangle> itemBounds;
    public ArrayList<Rectangle> treeBounds;
    public ArrayList<Image> itemImages;
    Rectangle houseBound;

    public item(gameView view) {
        this.view = view;
        itemImages = new ArrayList<Image>();
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

    public void drawItem(Graphics2D g2) {
        for (Rectangle itemBound : treeBounds) {
            g2.drawImage(tree, itemBound.x, itemBound.y, itemBound.width, itemBound.height, null);
            g2.drawImage(house, 500, 200, 200, 200, null);
            //g2.draw(itemBound);
        }
    }

    public boolean checkCollision(Rectangle playerBounds) {
        for (Rectangle itemBound : itemBounds) {
            boolean collision = playerBounds.intersects(itemBound);
            while (collision) {
                System.out.println("Collision detected! with an item");
                return true; // return true on the first collision
            }
        }
        return false; // return false if no collision is detected
    }
    
    public void removeItem(int index) {
        if (index >= 0 && index < itemBounds.size()) {
            Rectangle itemToRemove = itemBounds.get(index);
            itemBounds.remove(index);
            if (itemToRemove.equals(treeBounds.get(index))) {
                itemImages.set(index, stump);
                itemBounds.add(index, new Rectangle(itemToRemove.x, itemToRemove.y, 16, 16));
            } else {
                itemImages.remove(index); // Remove the corresponding image from itemImages
            }
        }
        view.repaint();
    }
    
    
}
