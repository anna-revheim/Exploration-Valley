package no.uib.inf101.sem2.ExploartionValley.view;

import no.uib.inf101.sem2.ExploartionValley.grid.GridCell;
import no.uib.inf101.sem2.ExploartionValley.grid.GridDimension;

public interface ViewableGame {

    // Get the GridDimensions of the board
    GridDimension getDimensions();

    // Get all of the cells on the board
    Iterable<GridCell<Character>> getTilesOnBoard();
}
