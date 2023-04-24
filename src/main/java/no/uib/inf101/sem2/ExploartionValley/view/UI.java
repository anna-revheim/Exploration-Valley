package no.uib.inf101.sem2.ExploartionValley.view;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UI {

    public BufferedImage hp0, hp2, hp4, hp6, hp8;
    private gameView view;
    BufferedImage healthBar = null;

    public UI(gameView view) {
        this.view = view;
        getUIImage();
    }

    public void getUIImage() {
        try {
            hp0 = ImageIO.read(getClass().getResourceAsStream("/UI/0.png"));
            hp2 = ImageIO.read(getClass().getResourceAsStream("/UI/0.png"));
            hp4 =  ImageIO.read(getClass().getResourceAsStream("/UI/0.png"));
            hp6 =  ImageIO.read(getClass().getResourceAsStream("/UI/0.png"));
            hp8 = ImageIO.read(getClass().getResourceAsStream("/UI/0.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(int health) {
        switch (health) {
            case 0:
                healthBar = hp0;
                break;
            case 1:
                healthBar = hp2;
                break;
            case 2:
                healthBar = hp4;
                break;
            case 3:
                healthBar = hp6;
                break;
            case 4:
                healthBar = hp8;
                break;
            default:
                break;
        }
        
    }

    public void drawUI(Graphics2D g2d, int health) {
        g2d.drawImage(healthBar, 10, 30, 32, 104, null);
    }
    
}