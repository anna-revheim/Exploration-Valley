package no.uib.inf101.sem2.ExploartionValley.view;


import java.awt.Color;
import java.awt.Image;

public interface ColorTheme {

    Color getBackgroundColor();

    Color getFrameColor();

    Image getCellImage(char c);

    Color getCellColor(char c);

    Color GameOverColor();

    Color GameOverTextColor();
}
