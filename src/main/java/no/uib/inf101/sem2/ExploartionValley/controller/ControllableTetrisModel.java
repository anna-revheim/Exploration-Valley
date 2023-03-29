package no.uib.inf101.sem2.ExploartionValley.controller;

public interface ControllableTetrisModel {
    // Interface holder på metodene som blir overført ved implementasjon.
    public boolean moveTetromino(int deltaRow, int deltaCol);
    // den over til move character

    public Integer milli();

    public boolean clockTick();
}
