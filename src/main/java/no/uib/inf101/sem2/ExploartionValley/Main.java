package no.uib.inf101.sem2.ExploartionValley;
import no.uib.inf101.sem2.ExploartionValley.view.TetrisView;

import javax.swing.JFrame;

public class Main {
    public static final String WINDOW_TITLE = "Exploration valley";
    public static void main(String[] args) {
        TetrisView view = new TetrisView();

        JFrame frame = new JFrame(WINDOW_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Exploation Valley");
        frame.setContentPane(view);
        frame.pack();
        frame.setVisible(true);
    }
}
