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
    // display map
    public void displayMap() {
        for (int i = 0; i < numOfRows; i++) {
            for(int j = 0; j < numOfCols; j++) {
                System.out.print("+");
                // top
                System.out.print("--");
            }
            System.out.print("+");
            System.out.println();
            for (int j = 0; j < numOfCols; j++) {
                // left for each grid
                System.out.print("|");
                // center
                if(grids[i][j] instanceof GridInaccessible) {
                    Color.print(Color.GREEN, "//");
                }else if (grids[i][j] instanceof GridMarket){
                    Color.print(Color.YELLOW, "M ");
                }else if (grids[i][j] instanceof GridParty){
                    Color.print(Color.BLUE, "H ");
                }else if (grids[i][j] instanceof InaccessibleLegendsGrid) {
                    System.out.print("  ");
                }else if (grids[i][j] instanceof MonstersNexusGrid) {
                    Color.print(Color.RED_BACKGROUND, grids[i][j].toString());
                }else if (grids[i][j] instanceof HerosNexusGrid) {
                    Color.print(Color.PURPLE_BACKGROUND, grids[i][j].toString());
                }else if (grids[i][j] instanceof CaveLegendsGrid) {
                    Color.print(Color.YELLOW_BACKGROUND, grids[i][j].toString());
                }else if (grids[i][j] instanceof KoulouLegendsGrid) {
                    Color.print(Color.GREEN_BACKGROUND, grids[i][j].toString());
                }else if (grids[i][j] instanceof BushLegendsGrid) {
                    Color.print(Color.BLUE_BACKGROUND, grids[i][j].toString());
                }else if (grids[i][j] instanceof PlainLegendsGrid) {
                    Color.print(Color.CYAN_BACKGROUND, grids[i][j].toString());
                }else {
                    System.out.print("  ");
                }
            }
            // right edge for last grid of each row
            System.out.print("|");
            System.out.println();
        }
        for(int j = 0; j < numOfCols; j++) {
            System.out.print("+");
            // bottom
            System.out.print("--");
        }
        System.out.print("+");
        System.out.println();
    }
}
