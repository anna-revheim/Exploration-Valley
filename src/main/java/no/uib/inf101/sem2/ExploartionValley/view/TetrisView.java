package no.uib.inf101.tetris.view;

import javax.swing.JPanel;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.tetris.model.GameState;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Dimension;

public class TetrisView extends JPanel {
    private DefaultColorTheme ct;
    private ViewableTetrisModel model;

    private static final int OUTER_MARGIN = 30;

    // Konstruktør til TetrisView. Tar inn ViewableTetrisModel som paramter.
    //Ønsker public sa. klassen er tilgjengelig overalt.
    public TetrisView(ViewableTetrisModel model) {
        this.model = model;
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(350, 600));

        ct = new DefaultColorTheme();
        this.setBackground(ct.getBackgroundColor());
    }

    private void drawGame(Graphics2D g2d) {
        Graphics2D g2 = (Graphics2D) g2d;
        double width = this.getWidth() - 2 * OUTER_MARGIN;
        double height = this.getHeight() - 2 * OUTER_MARGIN;
        Rectangle2D rektangel = new Rectangle2D.Double(OUTER_MARGIN, OUTER_MARGIN, width, height);
        g2.setColor(this.ct.getFrameColor());
        g2.fill(rektangel);
        CellPositionToPixelConverter cp = new CellPositionToPixelConverter(rektangel, model.getDimension(), (double) 2);
        drawCells(g2, model.getTilesOnBoard(), cp, this.ct);
        drawCells(g2, model.revealPieces(), cp, this.ct);
        gameOver(g2, rektangel);
    }

    @Override
    //public ettersom vi ønsker at JComponent kan implementere denne
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawGame(g2);
    }

    private static void drawCells(Graphics2D g, Iterable<GridCell<Character>> cells, CellPositionToPixelConverter cp,
            DefaultColorTheme ct) {
        // Hensikt: å tegne en samling med ruter, f.eks. rutene på tetrisbrettet
        for (GridCell<Character> cell : cells) {
            Rectangle2D cellPos = cp.getBoundsForCell(cell.pos());
            Color color = ct.getCellColor(cell.value());
            g.setColor(color);
            g.fill(cellPos);
        }
    }

    private void gameOver(Graphics2D g2, Rectangle2D rektangel) {
        if (model.getGameState() == GameState.GAME_OVER) {
            g2.setColor(this.ct.GameOverColor());
            g2.fill(rektangel);
            g2.setColor(this.ct.GameOverTextColor());
            Inf101Graphics.drawCenteredString(g2, "GAME OVER", rektangel);
        }
    }
}