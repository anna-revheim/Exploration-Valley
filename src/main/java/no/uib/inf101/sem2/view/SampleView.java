package no.uib.inf101.sem2.view;

// Denne klassen er bare et eksempel, og bør slettes når du begynner å
// komme i gang med ditt eget program.

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseAdapter;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * A sample view to get you inspired for your own project.
 */
public class SampleView extends JPanel {
  
  private boolean mouseIsInTheRectangle = false;
  private boolean mouseIsPressed = false;
  
  /** Construct a new SampleView */
  public SampleView() {
    this.setPreferredSize(new Dimension(400, 150));
    this.setupMousePositionUpdater();
    this.setupMousePressedUpdater();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    
    // Draw a centered rectangle with text
    Rectangle2D rect = this.getRectangle();
    Color color = mouseIsInTheRectangle ? (mouseIsPressed ? Color.RED : Color.BLUE) : Color.BLACK;
    g2.setColor(color);
    g2.draw(rect);
    Inf101Graphics.drawCenteredString(g2, "Semesteroppgave 2", rect);
    
    // Draw the INF101 logo in the left side of the rectangle
    BufferedImage logo = Inf101Graphics.loadImageFromResources("/inf101.png");
    double scale = (rect.getHeight() - 1)/logo.getHeight();
    Inf101Graphics.drawImage(g2, logo, rect.getX() + 1, rect.getY() + 1, scale);
  }

  private Rectangle2D getRectangle() {
    return new Rectangle2D.Double(50, 50, getWidth() - 100, getHeight() - 100);
  }

  private void setupMousePositionUpdater() {
    // Keep the mousePosition variable up to date
    this.addMouseMotionListener(new MouseMotionAdapter() {
      @Override
      public void mouseMoved(MouseEvent e) {
        mouseIsInTheRectangle = getRectangle().contains(e.getPoint());
        updateCursor();
        repaint();
      }
    });
  }

  private void updateCursor() {
    if (mouseIsInTheRectangle) {
      setCursor(new Cursor(Cursor.HAND_CURSOR));
    } else {
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
  }

  private void setupMousePressedUpdater() {
    this.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        mouseIsPressed = true;
        repaint();
      }
  
      @Override
      public void mouseReleased(MouseEvent e) {
        mouseIsPressed = false;
        repaint();
      }
    });
  }
}
