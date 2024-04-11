/*
 * HerosAndLegendsMap.java
 * By Heyang Yu(jhyyu@bu.edu)
 * This is the map of the game, different kinds of grids are stored in 2d array.
 */

public class HerosAndLegendsMap extends Map {
    // attributes
    private int sideLength;

    //constructor
    public HerosAndLegendsMap(int sideLength) {
        this.sideLength = sideLength;
        grids = new Grid[sideLength][sideLength];
    }
    // getters
    public int getSideLength() {
        return sideLength;
    }
    public Grid[][] getGrids() {
        return grids;
    }
    // initialize map
    public void initializeMap() {
        for(int i = 0; i < sideLength; i++) {
            for(int j = 0; j < sideLength; j++) {
                grids[i][j] = new GridNormal(i, j);
            }
        }
    }
    // set grid
    public void setGrid(int row, int col, Grid grid) {
        grids[row][col] = grid;
    }
    // get grid
    public Grid getGrid(int row, int col) {
        return grids[row][col];
    }
    // display map
    public void displayMap() {
        for (int i = 0; i < sideLength; i++) {
            for(int j = 0; j < sideLength; j++) {
                System.out.print("+");
                // top
                System.out.print("--");
            }
            System.out.print("+");
            System.out.println();
            for (int j = 0; j < sideLength; j++) {
                // left for each grid
                System.out.print("|");
                // center
                if(grids[i][j] instanceof GridInaccessible) {
                    Color.print(Color.GREEN, "//");
                }else if (grids[i][j] instanceof GridMarket){
                    Color.print(Color.YELLOW, "M ");
                }else if (grids[i][j] instanceof GridParty){
                    Color.print(Color.BLUE, "H ");
                }else {
                    System.out.print("  ");
                }
            }
            // right edge for last grid of each row
            System.out.print("|");
            System.out.println();
        }
        for(int j = 0; j < sideLength; j++) {
            System.out.print("+");
            // bottom
            System.out.print("--");
        }
        System.out.print("+");
        System.out.println();
    }
}
