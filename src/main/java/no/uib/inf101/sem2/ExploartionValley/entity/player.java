package no.uib.inf101.sem2.ExploartionValley.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import no.uib.inf101.sem2.ExploartionValley.controller.gameController;
import no.uib.inf101.sem2.ExploartionValley.view.gameView;

public class player extends entity {

    gameView view;
    gameController controller;

    public player(gameView view, gameController controller) {
        this.view = view;
        this.controller = controller;

        setDefaultValues();
        getCharacterImage();
    }

    public void setDefaultValues() {
        this.x = 100;
        this.y = 100;
        this.speed = 4;
    }

    public void getCharacterImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/up/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/up/up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/down/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/down/down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/left/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/left/left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/right/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/right/right2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (controller.upPressed == true) {
            direction = "up";
            y -= speed;
        } else if (controller.downPressed) {
            direction = "down";
            y += speed;
        } else if (controller.leftPressed) {
            direction = "left";
            x -= speed;
        } else if (controller.rightPressed) {
            direction = "right";
            x += speed;
        }

        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        // g2.setColor(Color.white);
        // g2.fillRect(this.x, this.y, 40, 40); //Når vi veit tilesize, så kan vi hente
        // det fra view. 40 for no.

        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, x, y, 100, 100, null);
    }
}
