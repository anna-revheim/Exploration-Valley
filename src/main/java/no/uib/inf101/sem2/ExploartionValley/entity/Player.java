package no.uib.inf101.sem2.ExploartionValley.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import no.uib.inf101.sem2.ExploartionValley.controller.GameController;
import no.uib.inf101.sem2.ExploartionValley.model.AudioPlayer;
import no.uib.inf101.sem2.ExploartionValley.model.GameTextBox;
import no.uib.inf101.sem2.ExploartionValley.view.GameView;

/**
 * The player class represents the player in a game. It extends the entity
 * class.
 * It contains information about the player's position, speed, direction, and
 * interaction range.
 */

public class Player extends Entity {
    GameView view; // gp
    GameController controller; // keyh
    String direction = "down";
    public boolean isMoving;
    private boolean hasCollided = false;
    public Rectangle playerBounds;
    public Rectangle interactRange;
    private Rectangle hitBox;
    private int StepCounter;
    public int KillCount;
    public int hitPoints;

    // Where we place the player
    public final int screenX;
    public final int screenY;
    private Random rand;

    /**
     * Constructor for the 'player' class.
     * @param view       The 'gameView' instance for displaying the game.
     * @param controller The 'gameController' instance for controlling the game.
     */
    public Player(GameView view, GameController controller) {
        this.view = view;
        this.controller = controller;
        setDefaultValues();
        getCharacterImage();
        screenX = this.view.w / 2 - 56;
        screenY = this.view.h / 2 - 60;
        hitBox = new Rectangle(-100, -100, 100, 100);
        rand = new Random();
        KillCount = 0;
    }

    /*
     * Sets players default values. Not in constructor as we want to
     * be able to reset the players values midgame.
     */
    public void setDefaultValues() {
        this.hitPoints = 4;
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

    public int getKillCount() {
        return this.KillCount;
    }

    public void interact() {
        PlayerSword();
        if (checkCollision(hitBox, view.bat.npcBounds)) {
            System.out.println("Attack enemy");
            view.bat.hitNumber--;
            if (view.bat.hitNumber == 0) {
                view.bat.x = rand.nextInt(this.view.w - 200);
                view.bat.y = rand.nextInt(this.view.h - 200);
                System.out.println("Bat Dead");
                this.KillCount++;
                view.bat.hitNumber = 2;
                if (this.KillCount >= 5){
                    view.bat.updateBat();
                    view.bat.hitNumber = 5;
                }
            }
        }
    }

    /**
     * Updates the player's position and checks for collisions.
     * This code is quite long due to its complexity. First checks for collision, if
     * no move.
     * After it checks for directions and inputs. Also keeps track for spritecounter
     * that is used for drawing. Code inspiration from: @RyiSnow
     */

    public void update() {
        PlayerSteps();
        playerBounds.setLocation(worldX + 36, worldY + 60);
        // To do collisions. First check when collision happens, then when not.
        // check collision with item

        if (checkCollision(playerBounds, view.item.itemBounds)) { //
            hasCollided = true;

            // move player away from item
            if (direction == "up") {
                worldY += 4;
            } else if (direction == "down") {
                worldY -= 4;
            } else if (direction == "left") {
                worldX += 4;
            } else if (direction == "right") {
                worldX -= 4;
            }
        }

        else if (checkCollision(playerBounds, view.bat.npcBounds)) {
            hasCollided = true;
            if (direction == "up") {
                worldY += 35;
            } else if (direction == "down") {
                worldY -= 35;
            } else if (direction == "left") {
                worldX += 35;
            } else if (direction == "right") {
                worldX -= 35;
            }
            hitPoints--;
            if (hitPoints > 0) {

                System.out.println("Bat is attacking");
            }
            while (hitPoints == 0) {
                try {
                    Thread.sleep(1000); // add a delay of 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                direction = "lay";
                isMoving = false;
                setDefaultValues();
                System.out.println("We got respawned");
                this.KillCount = 0;
                gameOver();
            }

        } else {
            // player did not collide with item, so continue moving
            if (controller.upPressed == true) {
                direction = "up";
                if ((this.worldY > -40)) {
                    worldY -= speed;
                    isMoving = true;
                }
            } else if (controller.downPressed) {
                direction = "down";
                if (this.worldY < this.view.h - 128) {
                    worldY += speed;
                    isMoving = true;
                }
            } else if (controller.leftPressed) {
                direction = "left";
                if (this.worldX > 0) {
                    worldX -= speed;
                    isMoving = true;
                }
            } else if (controller.rightPressed) {
                direction = "right";
                if (this.worldX < this.view.w - 100) {
                    worldX += speed;
                    isMoving = true;
                }
            } else if(controller.actionPressed){
                hitBox.setLocation(worldX + 5, worldY + 30);
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
            } else {
                isMoving = false;
                hitBox.setLocation(-100, -100); //Resets the hitBox
            }
            // if the player has collided with the item and is not colliding anymore, allow
            // movement
            if (hasCollided && !(checkCollision(hitBox, view.item.itemBounds))) {
                hasCollided = false;
                isMoving = true;
                // System.out.println("X: " + this.worldX + "\tY: " + this.worldY);
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
        } else if (controller.actionPressed = false) {
            spriteNum = 6;
            spriteCounter = 0;
        } else {
            spriteNum = 3;
            spriteCounter = 0;
        }
    }

    public boolean isMoving() {
        return isMoving;
    }

    public int getHitPoints() {
        return this.hitPoints;
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
                    image = right2;
                } else if (spriteNum == 3 || spriteNum == 6) {
                    image = right3;
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
        //g2.draw(playerBounds);
        //g2.draw(hitBox);
    }

    private void gameOver() {
        GameTextBox textBox = new GameTextBox();
        int index = rand.nextInt(2);
        switch (index) {
            case 0:
                textBox.appendText(
                        "Sorry to break it to you but you died...\n\nThat was a pretty bloody death right there.\n\nBe careful! After you killed 5 bats, their bigger and older red siblings come out.\n\nEscape to close.\n\n\n\n");
            case 1:
                textBox.appendText("\t       OUCH!!!!\n\n That looked brutal, too bad you dont own a gun.\n\nBe careful! After you killed 5 bats, their bigger and older red siblings come out.\n\nEscape to close.");
        }
    }

    public void PlayerSteps() {
        if (this.isMoving) {
            StepCounter++;
            AudioPlayer audio = new AudioPlayer();
            if (StepCounter == 15) {
                audio.play("src/main/resources/sound/misc/grass_l.wav", 0.2);
            } else if (StepCounter == 30) {
                audio.play("src/main/resources/sound/misc/grass_r.wav", 0.2);
                StepCounter = 0;
            }
        }
    }

    public void PlayerSword() {
        AudioPlayer audio = new AudioPlayer();
        int index = rand.nextInt(3);
        switch (index) {
            case 0:
                audio.play("src/main/resources/sound/misc/sword2.wav", 1);
            case 1:
                audio.play("src/main/resources/sound/misc/sword3.wav", 1);
            case 2:
                audio.play("src/main/resources/sound/misc/sword4.wav", 1);
        }
    }
}
// Code got quite long but it was needed.