/*
 * Grid.java
 * By Heyang Yu(jhyyu@bu.edu)
 * This class is the super class for all kinds of grids
 */

public class Grid {
    // attributes
    private int row;
    private int col;
    // constructor
    public Grid(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public Grid(){
        this.row = 0;
        this.col = 0;
    }
    // getters
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
}
