package no.uib.inf101.sem2.ExploartionValley.model;

import no.uib.inf101.sem2.ExploartionValley.grid.GridCell;
import no.uib.inf101.sem2.ExploartionValley.grid.GridDimension;
import no.uib.inf101.sem2.ExploartionValley.view.ViewableGame;

/*
 * Represents a model for a game with a game board
 */

public class gameModel implements ViewableGame{
    public gameBoard board; 

    /**
    *Constructs a game model with the given game board.
    *@param board the game board
    */
    public gameModel(gameBoard board) {
        this.board = board;
    }
    
    @Override
    public GridDimension getDimensions() {
        return this.board;
    }
    
    @Override
    public Iterable<GridCell<Character>> getTilesOnBoard() {
        return this.board;
    }
}