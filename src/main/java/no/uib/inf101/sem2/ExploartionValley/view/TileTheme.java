package no.uib.inf101.sem2.ExploartionValley.view;


import java.awt.Color;
import java.awt.Image;

public interface TileTheme {

    Color getBackgroundColor();

    Image getTileImage(char c);
}
