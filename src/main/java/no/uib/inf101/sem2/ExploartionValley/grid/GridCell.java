package no.uib.inf101.grid;

public record GridCell<E>(CellPosition pos, E value) {
    //GridCell<E> er en klasse som holder på verdier. Den vil ha verdi CellPosition pos, og E value. Der Cellposition er gitt med (rows,cols).
}
