package no.uib.inf101.sem2.ExploartionValley.view;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TileDirectory implements TileTheme {

    @Override
    public Color getBackgroundColor() {
        // Returns black - for backgroundcolor
        return Color.BLACK;
    }

    public Color getFrameColor() {
        // Returns black - for framecolor
        return Color.BLACK;
    }

    /* This class provides a way to retrieve the image associated with a given character on the gamemap.
     * 
     * @param c - Takes in char from map
     */
    @Override
    public Image getTileImage(char c) {
        String imagePath;
        // Checking character and what tiles it should be
        switch (c) {
            case '-':
            imagePath = "/tiles/background/blank.png";
                break;
            case 'g':
            imagePath = "/tiles/grass/grass0.png";
                break;
            case '1' :
            imagePath = "/tiles//grass/grass1.png";
                break;
            case '2' :
            imagePath = "/tiles/grass/grass2.png";
                break;
            case '3' :
            imagePath = "/tiles/grass/grass3.png";
                break;
            case '4' :
            imagePath = "/tiles/grass/grass4.png";
                break;
            case 'k' :
            imagePath = "/tiles/grass/grassK1.png";
                break;
            case 'l' :
            imagePath = "/tiles/grass/grassK2.png";
                break;
            case 'i' :
            imagePath = "/tiles/grass/grassK3.png";
                break;            
            case 'o' :
            imagePath = "/tiles/grass/grassK4.png";
                break;
            case 'w' :
            imagePath = "/tiles/water/water.png";
                break;
            case 'b' :
            imagePath = "/tiles/water/bottom.png";
                break;
            case 't' :
            imagePath = "/tiles/water/top.png";
                break;
            case 'x' :
            imagePath = "/tiles/water/lTop.png";
                break;
            case 'v' :
            imagePath = "/tiles/water/rTop.png";
                break;
            case 'd' :
            imagePath = "/tiles/water/lBottom.png";
                break;
            case 'f' :
            imagePath = "/tiles/water/rBottom.png";
                break;
            case '5' :
            imagePath = "/tiles/water/left.png";
                break;
            case '6' :
            imagePath = "/tiles/water/right.png";
                break;
            default:
                throw new IllegalArgumentException(
                "No available image for '" + c + "'");
        }
        try {
            return ImageIO.read(getClass().getResourceAsStream(imagePath));
        } catch (IOException e) {
            throw new RuntimeException("Error loading image from " + imagePath, e);
        }
    }
}
