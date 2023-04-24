package no.uib.inf101.sem2.ExploartionValley.model;

import no.uib.inf101.sem2.ExploartionValley.grid.CellPosition;
import no.uib.inf101.sem2.ExploartionValley.grid.Grid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;;

public class gameBoard extends Grid<Character> {

    /**
     * Constructs a game board with the given number of rows and columns, initialized with '-'. The board can also be read from a text file, with each character representing a tile in the board.
     * @param rows The number of rows in the game board.
     * @param cols The number of columns in the game board.
     * @param filename The name of the text file containing the characters representing the tiles in the board.
     * 
     * @throws IOException If there is an error reading the text file.
     */

    public gameBoard(int rows, int cols, String filename) throws IOException {
        super(rows, cols, '-');
        BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/maps/" + filename)));
        List<String> lines = reader.lines().collect(Collectors.toList());
        
        /*
         * Loop over the lines and fill the board
         * If smaller map added the game panel will add black tiles to cover up. then center the new map.
         */
        if (lines.size() != rows) {
            for (int i = 0; i < rows && i < lines.size(); i++) {
                String line = lines.get(i);
                String[] tokens = line.split("\\s+");
                for (int j = 0; j < cols && j < tokens.length; j++) {
                    String token = tokens[j];
                    if (!token.isEmpty()) { // If ' '
                        char c = token.charAt(0);
                        set(new CellPosition((rows / 2) + i - (lines.size() / 2), (cols / 2) + j - (tokens.length / 2)),
                                c);
                    }
                }
            }
        }
        else {
            for (int i = 0; i < rows && i < lines.size(); i++) {
                String line = lines.get(i);
                String[] tokens = line.split("\\s+");
                for (int j = 0; j < cols && j < tokens.length; j++) {
                    String token = tokens[j];
                    if (!token.isEmpty()) { // If ' '
                        char c = token.charAt(0);
                        set(new CellPosition(i, j), c);
                    }
                }
            }
        }
    }

    // To be able to test
    public String prettyString() {
        StringBuilder stringOfBoard = new StringBuilder();
        for (int i = 0; i < this.rows(); i++) {
            for (int j = 0; j < this.cols(); j++) {
                stringOfBoard.append(this.get(new CellPosition(i, j)));
            }
            stringOfBoard.append('\n');
        }
        // Returns form stringbuilder to a string, then trim any spaces to get correct format
        return stringOfBoard.toString().trim();
    }
}
