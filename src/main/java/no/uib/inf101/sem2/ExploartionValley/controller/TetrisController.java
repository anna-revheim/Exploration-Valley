package no.uib.inf101.sem2.ExploartionValley.controller;

import no.uib.inf101.sem2.ExploartionValley.view.TetrisView;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TetrisController implements KeyListener {
    ControllableTetrisModel controller;
    TetrisView tetrisView;
    Timer tick;

    //Konstruktør til tetriscontroller. Tar inn parameter i type ControllableTetrismodel og tetrisview. Setter dette som innstansvariabler.
    public TetrisController(ControllableTetrisModel controller, TetrisView tetrisView) {
        this.controller = controller;
        this.tetrisView = tetrisView;
        //this.tick = new Timer(model.milli(), this::clockTick);
        tetrisView.addKeyListener(this);
        tetrisView.setFocusable(true);
    }

    @Override
    public void keyPressed(KeyEvent action) {
        // <-- Flytt Venstre
        if (action.getKeyCode() == KeyEvent.VK_LEFT) {
            controller.moveTetromino(0, -1);
        }
        // --> Flytt Høgre
        else if (action.getKeyCode() == KeyEvent.VK_RIGHT) {
            controller.moveTetromino(0, 1);
        }
        // vvv Flytt Ned
        else if (action.getKeyCode() == KeyEvent.VK_DOWN) {
            controller.moveTetromino(1, 0);
        }
        // <^> Roter
        else if (action.getKeyCode() == KeyEvent.VK_UP) {
        }
        // VVV Dropp ned
        if (action.getKeyCode() == KeyEvent.VK_SPACE) {
            //kaller metode dropBrick. 
        }
        //Bruker repaint() for å gi konstante oppdateringer til view.
        this.tetrisView.repaint();
    }
    public void clockTick(ActionEvent action){
        
    }


    @Override
    public void keyReleased(KeyEvent arg0) {
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }
}
