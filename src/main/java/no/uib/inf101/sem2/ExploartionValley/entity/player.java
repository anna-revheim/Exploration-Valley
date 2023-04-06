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
    String direction = "down";

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
            up3 = ImageIO.read(getClass().getResourceAsStream("/up/up3.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/up/up4.png"));
            up5 = ImageIO.read(getClass().getResourceAsStream("/up/up5.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/down/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/down/down2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/down/down3.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/down/down4.png"));
            down5 = ImageIO.read(getClass().getResourceAsStream("/down/down5.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/left/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/left/left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/left/left3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/left/left4.png"));
            left5 = ImageIO.read(getClass().getResourceAsStream("/left/left5.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/right/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/right/right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/right/right3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/right/right4.png"));
            right5 = ImageIO.read(getClass().getResourceAsStream("/right/right5.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        boolean isMoving = false;
        if (controller.upPressed == true) {
            direction = "up";
            y -= speed;
            isMoving = true;
        } else if (controller.downPressed) {
            direction = "down";
            y += speed;
            isMoving = true;
        } else if (controller.leftPressed) {
            direction = "left";
            x -= speed;
            isMoving = true;
        } else if (controller.rightPressed) {
            direction = "right";
            x += speed;
            isMoving = true;
        } else{
            isMoving = false;
        }

        if (isMoving == true) {
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
        else {
            spriteNum = 3;
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
                else if (spriteNum == 2) {
                    image = up2;
                }
                else if (spriteNum == 3 || spriteNum == 6) {
                    image = up3;
                }
                else if (spriteNum == 4) {
                    image = up4;
                }
                else if (spriteNum == 5) {
                    image = up5;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                else if (spriteNum == 2) {
                    image = down2;
                }
                else if (spriteNum == 3 || spriteNum == 6) {
                    image = down3;
                }
                else if (spriteNum == 4) {
                    image = down4;
                }
                else if (spriteNum == 5) {
                    image = down5;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                else if (spriteNum == 2) {
                    image = left2;
                }
                else if (spriteNum == 3 || spriteNum == 6) {
                    image = left3;
                }
                else if (spriteNum == 4) {
                    image = left4;
                }
                else if (spriteNum == 5) {
                    image = left5;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                else if (spriteNum == 3 || spriteNum == 6) {
                    image = right3;
                }
                if (spriteNum == 4) {
                    image = right4;
                }
                else if (spriteNum == 5) {
                    image = right5;
                }
                break;
        }
        g2.drawImage(image, x, y, 100, 100, null);
    }
}
