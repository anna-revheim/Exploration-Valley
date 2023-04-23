package no.uib.inf101.sem2.ExploartionValley.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

import no.uib.inf101.sem2.ExploartionValley.controller.gameController;
import no.uib.inf101.sem2.ExploartionValley.view.gameView;

public class npc extends entity {
    private int x, y, startX, startY; // NPC's current position
    private int speed; // NPC's movement speed
    private int moveTimer; // Timer for NPC's movement
    private Random rand; // Random number generator for NPC's movement
    private Rectangle npcRect; // Rectangle for NPC's collision detection
    BufferedImage[] batSprites = new BufferedImage[16]; // NPC's sprite image
    private int spriteCounter = 0;

    ArrayList<Rectangle> npcBounds;
    gameView view; // gp
    gameController controller; // keyh
    
    //Where we place the player

    public npc(gameView view) {
        this.view = view;
        rand = new Random();
        //startX = rand.nextInt(this.view.w);
        //startY = rand.nextInt(this.view.h);

        x = 300;
        y = 400;
        moveTimer = 0;
        
        speed = 4;
    
        npcBounds = new ArrayList<Rectangle>(); //List used for collision detection
        npcRect = new Rectangle(x, y, 40, 40);
        npcBounds.add(npcRect);
        getNPCimage();
        
        // Print out the random starting position for testing purposes
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

    public void update() {
        // Update NPC's collision rectangle
        npcRect.setLocation(x + 10, y + 16);
    
        moveTimer--;
        if (moveTimer <= 0) {
            direction = rand.nextInt(4); // Randomly choose a direction
            moveTimer = 60;  // Wait 2-6 seconds before moving again
        }
    
        int newX = x;
        int newY = y;
    
        switch (direction) {
            case 0: // Move up
                newY -= speed;
                break;
            case 1: // Move down
                newY += speed;
                break;
            case 2: // Move left
                newX -= speed;
                break;
            case 3: // Move right
                newX += speed;
                break;
        }
    
        if (newX >= -24 && newX <= view.w - 80 && newY >= -40 && newY <= view.h - 100) {
            x = newX;
            y = newY;
        }
    
        this.spriteCounter++;
        if (this.spriteCounter > 4) {
            this.spriteCounter = 0;
        }
    
        System.out.println("NPC position: (" + x + ", " + y + ")");
    }

    
    

    public void draw(Graphics2D g2d, int spriteCounter) {
        BufferedImage image = null;
        int count = spriteCounter;
        if (this.direction == 0) {// if the bat moves upwards
            image = batSprites[Math.max(7 - count, 0)];
        } else if (this.direction == 1) {// if the bat moves down
            image = batSprites[Math.max(11 - count, 0)];
        } else if (this.direction == 2) {// if the bat moves left
            image = batSprites[Math.max(15 - count, 0)];
        } else if (this.direction == 3) {// if the bat moves right
            image = batSprites[Math.max(3 - count, 0)];
        } else if (this.direction == -1) {
            this.direction = 0;
        } else {
            image = up1;
            System.out.println("FAKK feil i batsprite.");
        }
        for (Rectangle npcBound : npcBounds) {
            g2d.drawImage(image, npcBound.x, npcBound.y, npcBound.width, npcBound.height, null);
            g2d.draw(npcRect);
        }
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

    public boolean checkCollision(Rectangle playerBounds) {
        for (Rectangle npcBound : npcBounds) {
            boolean collision = playerBounds.intersects(npcBound);
            while (collision) {
                System.out.println("Collision detected! with an item");
                return true; // return true on the first collision
            }
        }
        return false; // return false if no collision is detected
    }
    
}
