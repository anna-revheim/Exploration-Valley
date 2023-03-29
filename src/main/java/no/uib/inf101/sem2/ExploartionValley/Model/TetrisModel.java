package no.uib.inf101.sem2.ExploartionValley.Model;


import no.uib.inf101.sem2.ExploartionValley.grid.GridDimension;
import no.uib.inf101.sem2.ExploartionValley.controller.ControllableTetrisModel;

public class TetrisModel implements ControllableTetrisModel {

    private GridDimension gd;
    //private GameState gamestate;

    public void TetrisMode() {
        //this.gamestate = GameState.ACTIVE_GAME;
    }


/* 
    @Override
    public boolean moveTetromino(int deltaRow, int deltaCol) {
        Tetromino movedtetro = this.tetromino.shiftedBy(deltaRow, deltaCol);
        if (legalMove(movedtetro)) {
            this.tetromino = this.tetromino.shiftedBy(deltaRow, deltaCol); // endrer tetrominoens posisjon.
            return true;
        } else {
            return false;
        }
    }
    */
/* 
    // Fått hjelp av Kristerffer_#5574 på discord - kjekt sammarbeid
    private boolean legalMove(Tetromino tet) {
        for (GridCell<Character> cell : tet) {
            int row = cell.pos().row();
            int col = cell.pos().col();
            if (row < 0 || row >= board.rows()) {
                return false;
            }
            if (col < 0 || col >= board.cols()) {
                return false;
            }
            if (board.get(cell.pos()) != '-') {
                return false;
            }
        }
        return true;
    }*/



/* 
    @Override
    public GameState getGameState() {
        return this.gamestate;
    }*/

    @Override
    public Integer milli() {
        return 1000;
    }


    @Override
    public boolean moveTetromino(int deltaRow, int deltaCol) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveTetromino'");
    }


    @Override
    public boolean clockTick() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clockTick'");
    }
}