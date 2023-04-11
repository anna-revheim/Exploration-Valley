package no.uib.inf101.sem2.ExploartionValley;

import java.io.IOException;


import javax.swing.JFrame;

import no.uib.inf101.sem2.ExploartionValley.model.gameBoard;

import no.uib.inf101.sem2.ExploartionValley.view.gameView;
import no.uib.inf101.sem2.ExploartionValley.view.ViewableGame;
import no.uib.inf101.sem2.ExploartionValley.model.gameModel;

public class Main {
    public static final String WINDOW_TITLE = "Exploration valley";

    public static void main(String[] args) {

        try {
            gameBoard board = new gameBoard(40,60, "maps1.txt");
            //gameBoard board = new gameBoard(10,10, "maps2.txt");
            ViewableGame model = new gameModel(board);
            gameView view = new gameView(model);
    
            JFrame frame = new JFrame(WINDOW_TITLE);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Exploation Valley");
            frame.setContentPane(view);
            frame.setResizable(false);
            frame.pack();
            frame.setVisible(true);
            view.startGameThread();
        } catch (IOException e) {
            System.out.print("Error couldn't find:" + e.getMessage());
        }
    }
}
