package no.uib.inf101.sem2.ExploartionValley.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class gameMaps {

    public static char[][] loadMap(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        ArrayList<String> lines = new ArrayList<String>();
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();
        int numRows = lines.size();
        int numCols = lines.get(0).length();
        char[][] map = new char[numRows][numCols];
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                map[r][c] = lines.get(r).charAt(c);
            }
        }
        return map;
    }
}