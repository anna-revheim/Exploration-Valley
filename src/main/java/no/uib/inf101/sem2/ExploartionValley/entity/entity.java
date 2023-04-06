package no.uib.inf101.sem2.ExploartionValley.entity;

import java.awt.image.BufferedImage;

public class entity {
    // this class is the parentclass to all things player, monster, npc related.
    // all beings need a position, and a speed.
    public int x, y;
    public int speed;

    public BufferedImage up1, up2, up3, up4, up5, down1, down2, down3, down4, down5, left1, left2, left3, left4, left5, right1, right2, right3, right4, right5;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
}
