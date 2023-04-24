package no.uib.inf101.sem2.ExploartionValley.entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class entity {
    // this class is the parentclass to all things player, monster, npc related.
    // all beings need a position, and a speed.
    public int worldX, worldY;
    public int speed;

    //player 
    public BufferedImage up1, up2, up3, up4, up5, down1, down2, down3, down4, down5, left1, left2, left3, left4, left5, right1, right2, right3, right4, right5,
    downatk1, downatk2, downatk3, upatk1, upatk2, upatk3, upatk4, latk1, latk2, latk3, latk4, ratk1, ratk2, ratk3, ratk4;
    public int direction;
    public BufferedImage tree, house;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int hitNumber = 3;
    public Rectangle collisionArea;
    public boolean collisionOn = false;

    public boolean checkCollision(Rectangle playerBounds, ArrayList<Rectangle> gameBounds) {
        for (Rectangle gameBound : gameBounds) {
            boolean collision = playerBounds.intersects(gameBound);
            if (collision) {
                System.out.println("Collision detected!");
                return true; // return true on the first collision
            }
        }
        return false; // return false if no collision is detected
    }
}
