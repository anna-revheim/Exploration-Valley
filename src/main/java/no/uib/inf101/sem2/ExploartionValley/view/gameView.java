package no.uib.inf101.sem2.ExploartionValley.view;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.Dimension;
import no.uib.inf101.sem2.ExploartionValley.controller.gameController;
import no.uib.inf101.sem2.ExploartionValley.entity.*;
import no.uib.inf101.sem2.ExploartionValley.grid.GridCell;

/*
 *  This class represents the view for the Exploration Valley game. It extends JPanel and implements Runnable, 
 *  and is responsible for painting the game graphics on the screen. It creates a buffer image for rendering and
 *  a game thread for updating the game state and drawing the graphics at a fixed frame rate. The paintComponent
 *  method is called when the screen needs to be updated, and it draws the game graphics using the buffer image. 
 *  The run method runs the game loop, which regulates the frame rate using Thread.sleep(). The update method updates 
 *  the game state, and the drawGame and drawCell methods draw the game graphics.
 */


public class gameView extends JPanel implements Runnable{
    private TileDirectory ct;
    private ViewableGame model;
    private static final int OUTER_MARGIN = 0;
    private boolean isLoaded = false;  // Game only needs to be painted once
    private Image buffer;             // off-screen image
    private Graphics2D bufferGraphics;// graphics object for off-screen images
    private Thread gameThread;       
    public Dimension dim;
    public int tilesize;
    private int fps = 60;
    public int w = 1200;
    public int h = 800;
    public int npcDrawCounter = 0;
    gameController controller = new gameController();

    public player player = new player(this, controller);
    public item item = new item(this);
    public npc bat = new npc(this);


    /*
    * Constructs a new game view with the given ViewableGame model.
    * Sets the tile size to 48 and adds a key listener for the controller.
    * Sets the focusable property to true and the preferred size to w x h.
    * Initializes the color theme to DefaultColorTheme and sets the background color to the theme's background color.
    * @param model the ViewableGame model to be used
    */
    public gameView(ViewableGame model) {
        this.model = model;
        this.tilesize = 48; 
        this.tilesize = 48; //KOR STOR E VÅR TILESIZE
        this.addKeyListener(controller);
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(w, h));
        ct = new TileDirectory();
        this.setBackground(ct.getBackgroundColor());
    }

    @Override
    // public ettersom vi ønsker at JComponent kan implementere denne
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (buffer == null) {
            buffer = createImage(getWidth(), getHeight());
            bufferGraphics = (Graphics2D) buffer.getGraphics();
        }
        // Checks if the board is already loaded. Prevents overuse of rendering.
        if (!isLoaded) {
            drawGame(bufferGraphics);
            isLoaded = true;
        }
        g.drawImage(buffer, 0, 0, null);
        
        this.item.drawItem(g2); 
        this.player.draw(g2); // Paint the player
        this.bat.draw(g2, bat.getSpriteCounter());
        //this.item.drawItem(g2); 
        g2.dispose();
    }

    public void startGameThread() {
        gameThread = new Thread(this); // this = gameview
        gameThread.start();
    }


/**
 * This method runs the game loop, which updates the game state and repaints
 * the screen at a fixed frame rate per second. It uses the 'repaint()'
 * and 'update()' methods to draw the game, and the 'Thread.sleep()' method to
 * regulate the frame rate.
 *
 * @override The 'run()' method of the 'Thread' class.
 */
    @Override
    public void run() {
        double drawInterval = 1000000000 / fps;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {
            // Using sleep method to define a fps.
            try {
                repaint();
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
        }
    }


    /**
    * Updates the player and bat entities.
    * The bat entity is only updated every 5 calls to this method (to make it move slower).
    */
    public void update() {
        if(this.npcDrawCounter % 5 == 0){
            player.update();
            bat.update();
            //System.out.println("X: "+bat.getX()+", Y: "+bat.getY()); Bat tracking for testing purposes.
        } else{
            player.update();
        }
        this.npcDrawCounter++;
    }
    
    

    

    /**
    * Draws the game board, given a graphics context.
    * @param Graphics2D g2 The graphics context to use for drawing.
    */
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


    /**
    * Draws the cells on the game board by iterating over them and drawing each
    * cell with its respective image using 'CellPositionToPixelConverter' and 'DeafultColorTheme'
    * @param g2 object to use for drawing.
    * @param cells an Iterable of GridCell<Character> representing the cells on the board.
    * @param cp: CellPositionToPixelConverter object that converts cell positions to pixel coordinates on the screen.
    * @param ct: DefaultColorTheme object that provides the images for each cell value.
    */
    private void drawCell(Graphics2D g2, Iterable<GridCell<Character>> cells, CellPositionToPixelConverter cp,
            TileDirectory ct) {
        for (GridCell<Character> cell : cells) {
            Rectangle2D bounds = cp.getBoundsForCell(cell.pos());
            Image image = ct.getTileImage(cell.value());
            g2.drawImage(image, (int) bounds.getX(), (int) bounds.getY(), (int) bounds.getWidth(),
                    (int) bounds.getHeight(), null);
        }
    }
}