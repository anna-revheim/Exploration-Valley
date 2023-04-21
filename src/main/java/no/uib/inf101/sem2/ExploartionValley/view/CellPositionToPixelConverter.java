package no.uib.inf101.sem2.ExploartionValley.view;

import no.uib.inf101.sem2.ExploartionValley.grid.CellPosition;
import no.uib.inf101.sem2.ExploartionValley.grid.GridDimension;
import java.awt.geom.Rectangle2D;

public class CellPositionToPixelConverter {
    public Rectangle2D box;
    private GridDimension gd;
    private double margin;

    public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin){
        this.box = box;
        this.gd = gd;
        this.margin = 0.0;
    }

    public Rectangle2D getBoundsForCell(CellPosition pos){
            double cellWidth = ( box.getWidth() - (margin * (gd.cols() + 1 )))/ gd.cols();
            double cellHeight = ( box.getHeight() - (margin * (gd.rows() + 1)))/ gd.rows();
            double x = box.getX() + margin + (pos.col() * (cellWidth + margin));
            double y = box.getY() + margin + (pos.row() * (cellHeight + margin));
            Rectangle2D cellbox =  new Rectangle2D.Double(x,y,cellWidth,cellHeight);
            return cellbox;
    }
}

