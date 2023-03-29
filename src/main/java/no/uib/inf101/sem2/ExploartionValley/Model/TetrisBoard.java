package no.uib.inf101.sem2.ExploartionValley.model;

import no.uib.inf101.sem2.ExploartionValley.grid.CellPosition;
import no.uib.inf101.sem2.ExploartionValley.grid.Grid;

public class TetrisBoard extends Grid<Character> {

    //The board is made by rows*cols and will be filled with '-'
    public TetrisBoard(int rows, int cols) {
        //Super method to get the constructor
        super(rows, cols, '-');
    }


    //To be able to test
    public String prettyString() {
        StringBuilder stringOfBoard = new StringBuilder();
        for (int i = 0; i < this.rows(); i++) {
            for (int j = 0; j < this.cols(); j++) {
                stringOfBoard.append(this.get(new CellPosition(i, j)));
            }
            stringOfBoard.append('\n');
        }
        //Returns form stringbuilder to a string, then trim any spaces to get correct format
        return stringOfBoard.toString().trim();
    }

}

