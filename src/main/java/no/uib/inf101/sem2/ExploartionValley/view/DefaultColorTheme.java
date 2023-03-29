package no.uib.inf101.sem2.ExploartionValley.view;

import java.awt.Color;

public class DefaultColorTheme implements ColorTheme {

    @Override
    public Color getBackgroundColor() {
        //Personlig utvalg av bakgrunnsfarge.
        return Color.BLACK;
    }

    @Override
    public Color getFrameColor() {
        //Personlig utvalg av rammefarge.
        return Color.LIGHT_GRAY;
    }

    @Override
    public Color getCellColor(char c) {
        //Tar inn char c og sjekker kva farge den hører til.
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
        //Returnerer en sladdet bakgrunnsfarge.
        return new Color(0, 0, 0, 128);
    }

    @Override
    public Color GameOverTextColor() {
    //Returnerer ønsket tekstfarge.
        return Color.WHITE;
    }
}
