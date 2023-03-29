package no.uib.inf101.sem2.ExploartionValley.view;

import no.uib.inf101.sem2.ExploartionValley.grid.GridCell;
import no.uib.inf101.sem2.ExploartionValley.grid.GridDimension;

public interface ViewableTetrisModel {

    GridDimension getDimension();

    Iterable<GridCell<Character>> getTilesOnBoard();

    Iterable<GridCell<Character>> revealPieces();
}
