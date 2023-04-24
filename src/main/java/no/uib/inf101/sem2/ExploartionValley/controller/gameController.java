package no.uib.inf101.sem2.ExploartionValley.controller;

import no.uib.inf101.sem2.ExploartionValley.model.gameModel;
import no.uib.inf101.sem2.ExploartionValley.view.gameView;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
This class serves as the controller for the Exploration Valley game. It implements the KeyListener interface to handle keyboard input from the user.
It interacts with the controllableGameModel, gameView, and gameModel classes to manage the game's state and user interactions.
*/

public class gameController implements KeyListener {
    controllableGameModel controller;
    gameView gameView;
    gameModel model;
    public boolean upPressed, downPressed, leftPressed, rightPressed, actionPressed;

    public gameController() {
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
        if (action.getKeyCode() == KeyEvent.VK_E) {
            actionPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent action) {
        // <-- Flytt venstre
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
        // Interact | action
        if (action.getKeyCode() == KeyEvent.VK_E) {
            actionPressed = false;
        }
    }

    public void clockTick(ActionEvent action) {
    }
    @Override
    public void keyTyped(KeyEvent arg0) {
    }
}
