package no.uib.inf101.sem2.ExploartionValley.grid;

import java.awt.Image;

public record GridCell<E>(CellPosition pos, E value, Image image) {
    //GridCell<E> er en klasse som holder på verdier. Den vil ha verdi CellPosition pos, og E value. Der Cellposition er gitt med (rows,cols).
}
