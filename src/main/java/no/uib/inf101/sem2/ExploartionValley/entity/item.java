package no.uib.inf101.sem2.ExploartionValley.entity;

import java.awt.Graphics2D;
import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;

import no.uib.inf101.sem2.ExploartionValley.view.gameView;

public class item extends entity {

    gameView view;

    public item(gameView view) {
        this.view = view;
        getItemImage();
    }

    public void getItemImage() {
        try {
            tree = ImageIO.read(getClass().getResourceAsStream("/item/tree.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawItem(Graphics2D g2, String FileName) throws FileNotFoundException {
        try {
            Scanner scanner = new Scanner(new File(FileName));
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
                ArrayList<String> item = new ArrayList<>();

                List<String> result;
                try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
                    result = lines.collect(Collectors.toList());
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
}
    }