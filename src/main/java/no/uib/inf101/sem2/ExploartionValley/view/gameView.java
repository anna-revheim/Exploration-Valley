package no.uib.inf101.sem2.ExploartionValley.view;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.Dimension;

import no.uib.inf101.sem2.ExploartionValley.controller.gameController;
import no.uib.inf101.sem2.ExploartionValley.entity.player;
import no.uib.inf101.sem2.ExploartionValley.grid.GridCell;
//import no.uib.inf101.sem2.ExploartionValley.model.tiles.tileManager;

public class gameView extends JPanel implements Runnable {


    private DefaultColorTheme ct;
    private ViewableGame model;
    private static final int OUTER_MARGIN = 0;
    gameController controller = new gameController();
    Thread gameThread;

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    int fps = 60;

    //tileManager tileM = new tileManager(this);
    player player = new player(this, controller);

    public gameView(ViewableGame model) {
        this.model = model;
        this.addKeyListener(controller);
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(1200, 800));
        ct = new DefaultColorTheme();
        this.setBackground(ct.getBackgroundColor());
    }

    public boolean isLoaded = false;  // Game only needs to be painted once

    @Override
    // public ettersom vi ønsker at JComponent kan implementere denne
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        /* Coden blir kalt på, men oppdaterer ikke brettet? 
        if (!isLoaded) {
            drawGame(g2);
        }*/

        this.drawGame(g2); // Tegner brettet, men hvert sekund = meget tregt spill

        
        this.player.draw(g2); //Paint the player
        g2.dispose();

    }

    public void startGameThread() {
        gameThread = new Thread(this); // this = gameview
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / fps;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {
            //System.out.println("Current time");

            // Using sleep method to define a fps.
            try {


                update();
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println("The game loop is running");
        }
    }

    public void update() {
        player.update();
    }

    private void drawGame(Graphics2D g2) {
        double width = this.getWidth() - 2 * OUTER_MARGIN;
        double height = this.getHeight() - 2 * OUTER_MARGIN;
        Rectangle2D rektangel = new Rectangle2D.Double(OUTER_MARGIN, OUTER_MARGIN, width, height);
        g2.setColor(this.ct.getFrameColor());
        g2.fill(rektangel);
        CellPositionToPixelConverter cp = new CellPositionToPixelConverter(rektangel, model.getDimensions(),
                (double) 2);
        drawCell(g2, model.getTilesOnBoard(), cp, ct);
    }

    private void drawCell(Graphics2D g2, Iterable<GridCell<Character>> cells, CellPositionToPixelConverter cp, DefaultColorTheme ct) {
        System.out.println("drawCell called"); // add this line
        for (GridCell<Character> cell : cells) {
            Rectangle2D bounds = cp.getBoundsForCell(cell.pos());
            Image image = ct.getCellImage(cell.value());
            g2.drawImage(image, (int)bounds.getX(), (int)bounds.getY(), (int)bounds.getWidth(), (int)bounds.getHeight(), null);
        }
        isLoaded = true;
    }
}

