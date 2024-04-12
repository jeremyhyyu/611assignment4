/*
 * Legends of valor map has the functionality of the entire map
 * of the legends of valor map
 */

public class LegendsOfValorMap extends Map {
    // constructor
    public LegendsOfValorMap(int rows, int numOfHeros){
        numOfRows = rows;
        numOfCols = 3*numOfHeros-1;
        grids = new Grid[numOfRows][numOfCols];
        createRandomMap();
    }
    // create random map with all the possible tiles
    private void createRandomMap(){
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfCols; j++) {
                if((j+1)%3==0){
                    grids[i][j] = GridFactory.get().getGrid(GridFactory.InaccessibleLegendsGrid, i, j);
                    continue;
                }
                else if(i==0){
                    grids[i][j] = GridFactory.get().getGrid(GridFactory.MonstersNexusGrid, i, j);
                    continue;
                }
                else if(i==numOfRows-1){
                    grids[i][j] = GridFactory.get().getGrid(GridFactory.HerosNexusGrid, i, j);
                    continue;
                }
                double curRandom = Math.random();
                if(curRandom < 0.2){
                    grids[i][j] = GridFactory.get().getGrid(GridFactory.CaveLegendsGrid, i, j);
                }
                else if (curRandom < 0.4) {
                    grids[i][j] = GridFactory.get().getGrid(GridFactory.KoulouLegendsGrid, i, j);
                }
                else if (curRandom < 0.6) {
                    grids[i][j] = GridFactory.get().getGrid(GridFactory.BushLegendsGrid, i, j);
                }
                else{
                    grids[i][j] = GridFactory.get().getGrid(GridFactory.PlainLegendsGrid, i, j);
                }
            }
        }
    }

    

}