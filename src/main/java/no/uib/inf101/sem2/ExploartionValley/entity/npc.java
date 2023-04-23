package no.uib.inf101.sem2.ExploartionValley.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class npc extends entity {
    private int x, y; // NPC's current position
    private int speed; // NPC's movement speed
    private int moveTimer; // Timer for NPC's movement
    private Random rand; // Random number generator for NPC's movement
    private Rectangle npcRect; // Rectangle for NPC's collision detection
    BufferedImage[] batSprites = new BufferedImage[16]; // NPC's sprite image
    private int spriteCounter = 0;

    public npc(int startX, int startY, int npcSpeed, int npcWidth, int npcHeight) {
        x = startX;
        y = startY;
        speed = npcSpeed;
        moveTimer = 0;
        rand = new Random();
        npcRect = new Rectangle(x, y, npcWidth, npcHeight);
        getNPCimage();
    }

    public void getNPCimage() {
        try {
            batSprites[0] = ImageIO.read(getClass().getResourceAsStream("/enemies/bat/batright/batright1.png"));
            batSprites[1] = ImageIO.read(getClass().getResourceAsStream("/enemies/bat/batright/batright2.png"));
            batSprites[2] = ImageIO.read(getClass().getResourceAsStream("/enemies/bat/batright/batright3.png"));
            batSprites[3] = ImageIO.read(getClass().getResourceAsStream("/enemies/bat/batright/batright4.png"));
            batSprites[4] = ImageIO.read(getClass().getResourceAsStream("/enemies/bat/batup/batup1.png"));
            batSprites[5] = ImageIO.read(getClass().getResourceAsStream("/enemies/bat/batup/batup2.png"));
            batSprites[6] = ImageIO.read(getClass().getResourceAsStream("/enemies/bat/batup/batup3.png"));
            batSprites[7] = ImageIO.read(getClass().getResourceAsStream("/enemies/bat/batup/batup4.png"));
            batSprites[8] = ImageIO.read(getClass().getResourceAsStream("/enemies/bat/batdown/batdown1.png"));
            batSprites[9] = ImageIO.read(getClass().getResourceAsStream("/enemies/bat/batdown/batdown2.png"));
            batSprites[10] = ImageIO.read(getClass().getResourceAsStream("/enemies/bat/batdown/batdown3.png"));
            batSprites[11] = ImageIO.read(getClass().getResourceAsStream("/enemies/bat/batdown/batdown4.png"));
            batSprites[12] = ImageIO.read(getClass().getResourceAsStream("/enemies/bat/batleft/batleft1.png"));
            batSprites[13] = ImageIO.read(getClass().getResourceAsStream("/enemies/bat/batleft/batleft2.png"));
            batSprites[14] = ImageIO.read(getClass().getResourceAsStream("/enemies/bat/batleft/batleft3.png"));
            batSprites[15] = ImageIO.read(getClass().getResourceAsStream("/enemies/bat/batleft/batleft4.png"));

        } catch (IOException e) {
            System.out.println("Feil funnet i getNPCimage.");
            e.printStackTrace();
        }
    }

    public void update(Rectangle playerRect) {
        // Decrease move timer and move NPC if timer reaches 0
        moveTimer--;
        if (moveTimer <= 0) {
            direction = rand.nextInt(4); // Randomly choose a direction
            moveTimer = rand.nextInt(20) + 30; // Wait 2-6 seconds before moving again
        }
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
            this.spriteCounter++;
            if (this.spriteCounter > 4) {
                this.spriteCounter = 0;
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

    public void draw(Graphics2D g2d, int spriteCounter) {
        BufferedImage image = null;
        int count = spriteCounter;
        if (this.direction == 0) {// if the bat moves upwards
            image = batSprites[7 - count];
        } else if (this.direction == 1) {// if the bat moves down
            image = batSprites[11 - count];
        } else if (this.direction == 2) {// if the bat moves left
            image = batSprites[15 - count];
        } else if (this.direction == 3) {// if the bat moves right
            image = batSprites[3 - count];
        } else {
            image = up1;
            System.out.println("FAKK feil i batsprite.");
        }

        g2d.drawImage(image, x, y, 40, 40, null);
    }

    // Getters and setters for NPC's position
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpriteCounter(){
        return this.spriteCounter;
    }

    public void setX(int newX) {
        x = newX;
    }

    public void setY(int newY) {
        y = newY;
    }
}

// g2.drawImage(image, screenX, screenY, 100, 100, null);
// g2.drawImage(image, x, y, 100, 100, null);
// g2.draw(playerBounds);
// }
