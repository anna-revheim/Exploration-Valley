package no.uib.inf101.sem2.ExploartionValley.view;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DefaultColorTheme implements ColorTheme {

    @Override
    public Color getBackgroundColor() {
        // Personlig utvalg av bakgrunnsfarge.
        return Color.BLACK;
    }

    @Override
    public Color getFrameColor() {
        // Personlig utvalg av rammefarge.
        return Color.LIGHT_GRAY;
    }

    @Override
    public Image getCellImage(char c) {
        String imagePath;
        // Checking character and what tiles it should be
        switch (c) {
            case '-':
            imagePath = "/tiles/grass/grass0.png";
                break;
            case 'g' :
            imagePath = "/tiles/grass/grass1.png";
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

    @Override
    public Color getCellColor(char c) {
        // Tar inn char c og sjekker kva farge den hører til.
        Color color = switch (c) {
            case 'L' -> Color.cyan;
            case 'S' -> Color.GREEN;
            case 'Z' -> Color.BLUE;
            case 'J' -> Color.YELLOW;
            case '-' -> Color.BLACK;
            case 'T' -> Color.PINK;
            case 'O' -> Color.WHITE;
            case 'I' -> Color.ORANGE;
            default -> throw new IllegalArgumentException(
                    "No available color for '" + c + "'");
        };
        return color;
    }

    @Override
    public Color GameOverColor() {
        // Returnerer en sladdet bakgrunnsfarge.
        return new Color(0, 0, 0, 128);
    }

    @Override
    public Color GameOverTextColor() {
        // Returnerer ønsket tekstfarge.
        return Color.WHITE;
    }
}
