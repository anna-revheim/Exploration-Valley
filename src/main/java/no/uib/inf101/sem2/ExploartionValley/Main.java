package no.uib.inf101.sem2.ExploartionValley;



import javax.swing.JFrame;

import no.uib.inf101.sem2.ExploartionValley.model.gameBoard;

import no.uib.inf101.sem2.ExploartionValley.view.gameView;
import no.uib.inf101.sem2.ExploartionValley.view.ViewableTetrisModel;
import no.uib.inf101.sem2.ExploartionValley.model.gameModel;

public class Main {
    public static final String WINDOW_TITLE = "Exploration valley";

    public static void main(String[] args) {
        gameBoard board = new gameBoard(2, 2);
        ViewableTetrisModel model = new gameModel(board);
        gameView view = new gameView(model);

        JFrame frame = new JFrame(WINDOW_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Exploation Valley");
        frame.setContentPane(view);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        view.startGameThread();
    }
}
