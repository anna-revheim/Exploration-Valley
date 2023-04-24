package no.uib.inf101.sem2.ExploartionValley.entity;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import no.uib.inf101.sem2.ExploartionValley.view.gameView;

/*
 * Entity class is the parentclass to all things player, enemy and item related. 
 * It gives all classes containing entity a x, y position on the worldmap and its own speed. 
 */
public class entity {
    // this class is the parentclass to all things player, monster, npc related.
    // all beings need a position, and a speed.
    public int worldX, worldY;
    public int speed;
    //Sprite for player.
    public BufferedImage up1, up2, up3, up4, up5, down1, down2, down3, down4, down5, left1, left2, left3, left4, left5, right1, right2, right3, right4, right5,
                        downatk1, downatk2, downatk3, upatk1, upatk2, upatk3, upatk4, latk1, latk2, latk3, latk4, ratk1, ratk2, ratk3, ratk4,
                        lay1, lay2, lay3;
    public int direction;
    public BufferedImage tree, house, stump;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int hitNumber = 3;
    public Rectangle collisionArea;
    public boolean collisionOn = false;
    public gameView view;


    
/**
 * Checks for collision between the player and the game bounds.
 *
 * @param playerBounds the bounding rectangle of the player
 * @param gameBounds the list of bounding rectangles of the game objects
 * @return true if a collision is detected, false otherwise
 */
    public boolean checkCollision(Rectangle playerBounds, ArrayList<Rectangle> gameBounds) {
        for (Rectangle gameBound : gameBounds) {
            boolean collision = playerBounds.intersects(gameBound);
            if (collision) {
                return true; // return true on the first collision
            }
        }
        return false; // return false if no collision is detected
    }
    
    
}
