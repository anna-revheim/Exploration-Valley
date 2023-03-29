package no.uib.inf101.sem2.ExploartionValley.view;

import no.uib.inf101.sem2.ExploartionValley.grid.CellPosition;
import no.uib.inf101.sem2.ExploartionValley.grid.GridDimension;
import java.awt.geom.Rectangle2D;

public class CellPositionToPixelConverter {
    private Rectangle2D box;
    private GridDimension gd;
    private Double margin;

    // Constructor
    public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, Double margin) {
        this.box = box;
        this.gd = gd;
        this.margin = margin;
    }

    public Rectangle2D getBoundsForCell(CellPosition cp) {
        double cellWidth = (box.getWidth() - ((gd.cols() + 1) * margin)) / gd.cols();
        double cellHeight = (box.getHeight() - ((gd.rows() + 1) * margin)) / gd.rows();
        double x = box.getX() + ((cp.col() + 1) * margin) + (cp.col() * cellWidth);
        double y = box.getY() + ((cp.row() + 1) * margin) + (cp.row() * cellHeight);
        Rectangle2D cell = new Rectangle2D.Double(x, y, cellWidth, cellHeight);
        return cell;
    }
}
