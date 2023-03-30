package no.uib.inf101.sem2.ExploartionValley.view;

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Dimension;

import no.uib.inf101.sem2.ExploartionValley.controller.gameController;
import no.uib.inf101.sem2.ExploartionValley.grid.GridCell;

public class gameView extends JPanel implements Runnable {

    private DefaultColorTheme ct;
    private ViewableTetrisModel model;
    private static final int OUTER_MARGIN = 1;
    gameController controller = new gameController();
    Thread gameThread;

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    int fps = 60;

    public gameView(ViewableTetrisModel model) {
        this.model = model;
        this.addKeyListener(controller);
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(1200, 800));
        ct = new DefaultColorTheme();
        this.setBackground(ct.getBackgroundColor());
    }

    @Override
    // public ettersom vi ønsker at JComponent kan implementere denne
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawGame(g2);
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, 40, 40);
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
            long currentTime = System.nanoTime();
            // System.out.println("Current time")
            update();

            repaint();

            //Bruker sleep metoden.
            try {
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
            System.out.println("The game loop is running");
        }
    }

    public void update() {
        if (controller.upPressed == true) {
            playerY -= playerSpeed;
        } else if (controller.downPressed) {
            playerY += playerSpeed;
        } else if (controller.leftPressed) {
            playerX -= playerSpeed;
        } else if (controller.rightPressed) {
            playerX += playerSpeed;
        }
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

    private void drawCell(Graphics2D g2, Iterable<GridCell<Character>> cells, CellPositionToPixelConverter cp,
            DefaultColorTheme ct) {
        for (GridCell<Character> cell : cells) {
            Rectangle2D bounds = cp.getBoundsForCell(cell.pos());
            Color color = ct.getCellColor(cell.value());
            g2.setColor(color);
            g2.fill(bounds);
        }
    }

}