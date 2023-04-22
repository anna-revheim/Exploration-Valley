package no.uib.inf101.sem2.ExploartionValley.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class npc extends entity{
    private int x, y; // NPC's current position
    private int speed; // NPC's movement speed
    private int moveTimer; // Timer for NPC's movement
    private Random rand; // Random number generator for NPC's movement
    private Rectangle npcRect; // Rectangle for NPC's collision detection
    private BufferedImage sprite; // NPC's sprite image
    
    public npc(int startX, int startY, int npcSpeed, int npcWidth, int npcHeight, BufferedImage npcSprite) {
        x = startX;
        y = startY;
        speed = npcSpeed;
        moveTimer = 0;
        rand = new Random();
        npcRect = new Rectangle(x, y, npcWidth, npcHeight);
        sprite = npcSprite;
    }
    
    public void update(Rectangle playerRect) {
        // Decrease move timer and move NPC if timer reaches 0
        moveTimer--;
        if (moveTimer <= 0) {
            moveTimer = rand.nextInt(5) + 2; // Wait 2-6 seconds before moving again
            int direction = rand.nextInt(4); // Randomly choose a direction
            switch (direction) {
                case 0: // Move up
                    y -= speed;
                    break;
                case 1: // Move down
                    y += speed;
                    break;
                case 2: // Move left
                    x -= speed;
                    break;
                case 3: // Move right
                    x += speed;
                    break;
            }
        }
        // Update NPC's collision rectangle
        npcRect.setLocation(x, y);
        
        // Check for collision with player rectangle
        if (npcRect.intersects(playerRect)) {
            // Move NPC back to previous position to avoid collision
            x = npcRect.x;
            y = npcRect.y;
        }
    }
    
    private void getCharacterImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/up/up1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(sprite, x, y, null);
    }
    
    // Getters and setters for NPC's position
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setX(int newX) {
        x = newX;
    }
    
    public void setY(int newY) {
        y = newY;
    }
}        



//g2.drawImage(image, screenX, screenY, 100, 100, null);
//        g2.drawImage(image, x, y, 100, 100, null);
  //      g2.draw(playerBounds);
    //}

