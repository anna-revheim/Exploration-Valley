package no.uib.inf101.sem2.ExploartionValley;

import javax.swing.JFrame;

import no.uib.inf101.sem2.ExploartionValley.model.TetrisBoard;
import no.uib.inf101.sem2.ExploartionValley.view.TetrisView;
import no.uib.inf101.sem2.ExploartionValley.view.ViewableTetrisModel;
import no.uib.inf101.sem2.ExploartionValley.model.TetrisModel;






public class Main {
    public static final String WINDOW_TITLE = "Exploration valley";
    public static void main(String[] args) {

        TetrisBoard board = new TetrisBoard(20, 10);
        ViewableTetrisModel model = new TetrisModel(board);
        TetrisView view = new TetrisView(model);

        JFrame frame = new JFrame(WINDOW_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Exploation Valley");
        frame.setContentPane(view);
        frame.pack();
        frame.setVisible(true);
    }
}
