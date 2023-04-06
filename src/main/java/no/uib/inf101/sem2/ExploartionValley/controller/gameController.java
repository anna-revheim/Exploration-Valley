package no.uib.inf101.sem2.ExploartionValley.controller;

import no.uib.inf101.sem2.ExploartionValley.view.gameView;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class gameController implements KeyListener {
    controllableGameModel controller;
    gameView gameView;
    Timer tick;
    public boolean upPressed, downPressed, leftPressed, rightPressed;


    // Konstruktør til tetriscontroller. Tar inn parameter i type
    // ControllableTetrismodel og tetrisview. Setter dette som innstansvariabler.
    public gameController() {
        //this.controller = controller;
        //this.gameView = gameView;
        // this.tick = new Timer(model.milli(), this::clockTick);
    }

    @Override
    public void keyPressed(KeyEvent action) {
        // <-- Flytt Venstre
        if (action.getKeyCode() == KeyEvent.VK_A) {
            leftPressed = true;
        }
        // --> Flytt Høgre
        if (action.getKeyCode() == KeyEvent.VK_D) {
            rightPressed = true;

        }
        // vvv Flytt Ned
        if (action.getKeyCode() == KeyEvent.VK_S) {
            downPressed = true;
        }
        // ^^^ flytt opp
        if (action.getKeyCode() == KeyEvent.VK_W) {
            upPressed = true;
        }
        // VVV Dropp ned

        //if (action.getKeyCode() == KeyEvent.VK_SPACE) { NOT IMPLEMENTED
        //}

        // Bruker repaint() for å gi konstante oppdateringer til view.
        //gameView.repaint(); //Måtte fjernes(?)
    }

    public void clockTick(ActionEvent action) {

    }

    @Override
    public void keyReleased(KeyEvent action) {
        if (action.getKeyCode() == KeyEvent.VK_A) {
            leftPressed = false;
        }
        // --> Flytt Høgre
        if (action.getKeyCode() == KeyEvent.VK_D) {
            rightPressed = false;
        }
        // vvv Flytt Ned
        if (action.getKeyCode() == KeyEvent.VK_S) {
            downPressed = false;
        }
        // ^^^ flytt opp
        if (action.getKeyCode() == KeyEvent.VK_W) {
            upPressed = false;
        }
        // VVV Dropp ned
        if (action.getKeyCode() == KeyEvent.VK_SPACE) {

        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }
}
