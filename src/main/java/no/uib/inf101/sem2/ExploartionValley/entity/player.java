package no.uib.inf101.sem2.ExploartionValley.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import no.uib.inf101.sem2.ExploartionValley.controller.gameController;
import no.uib.inf101.sem2.ExploartionValley.view.gameView;

/**
 * The player class represents the player in a game. It extends the entity
 * class.
 * It contains information about the player's position, speed, direction, and
 * interaction range.
 */

public class player extends entity {
    gameView view; // gp
    gameController controller; // keyh
    String direction = "down";
    public boolean isMoving;
    private boolean hasCollided = false;
    public Rectangle playerBounds;
    public Rectangle interactRange;
    private Rectangle hitBox;

    // Where we place the player
    public final int screenX;
    public final int screenY;

    /**
     * Constructor for the `player` class.
     * 
     * @param view       The `gameView` instance for displaying the game.
     * @param controller The `gameController` instance for controlling the game.
     */
    public player(gameView view, gameController controller) {
        this.view = view;
        this.controller = controller;
        setDefaultValues();
        getCharacterImage();
        screenX = this.view.w / 2 - 56;
        screenY = this.view.h / 2 - 60;
        hitBox = new Rectangle(-70, -70, 70, 70);
    }

    /*
     * Sets players default values. Not in constructor as we want to
     * be able to reset the players values midgame.
     */
    public void setDefaultValues() {
        this.hitNumber = 3;
        worldX = this.view.w / 2 - 56;
        worldY = this.view.h / 2 - 60;
        this.speed = 4;
        this.isMoving = false;
        playerBounds = new Rectangle(worldX, worldY, 32, 32);
        playerBounds = new Rectangle(worldX, worldY, 32, 32);
        
    }

    private void getCharacterImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/up/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/up/up2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/up/up3.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/player/up/up4.png"));
            up5 = ImageIO.read(getClass().getResourceAsStream("/player/up/up5.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/down/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/down/down2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/down/down3.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/player/down/down4.png"));
            down5 = ImageIO.read(getClass().getResourceAsStream("/player/down/down5.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/left/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left/left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/left/left3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/player/left/left4.png"));
            left5 = ImageIO.read(getClass().getResourceAsStream("/player/left/left5.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/right/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/right/right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/right/right3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/player/right/right4.png"));
            right5 = ImageIO.read(getClass().getResourceAsStream("/player/right/right5.png"));

            // Attack
            downatk1 = ImageIO.read(getClass().getResourceAsStream("/player/interact/downatk/downatk1.png"));
            downatk2 = ImageIO.read(getClass().getResourceAsStream("/player/interact/downatk/downatk2.png"));
            downatk3 = ImageIO.read(getClass().getResourceAsStream("/player/interact/downatk/downatk3.png"));
            upatk1 = ImageIO.read(getClass().getResourceAsStream("/player/interact/upatk/upatk1.png"));
            upatk2 = ImageIO.read(getClass().getResourceAsStream("/player/interact/upatk/upatk2.png"));
            upatk3 = ImageIO.read(getClass().getResourceAsStream("/player/interact/upatk/upatk3.png"));
            upatk4 = ImageIO.read(getClass().getResourceAsStream("/player/interact/upatk/upatk4.png"));
            latk1 = ImageIO.read(getClass().getResourceAsStream("/player/interact/leftatk/latk1.png"));
            latk2 = ImageIO.read(getClass().getResourceAsStream("/player/interact/leftatk/latk2.png"));
            latk3 = ImageIO.read(getClass().getResourceAsStream("/player/interact/leftatk/latk3.png"));
            latk4 = ImageIO.read(getClass().getResourceAsStream("/player/interact/leftatk/latk4.png"));
            ratk1 = ImageIO.read(getClass().getResourceAsStream("/player/interact/rightatk/ratk1.png"));
            ratk2 = ImageIO.read(getClass().getResourceAsStream("/player/interact/rightatk/ratk2.png"));
            ratk3 = ImageIO.read(getClass().getResourceAsStream("/player/interact/rightatk/ratk3.png"));
            ratk4 = ImageIO.read(getClass().getResourceAsStream("/player/interact/rightatk/ratk4.png"));
            latk1 = ImageIO.read(getClass().getResourceAsStream("/player/interact/leftatk/latk1.png"));
            latk2 = ImageIO.read(getClass().getResourceAsStream("/player/interact/leftatk/latk2.png"));
            latk3 = ImageIO.read(getClass().getResourceAsStream("/player/interact/leftatk/latk3.png"));
            latk4 = ImageIO.read(getClass().getResourceAsStream("/player/interact/leftatk/latk4.png"));
            ratk1 = ImageIO.read(getClass().getResourceAsStream("/player/interact/rightatk/ratk1.png"));
            ratk2 = ImageIO.read(getClass().getResourceAsStream("/player/interact/rightatk/ratk2.png"));
            ratk3 = ImageIO.read(getClass().getResourceAsStream("/player/interact/rightatk/ratk3.png"));
            ratk4 = ImageIO.read(getClass().getResourceAsStream("/player/interact/rightatk/ratk4.png"));

            // laying
            lay1 = ImageIO.read(getClass().getResourceAsStream("/player/laying/lay1.png"));
            lay2 = ImageIO.read(getClass().getResourceAsStream("/player/laying/lay2.png"));
            lay3 = ImageIO.read(getClass().getResourceAsStream("/player/laying/lay3.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void interact() {
        hitBox.setLocation(worldX + 20, worldY + 45);
        if (view.item.checkCollision(hitBox) || view.bat.checkCollision(hitBox)) {
            System.out.println("Attack");
            Iterator<Rectangle> iterator = view.item.itemBounds.iterator();
            while (iterator.hasNext()) {
                Rectangle item = iterator.next();
                if (item.intersects(hitBox)) {
                    int index = view.item.itemBounds.indexOf(item);
                    view.item.removeItem(index); // Call removeItem() with the index
                    break;
                }
            }
        }
    }

    /**
     * Updates the player's position and checks for collisions.
     * This code is quite long due to its complexity. First checks for collision, if
     * no move.
     * After it checks for directions and inputs. Also keeps track for spritecounter
     * that is
     * used for drawing.
     */
    
    public void update() {
        playerBounds.setLocation(worldX + 36, worldY + 60);
        //To do collisions. First check when collision happens, then when not.
        // check collision with item

        if (view.item.checkCollision(playerBounds)) { // 
            hasCollided = true;

            // move player away from item
            if (direction == "up" ) {
                worldY +=4;
            } else if (direction == "down") {
                worldY -= 4;
            } else if (direction == "left") {
                worldX  += 4;
            } else if (direction == "right") {
                worldX  -= 4;
            }
        }

        else if (view.bat.checkCollision(playerBounds)){
            hasCollided = true;
            if (direction == "up" ) {
                worldY +=8;
            } else if (direction == "down") {
                worldY -= 8;
            } else if (direction == "left") {
                worldX  += 8;
            } else if (direction == "right") {
                worldX  -= 8;
            }
            hitNumber --;
            if (hitNumber > 0) {
                
                System.out.println("Bat is attacking");
            }
            while (hitNumber == 0) {
                try {
                    Thread.sleep(1000); // add a delay of 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                direction = "lay";
                isMoving = false;
                setDefaultValues();
                System.out.println("We got respawned");
                }
            }
        else {
            // player did not collide with item, so continue moving
            if (controller.upPressed == true) {
                direction = "up";
                if ((this.worldY > -40)) {
                    worldY -= speed;
                    isMoving = true;}
            } else if (controller.downPressed) {
                direction = "down";
                if (this.worldY < this.view.h - 100) {
                    worldY += speed;
                    isMoving = true;
                }
            } else if (controller.leftPressed) {
                direction = "left";
                if (this.worldX > -24) {
                    worldX -= speed;
                    isMoving = true;
                }
            } else if (controller.rightPressed) {
                direction = "right";
                if (this.worldX < this.view.w - 80) {
                    worldX += speed;
                    isMoving = true;
                }
            } else if(controller.actionPressed){
                if (direction == "up") {
                    direction = "up_atk";
                    interact();
                }
                if (direction == "down") {
                    direction = "down_atk";
                    interact();
                }
                if (direction == "left") {
                    direction = "left_atk";
                    interact();
                }
                if (direction == "right") {
                    direction = "right_atk";
                    interact();
                    }
            }
            else {
                isMoving = false;
                hitBox.setLocation(-70, -70); //Resets the hitBox
            }
            // if the player has collided with the item and is not colliding anymore, allow
            // movement
            if (hasCollided && !view.item.checkCollision(playerBounds)) {
                hasCollided = false;
                isMoving = true;
                //System.out.println("X: " + this.worldX + "\tY: " + this.worldY);
            }
        }
        // If the character is moving start counting. Count is for character movement.
        if (isMoving == true || controller.actionPressed) {
            spriteCounter++;
            if (spriteCounter > 6) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 4;
                } else if (spriteNum == 4) {
                    spriteNum = 5;
                } else if (spriteNum == 5) {
                    spriteNum = 6;
                } else if (spriteNum == 6) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } 
        else if (controller.actionPressed = false) {
            spriteNum = 6;
            spriteCounter = 0;
        }
        else {
            spriteNum = 3;
            spriteCounter = 0;
        }
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                } else if (spriteNum == 2) {
                    image = up2;
                } else if (spriteNum == 3 || spriteNum == 6) {
                    image = up3;
                } else if (spriteNum == 4) {
                    image = up4;
                } else if (spriteNum == 5) {
                    image = up5;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                } else if (spriteNum == 2) {
                    image = down2;
                } else if (spriteNum == 3 || spriteNum == 6) {
                    image = down3;
                } else if (spriteNum == 4) {
                    image = down4;
                } else if (spriteNum == 5) {
                    image = down5;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                } else if (spriteNum == 2) {
                    image = left2;
                } else if (spriteNum == 3 || spriteNum == 6) {
                    image = left3;
                } else if (spriteNum == 4) {
                    image = left4;
                } else if (spriteNum == 5) {
                    image = left5;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                } else if (spriteNum == 2) {
                } else if (spriteNum == 2) {
                    image = right2;
                } else if (spriteNum == 3 || spriteNum == 6) {
                    image = right3;
                } else if (spriteNum == 4) {
                } else if (spriteNum == 4) {
                    image = right4;
                } else if (spriteNum == 5) {
                    image = right5;
                }
                break;
            // Interaction for attack

            case "up_atk":
                if (spriteNum == 1) {
                    image = upatk1;
                } else if (spriteNum == 2) {
                    image = upatk2;
                } else if (spriteNum == 3) {
                    image = upatk3;
                } else if (spriteNum == 4) {
                    image = upatk4;
                } else if (spriteNum == 5 || spriteNum == 6) {
                    image = up1;
                }
                break;
            case "down_atk":
                if (spriteNum == 1) {
                    image = downatk1;
                } else if (spriteNum == 2) {
                    image = downatk2;
                } else if (spriteNum == 3) {
                    image = downatk3;
                } else if (spriteNum == 4) {
                    image = downatk1;
                } else if (spriteNum == 5 || spriteNum == 6) {
                    image = down1;
                }
                break;
            case "left_atk":
                if (spriteNum == 1) {
                    image = latk1;
                } else if (spriteNum == 2) {
                    image = latk2;
                } else if (spriteNum == 3) {
                    image = latk3;
                } else if (spriteNum == 4) {
                    image = latk4;
                } else if (spriteNum == 5 || spriteNum == 6) {
                    image = left1;
                }
                break;
            case "right_atk":
                if (spriteNum == 1) {
                    image = ratk1;
                } else if (spriteNum == 2) {
                    image = ratk2;
                } else if (spriteNum == 3) {
                    image = ratk3;
                } else if (spriteNum == 4) {
                    image = ratk4;
                } else if (spriteNum == 5 || spriteNum == 6) {
                    image = right1;
                }
                break;

            case "lay":
                if (spriteNum == 1) {
                    image = lay1;
                } else if (spriteNum == 2) {
                    image = lay2;
                } else if (spriteNum == 3) {
                    image = lay3;
                }
                break;
        }

        g2.drawImage(image, worldX, worldY, 100, 100, null);

        // g2.drawImage(image, screenX, screenY, 100, 100, null);
        g2.drawImage(image, worldX, worldY, 100, 100, null);
        g2.draw(playerBounds);
        g2.draw(hitBox);
    }
}