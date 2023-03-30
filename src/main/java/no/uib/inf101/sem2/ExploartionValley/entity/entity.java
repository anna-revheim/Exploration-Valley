package no.uib.inf101.sem2.ExploartionValley.entity;

import java.awt.image.BufferedImage;

public class entity {
    // this class is the parentclass to all things player, monster, npc related.
    // all beings need a position, and a speed.
    public int x, y;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 0;
}
