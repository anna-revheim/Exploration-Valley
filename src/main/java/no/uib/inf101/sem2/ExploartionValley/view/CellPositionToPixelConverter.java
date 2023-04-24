package no.uib.inf101.sem2.ExploartionValley.view;

import no.uib.inf101.sem2.ExploartionValley.grid.CellPosition;
import no.uib.inf101.sem2.ExploartionValley.grid.GridDimension;
import java.awt.geom.Rectangle2D;

/*
*Converts cell positions to pixel coordinates for drawing on a given Rectangular area.
*/

public class CellPositionToPixelConverter {
    public Rectangle2D box;
    private GridDimension gd;
    private double margin;

    /**
    *Constructs a new CellPositionToPixelConverter with the given parameters.
    *@param box the rectangular area on which to draw the grid
    *@param gd the dimensions of the grid to be drawn
    *@param margin the margin between each cell
    */
    public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin){
        this.box = box;
        this.gd = gd;
        this.margin = 0.0;
    }

    /*
    * Returns the pixel coordinates for the given cell position.
    * @param pos the position of the cell to retrieve the pixel coordinates for
    * @return the rectangular area representing the pixel coordinates of the cell
    */
    public Rectangle2D getBoundsForCell(CellPosition pos){
            double cellWidth = ( box.getWidth() - (margin * (gd.cols() + 1 )))/ gd.cols();
            double cellHeight = ( box.getHeight() - (margin * (gd.rows() + 1)))/ gd.rows();
            double x = box.getX() + margin + (pos.col() * (cellWidth + margin));
            double y = box.getY() + margin + (pos.row() * (cellHeight + margin));
            Rectangle2D cellbox =  new Rectangle2D.Double(x,y,cellWidth,cellHeight);
            return cellbox;
    }
}

