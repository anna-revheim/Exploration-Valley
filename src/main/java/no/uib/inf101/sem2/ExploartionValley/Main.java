package no.uib.inf101.sem2.ExploartionValley;

import java.io.IOException;
import javax.swing.JFrame;

import no.uib.inf101.sem2.ExploartionValley.model.BackgroundMusic;
import no.uib.inf101.sem2.ExploartionValley.model.gameBoard;
import no.uib.inf101.sem2.ExploartionValley.view.gameView;
import no.uib.inf101.sem2.ExploartionValley.view.ViewableGame;
import no.uib.inf101.sem2.ExploartionValley.model.gameModel;
import no.uib.inf101.sem2.ExploartionValley.model.gameTextBox;

/**
The Main class is the entry point for the Exploration Valley game.
It creates a gameBoard, a model and a view, and starts the game thread.
It also sets up a text box with a welcome message for the player.
*/

public class Main {
    public static final String WINDOW_TITLE = "Exploration valley";
    public static void main(String[] args) {
        try {
            //gameBoard board = new gameBoard(gameplay.getRows(),gameplay.getCols(), gameplay.getMap());
            gameBoard board = new gameBoard(40 ,60, "maps4.txt"); // Original map
            ViewableGame model = new gameModel(board);
            gameView view = new gameView(model);
            JFrame frame = new JFrame(WINDOW_TITLE);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Exploation Valley");
            frame.setContentPane(view);
            frame.setResizable(false);
            frame.pack();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            view.startGameThread();
            BackgroundMusic musicPlayer = new BackgroundMusic("/sound/8bit-music.waw");
            gameTextBox textBox = new gameTextBox();
            textBox.appendText("Welcome to Exploration Valley!\nIt seems you lost your keys your house..\nThats unlucky..Use your 'E' and go look for them!\n\nPress escape to close.");
        }catch (IOException e) {
                System.out.print("Error couldn't find:" + e.getMessage());
            }
    }
}
