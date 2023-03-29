package no.uib.inf101.sem2.ExploartionValley.model;

import no.uib.inf101.sem2.ExploartionValley.grid.GridCell;
import no.uib.inf101.sem2.ExploartionValley.grid.GridDimension;
import no.uib.inf101.sem2.ExploartionValley.view.ViewableTetrisModel;

/*
 * Parts of this code is inspired by #Loosen from discord
 */

public class TetrisModel implements ViewableTetrisModel{

    //Instance variable
    private TetrisBoard board;

    //Constructor for the board and the first piece
    public TetrisModel(TetrisBoard board) {
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

    @Override
    public Iterable<GridCell<Character>> getFallingPiece() {
        throw new UnsupportedOperationException("Unimplemented method 'getFallingPiece'");
    }


}