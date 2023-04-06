package no.uib.inf101.sem2.ExploartionValley.grid;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;

public class Grid<E> implements IGrid<E> {
    // Grid er en klasse som inneholder en generisk type E og implementerer
    // interfacet IGRID.

    private ArrayList<ArrayList<E>> grid;
    private Image image;

    // Konstruktør dersom konstruktør blir talkalt med 2 parameter,.
    public Grid(int cols, int rows) {
        this.grid = new ArrayList<>();

        for (int i = 0; i < cols; i++) {
            grid.add(new ArrayList<>());
            for (int j = 0; j < rows; j++) {
                grid.get(i).add(null);
            }
        }
    }

    // Konstruktør dersom konstruktør blir talkalt med 3 parameter
    public Grid(int cols, int rows, E value) {
        this.grid = new ArrayList<>();

        for (int i = 0; i < cols; i++) {
            grid.add(new ArrayList<>());
            for (int j = 0; j < rows; j++) {
                grid.get(i).add(value);
            }
        }
    }

    public void setImage(Image image){
        this.image = image;
    }

    @Override
    public int rows() {
        // Henter menger rader til grid
        return this.grid.size();

    }

    @Override
    public int cols() {
        // Henter mengen kolonner til grid
        return this.grid.get(0).size();
    }

    @Override
    public void set(CellPosition pos, E value) {
        if (positionIsOnGrid(pos)) {
            grid.get(pos.row()).set(pos.col(), value);
        } else {
            // handle error or throw exception
        }
    }
    
    @Override
    public E get(CellPosition pos) {
        if (pos.row() < 0 || pos.row() >= this.rows() || pos.col() < 0 || pos.col() >= this.cols()) {
            throw new IndexOutOfBoundsException("Position " + pos + " is out of bounds");
        }
        return grid.get(pos.row()).get(pos.col());
    }

    @Override
    public boolean positionIsOnGrid(CellPosition pos) {
        // Sjekker om posisjonen er on grid.
        if (pos.col() >= this.cols() || (pos.row() >= this.rows()))
            return false;
        if ((pos.row() < 0) || (pos.col() < 0))
            return false;
        else
            return true;
    }

    @Override
    public Iterator<GridCell<E>> iterator() {
        // Metode til objektet som lager nye iteratorobjekt. Kan bla gjennom objektet
        // flere ganger.
        ArrayList<GridCell<E>> gridcell = new ArrayList<>();
        int colSize = cols();
        int rowSize = rows();
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                CellPosition pos = new CellPosition(i, j);
                gridcell.add(new GridCell<E>(pos, get(pos), image));
            }
        }
        return gridcell.iterator();
    }

    @Override
    public Image image() {
        return image;  
    }

}
