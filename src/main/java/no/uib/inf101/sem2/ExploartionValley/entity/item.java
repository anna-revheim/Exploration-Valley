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

    public void drawItem(Graphics2D g2) {
        for (Rectangle itemBound : treeBounds) {
            g2.drawImage(tree, itemBound.x, itemBound.y, itemBound.width, itemBound.height, null);
            g2.drawImage(house, 500, 200, 200, 200, null);
            // g2.draw(itemBound);
        }
    }
}
