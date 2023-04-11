package no.uib.inf101.sem2.ExploartionValley.entity;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import no.uib.inf101.sem2.ExploartionValley.view.gameView;

public class item extends entity {

    gameView view;

    public item(gameView view) {
        this.view = view;
        getItemImage();
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
        g2.drawImage(tree, 150, 20, 100, 100, null);
        g2.drawImage(tree, 280, 20, 100, 100, null);
        g2.drawImage(tree, 410, 20, 100, 100, null);
        g2.drawImage(tree, 540, 20, 100, 100, null);
        g2.drawImage(tree, 670, 20, 100, 100, null);
        g2.drawImage(tree, 800, 20, 100, 100, null);
        g2.drawImage(tree, 930, 20, 100, 100, null);
        g2.drawImage(tree, 1060, 20, 100, 100, null);
    }
}
