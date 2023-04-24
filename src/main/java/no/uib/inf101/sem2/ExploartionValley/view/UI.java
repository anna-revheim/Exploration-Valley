package no.uib.inf101.sem2.ExploartionValley.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UI {
    public BufferedImage hp0, hp2, hp4, hp6, hp8;
    BufferedImage healthBar = null;
    private GameView view;


    public UI(GameView view) {
        this.view = view;
        getUIImage();
    }

    public void getUIImage() {
        try {
            hp0 = ImageIO.read(getClass().getResourceAsStream("/UI/0.png"));
            hp2 = ImageIO.read(getClass().getResourceAsStream("/UI/2.png"));
            hp4 =  ImageIO.read(getClass().getResourceAsStream("/UI/4.png"));
            hp6 =  ImageIO.read(getClass().getResourceAsStream("/UI/6.png"));
            hp8 = ImageIO.read(getClass().getResourceAsStream("/UI/8.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public BufferedImage getHPimage(int health) {
        switch (health) {
            case 0:
                healthBar = hp0;
                return healthBar;
            case 1:
                healthBar = hp2;
                return healthBar;
            case 2:
                healthBar = hp4;
                return healthBar;
            case 3:
                healthBar = hp6;
                return healthBar;
            case 4:
                healthBar = hp8;
                return healthBar;
            default:
                return null;
        }
    }

    public void drawUI(Graphics2D g2d, int health) {
        healthBar = getHPimage(health);
        g2d.drawImage(healthBar, 10, 30, 32, 104, null);
    }
    
}