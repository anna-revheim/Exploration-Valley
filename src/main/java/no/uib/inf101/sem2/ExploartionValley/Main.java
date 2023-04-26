package no.uib.inf101.sem2.ExploartionValley;

import java.io.IOException;
import javax.swing.JFrame;
import no.uib.inf101.sem2.ExploartionValley.model.AudioPlayer;
import no.uib.inf101.sem2.ExploartionValley.model.GameBoard;
import no.uib.inf101.sem2.ExploartionValley.view.GameView;
import no.uib.inf101.sem2.ExploartionValley.view.ViewableGame;
import no.uib.inf101.sem2.ExploartionValley.model.GameModel;
import no.uib.inf101.sem2.ExploartionValley.model.GameTextBox;

/**
The Main class is the entry point for the Exploration Valley game.
It creates a gameBoard, a model and a view, and starts the game thread.
It also sets up a text box with a welcome message for the player.
*/

public class Main {
    public static final String WINDOW_TITLE = "Exploration valley";
    public static void main(String[] args) {
        try {
            GameBoard board = new GameBoard(40 ,60, "maps4.txt"); // Original map
            ViewableGame model = new GameModel(board);
            GameView view = new GameView(model);
            
            JFrame frame = new JFrame(WINDOW_TITLE);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Exploation Valley");
            frame.setContentPane(view);
            frame.setResizable(false);
            frame.pack();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            view.startGameThread();
            AudioPlayer musicplayer = new AudioPlayer();
            musicplayer.play("src/main/resources/sound/music/GC8bit.wav", 0.05);
            //GameTextBox textBox = new GameTextBox();
            //textBox.appendText("Welcome to Exploration Valley!\n\nThose darn bats have been violating your backyard for way too long.\n\nPress or hold E to eliminate them!\n\nPress escape to close.");
        }catch (IOException e) {
                System.out.print("Error couldn't find:" + e.getMessage());
            }
    }
}
