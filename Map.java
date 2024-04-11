/*
 * Generic map class that can be used in both the
 * heros and monsters game and Valor game
 */
public class Map {
    protected Grid[][] grids; // grids
    protected int numOfRows; // number of rows
    protected int numOfCols; // number of columns
    // set grid
    public void setGrid(int row, int col, Grid grid) {
        grids[row][col] = grid;
    }
    // get grid
    public Grid getGrid(int row, int col) {
        return grids[row][col];
    }
    public Grid[][] getGrids() {
        return grids;
    }
}
