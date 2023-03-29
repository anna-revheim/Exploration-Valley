package no.uib.inf101.sem2.ExploartionValley.view;

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Dimension;

import no.uib.inf101.sem2.ExploartionValley.grid.GridCell;

public class TetrisView extends JPanel {
    private DefaultColorTheme ct;
    private ViewableTetrisModel model;

    private static final int OUTER_MARGIN = 1;

    // Konstruktør til TetrisView. Tar inn ViewableTetrisModel som paramter.
    // Ønsker public sa. klassen er tilgjengelig overalt.
    public TetrisView(ViewableTetrisModel model) {
        this.model = model;
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(1200, 800));

        ct = new DefaultColorTheme();
        this.setBackground(ct.getBackgroundColor());
    }

    @Override
    // public ettersom vi ønsker at JComponent kan implementere denne
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);
        drawGame(g2);
    }

    private void drawGame(Graphics2D g2) {
        double width = this.getWidth() - 2 * OUTER_MARGIN;
        double height = this.getHeight() - 2 * OUTER_MARGIN;
        Rectangle2D rektangel = new Rectangle2D.Double(OUTER_MARGIN, OUTER_MARGIN, width, height);
        g2.setColor(this.ct.getFrameColor());
        g2.fill(rektangel);
        CellPositionToPixelConverter cp = new CellPositionToPixelConverter(rektangel, model.getDimensions(),(double) 2);
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