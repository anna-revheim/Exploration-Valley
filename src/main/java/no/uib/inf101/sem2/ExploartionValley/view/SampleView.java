package no.uib.inf101.sem2.ExploartionValley.view;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class SampleView extends JPanel {

  // Constructor
  public SampleView() {
    this.setFocusable(true);
    this.setPreferredSize(new Dimension(300, 400));
  }

  // The paintComponent method is called by the Java Swing framework every time
  // either the window opens or resizes, or we call .repaint() on this object.
  // Note: NEVER call paintComponent directly yourself
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    paintSample(g2);
  }

  private void paintSample(Graphics2D g2) {
    // A small rectangle (args: x, y of top-left corner, width, height)
    Rectangle2D rect1 = new Rectangle2D.Double(20, 20, 100, 50);
    g2.setColor(Color.BLACK);
    g2.fill(rect1);

    // A small rectangle further down
    Rectangle2D rect2 = new Rectangle2D.Double(20, 120, 100, 50);
    g2.setColor(Color.RED);
    g2.fill(rect2);

    // A small rectangle further to the right
    Rectangle2D rect3 = new Rectangle2D.Double(170, 20, 100, 50);
    g2.setColor(Color.YELLOW);
    g2.fill(rect3);

    // Draw a chess board that resize together with the window/component size
    Rectangle2D chessBox = new Rectangle2D.Double(
        20,
        this.getHeight() / 2,
        this.getWidth() - 40,
        this.getHeight() / 2 - 20);
    this.drawChess(g2, chessBox);

    // Using helper method from Inf101Graphics to draw centered text
    // over the resizing chess board
    g2.setColor(Color.LIGHT_GRAY);
    g2.setFont(new Font("Arial", Font.BOLD, 30));
    Inf101Graphics.drawCenteredString(g2, "Centered text", chessBox);
  }

  private void drawChess(Graphics2D g, Rectangle2D box) {
    double cellWidth = box.getWidth() / 8;
    double cellHeight = box.getHeight() / 8;

    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        // Here we paint the chess tile at position (row, col)
        // Pick the color
        Color c = (row + col) % 2 == 0 ? Color.BLACK : Color.WHITE;
        g.setColor(c);

        // Calculate the position of the tile and paint it
        double cellX = box.getX() + col * cellWidth;
        double cellY = box.getY() + row * cellHeight;
        Rectangle2D cellBox = new Rectangle2D.Double(cellX, cellY,
            cellWidth, cellHeight);
        g.fill(cellBox);
      }
    }
  }
}
