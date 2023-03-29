package no.uib.inf101.tetris.controller;

import no.uib.inf101.tetris.model.GameState;
import no.uib.inf101.tetris.model.tetromino.Tetromino;

public interface ControllableTetrisModel {
    // Interface holder på metodene som blir overført ved implementasjon.
    public boolean moveTetromino(int deltaRow, int deltaCol);

    public boolean rotateTetromino();

    public void dropBrick();

    public void stickTetromino();

    public Tetromino getNextTetromino();

    GameState getGameState();

    public Integer milli();

    public boolean clockTick();
}
