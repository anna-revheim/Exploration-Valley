package no.uib.inf101.sem2.ExploartionValley.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import no.uib.inf101.sem2.ExploartionValley.controller.gameController;
import no.uib.inf101.sem2.ExploartionValley.model.gamePlay;
import no.uib.inf101.sem2.ExploartionValley.view.gameView;

public class player extends entity {
    gameView view; // gp
    gameController controller; // keyh
    gamePlay gameplay;
    String direction = "down"; 
    public boolean isMoving;
    private boolean hasCollided = false;
    public Rectangle playerBounds;
    private Rectangle interactRange;

    //Where we place the player
    public final int screenX;
    public final int screenY;

    public player(gameView view, gameController controller) {
        this.view = view;
        this.controller = controller;
        setDefaultValues();
        getCharacterImage();
        collisionArea = new Rectangle();
        collisionArea.x = 0;
        collisionArea.y = 0;
        collisionArea.width = 30;
        collisionArea.height = 30;
        this.interactRange = new Rectangle(0, 0, 40, 40);

        screenX = this.view.w / 2 - 56;
        screenY = this.view.h / 2 - 60;
    }
    //er dinna nødvendig? kan bruke konstruktøren
    private void setDefaultValues() {
        worldX  = this.view.w / 2 - 56;
        worldY = this.view.h / 2 - 60;
        this.speed = 4;
        this.isMoving = false;
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
            downatk1 = ImageIO.read(getClass().getResourceAsStream("/player/interact/downatk/downatk1.png"));
            downatk2 = ImageIO.read(getClass().getResourceAsStream("/player/interact/downatk/downatk2.png"));
            downatk3 = ImageIO.read(getClass().getResourceAsStream("/player/interact/downatk/downatk3.png"));
            upatk1 = ImageIO.read(getClass().getResourceAsStream("/player/interact/upatk/upatk1.png"));
            upatk2 = ImageIO.read(getClass().getResourceAsStream("/player/interact/upatk/upatk2.png"));
            upatk3 = ImageIO.read(getClass().getResourceAsStream("/player/interact/upatk/upatk3.png"));
            upatk4 = ImageIO.read(getClass().getResourceAsStream("/player/interact/upatk/upatk4.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void interact(){
        playerBounds.setLocation(worldX + 36, worldY + 60);
        npc currentBat = new npc(100, 100, 3, 40, 40);

        if(controller.actionPressed){ // When pressed. (It will need to be held)
            // Bat hit logic
            if (currentBat != null && currentBat.checkCollision(playerBounds)) {
                currentBat.hit(); // Call the hit method in the Bat class
            }
        }
    }
    
    public void update() {
        item currentItem = new item(view); // create an instance of item

        playerBounds.setLocation(worldX + 36, worldY + 60);
        //To do collisions. First check when collision happens, then when not.
        // check collision with item

        if (currentItem.checkCollision(playerBounds)) { 
            // player collided with item, so stop moving 
            isMoving = true;
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
                //int border = gameplay.getXBorder();
                if (this.worldX > -24) {
                    worldX  -= speed;
                    isMoving = true;
                }
            } else if (controller.rightPressed) {
                direction = "right";
                if (this.worldX  < this.view.w - 80) {
                    worldX  += speed;
                    isMoving = true;
                }
            } else if(controller.actionPressed){
                direction = "interact"; 
                interact();
            }
            else {
                isMoving = false;
            }
            // if the player has collided with the item and is not colliding anymore, allow
            // movement
            if (hasCollided && !currentItem.checkCollision(playerBounds)) {
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
        } else {
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
                }
                else if (spriteNum == 2) {
                    image = right2;
                } else if (spriteNum == 3 || spriteNum == 6) {
                    image = right3;
                }
                else if (spriteNum == 4) {
                    image = right4;
                } else if (spriteNum == 5) {
                    image = right5;
                }
                break;
            case "interact":
            if (spriteNum == 1) {
                image = downatk1;
            }
            else if (spriteNum == 2) {
                image = downatk2;
            } else if (spriteNum == 3 || spriteNum == 6) {
                image = downatk3;
            }
            else if (spriteNum == 4) {
                image = downatk3;
            } else if (spriteNum == 5) {
                image = down1;
            }
            break;
        }
        //g2.drawImage(image, screenX, screenY, 100, 100, null);
        g2.drawImage(image, worldX, worldY, 100, 100, null);
        g2.draw(playerBounds);
    }
}
