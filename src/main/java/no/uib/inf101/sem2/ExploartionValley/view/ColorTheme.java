package no.uib.inf101.tetris.view;

import java.awt.Color;

public interface ColorTheme {

    Color getBackgroundColor();

    Color getFrameColor();

    Color getCellColor(char color);

    Color GameOverColor();

    Color GameOverTextColor();
}
