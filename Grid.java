/*
 * Grid.java
 * By Heyang Yu(jhyyu@bu.edu)
 * This class is the super class for all kinds of grids
 */

public class Grid {
    // attributes
    protected int row;
    protected int col;
    protected boolean hasHero;
    protected boolean hasMonster;
    // constructor
    public Grid(int row, int col) {
        this.row = row;
        this.col = col;
        hasHero = false;
        hasMonster = false;
    }
    public Grid(){
        this.row = 0;
        this.col = 0;
        hasHero = false;
        hasMonster = false;
    }
    // getters
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    // used in printing the tile
    public String toString(){
        return (hasHero?"H":" ")+(hasMonster?"M":" ");
    }
    public void addHero(){
        hasHero = true;
    }
    public void addMonster(){
        hasMonster = true;
    }
    public void removeMonster(){
        hasMonster = false;
    }
    public void removeHero(){
        hasHero = false;
    }
}
