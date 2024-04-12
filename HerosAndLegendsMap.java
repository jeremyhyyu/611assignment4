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
        this.numOfRows = sideLength;
        this.numOfCols = sideLength;
        grids = new Grid[sideLength][sideLength];
    }
    // getters
    public int getSideLength() {
        return sideLength;
    }
    // initialize map
    public void initializeMap() {
        for(int i = 0; i < sideLength; i++) {
            for(int j = 0; j < sideLength; j++) {
                grids[i][j] = new GridNormal(i, j);
            }
        }
    }
}
