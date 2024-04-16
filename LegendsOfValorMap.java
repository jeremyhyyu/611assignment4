/*
 * Legends of valor map has the functionality of the entire map
 * of the legends of valor map
 */
import java.util.*;

public class LegendsOfValorMap extends Map {
    // Constants to be used in this class
    private static final String UP = "UP";
    private static final String DOWN = "DOWN";
    private static final String RIGHT = "RIGHT";
    private static final String LEFT = "LEFT";
    public static final int MONSTER_NEXUS_ROW = 0;

    private List<HeroLegends> heros; // the list of heros on the map
    private List<Monster> monsters; // the list of monsters on the map
    private List<Market> markets; // the list of monsters on the map, one for each lane
    private List<List<Integer>> heroPositions; // positions of the heros on the map. pos[i] represents ith lane hero coordinates
    private List<List<Integer>> monsterPositions; // positions of the monsters on the map. It is list of monsters per lane. pos[i][j] is ith lane jth monster row number

    // constructor
    public LegendsOfValorMap(int rows, List<HeroLegends> hs){
        // initializing
        numOfRows = rows;
        numOfCols = 3*hs.size()-1;
        grids = new Grid[numOfRows][numOfCols];
        heros = new ArrayList<>();
        monsters = new ArrayList<>();
        markets = new ArrayList<>();
        heroPositions = new ArrayList<>();
        monsterPositions = new ArrayList<>();

        // populating the data
        createRandomMap();
        heros.addAll(hs);
        for (int i = 0; i < heros.size(); i++) {
            heroPositions.add(Arrays.asList(numOfRows-1,i*3));
            updateHeroGrid(i, true);
            monsterPositions.add(new ArrayList<>());
        }
        // add the markets
        for(int i = 0; i < hs.size(); i++) {
            markets.add(MarketFactory.generateMarket(rows - 1, i * 3));
        }
    }

    // hero enter a market
    public void enterMarket(int heroId) {
        Hero hero = heros.get(heroId);
        Market market = markets.get(heroPositions.get(heroId).get(1) / 3);
        market.tradingMenu(hero);
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

    // moving a hero. first validity should be checked only then this should be called
    public void moveHero(int heroInd, String direction){
        updateHeroGrid(heroInd, false);
        switch (direction) {
            case LEFT:
                heroPositions.get(heroInd).set(1, heroPositions.get(heroInd).get(1)-1);
                break;
            case RIGHT:
                heroPositions.get(heroInd).set(1, heroPositions.get(heroInd).get(1)+1);
                break;
            case UP:
                heroPositions.get(heroInd).set(0, heroPositions.get(heroInd).get(0)-1);
                break;
            case DOWN:
                heroPositions.get(heroInd).set(0, heroPositions.get(heroInd).get(0)+1);
                break;
            default:
                break;
        }
        updateHeroGrid(heroInd, true);
    }

    /**
     * adds a new monster that is created here in the given lane at the nexus.
     * lane is from 0..numOfLanes-1
     */
    public void addMonster(int lane, Monster monster){
        monsters.add(monster);
        monsterPositions.get(lane).add(0);
        updateMonsterGrid(lane, numOfMonstersInLane(lane)-1, true);
    }

    /**
     * Adds or removed the monster from certain tile.
     * addOrRemove is true for adding, false for removing
     */
    private void updateMonsterGrid(int lane, int monsterInLane, boolean addOrRemove){
        int row = getMonsterPosition(lane, monsterInLane).get(0);
        int col = getMonsterPosition(lane, monsterInLane).get(1);
        if (addOrRemove) {
            grids[row][col].addMonster();
        }
        else{
            grids[row][col].removeMonster();
        }
    }

    /**
     * Adds or removed the hero from certain tile.
     * addOrRemove is true for adding, false for removing.
     * Uses the observer pattern to let the hero know to update their abilities.
     */
    private void updateHeroGrid(int heroId, boolean addOrRemove){
        int row = getHeroPosition(heroId).get(0);
        int col = getHeroPosition(heroId).get(1);
        if (addOrRemove) {
            grids[row][col].addHero();
            ((TerrainObserver) heros.get(heroId)).update(grids[row][col]);
        }
        else{
            grids[row][col].removeHero();
        }
    }

    // moving a monster. first validity should be checked only then this should be called
    public void moveMonster(int lane, int monsterInLane){
        updateMonsterGrid(lane, monsterInLane, false);
        monsterPositions.get(lane).set(monsterInLane, monsterPositions.get(lane).get(monsterInLane)+1);
        updateMonsterGrid(lane, monsterInLane, true);
    }

    // get number of monsters in lane
    public int numOfMonstersInLane(int lane){
        return monsterPositions.get(lane).size();
    }

    // get all monsters in lane
    public List<Monster> getMonstersInLane(int lane){
        int indInMons = 0;
        for (int i = 0; i < lane; i++) {
            indInMons += numOfMonstersInLane(i);
        }
        return monsters.subList(indInMons, indInMons+numOfMonstersInLane(lane));
    }

    // get the monster of a lane and monster number
    public Monster getMonster(int lane, int monsterInLane){
        int indInMons = 0;
        for (int i = 0; i < lane; i++) {
            indInMons += numOfMonstersInLane(i);
        }
        return monsters.get(indInMons+monsterInLane);
    }

    /**
     * returns the lane the hero is in
     */
    public int getHeroLane(int heroId){
        return heroPositions.get(heroId).get(1)/3;
    }

    /**
     * returns the position of the hero in the form of list of two ints
     */
    public List<Integer> getHeroPosition(int heroId){
        return heroPositions.get(heroId);
    }

    // returns the position of monster in the form of list of two ints
    public List<Integer> getMonsterPosition(int lane, int monsterInLane){
        return Arrays.asList(monsterPositions.get(lane).get(monsterInLane), 3*lane+1);
    }

    /*
     * puts the hero to his nexus position. Can be used after hero gets killed or after
     * he goes back from teleportation or when he needs to go to market to buy something
     */
    public void resetHero(int heroId){
        updateHeroGrid(heroId, false);
        heroPositions.set(heroId, Arrays.asList(numOfRows-1, heroId*3));
        updateHeroGrid(heroId, true);
    }

    /**
     * removes monster in a lane given the monster number
     */
    public void removeMonster(int lane, int monsterInLane){
        updateMonsterGrid(lane, monsterInLane, false);
        Monster deadMonster = getMonster(lane, monsterInLane);
        monsters.remove(deadMonster);
        monsterPositions.get(lane).remove(monsterInLane);
        
    }

    // can the hero be movable in the given direction
    public boolean isHeroMovable(int heroId, String direction){
        // if crossing a monster
        if (direction == UP) {
            int lane = getHeroLane(heroId);
            if(numOfMonstersInLane(lane) >= 1 && getHeroPosition(heroId).get(0) == getMonsterPosition(lane, 0).get(0)){
                return false;
            }
        }

        // now hero shouldn't go to inaccessible tile and he shouldn't go to another hero tile
        int nextRow=getHeroPosition(heroId).get(0);
        int nextCol=getHeroPosition(heroId).get(1);

        switch (direction) {
            case LEFT: nextCol-=1; break;
            case RIGHT: nextCol+=1; break;
            case UP: nextRow-=1; break;
            case DOWN: nextRow+=1; break;
            default: break;
        }
        // if hero enters into inaccssible area
        if ((nextCol+1)%3==0 || nextRow<0 || nextRow >= numOfRows) {
            return false;
        }
        // if hero is entering into another hero position
        for (int i = 0; i < heros.size(); i++) {
            if (i == heroId) {
                continue;
            }
            if (heroPositions.get(i).equals(Arrays.asList(nextRow,nextCol))) {
                return false;
            }
        }

        // else hero is movable
        return true;
    }

    // can the monster of the given lane and given monster number move
    public boolean isMonsterMovable(int lane, int monsterInLane){
        // if crossing a hero
        for (int i = 0; i < heros.size(); i++) {
            int heroRow = getHeroPosition(i).get(0);
            int monsterRow = getMonsterPosition(lane, monsterInLane).get(0);
            if(getHeroLane(i) == lane && monsterRow == heroRow){
                return false;
            }
        }

        // if no monster ahead
        if (monsterInLane == 0) {
            return true;
        }

        // if monster ahead
        if (getMonsterPosition(lane, monsterInLane).get(0)+1==
            getMonsterPosition(lane, monsterInLane-1).get(0)) {
            return false;
        }

        return true;
    }

    // gets list of monsters which are in range of the current hero
    public List<Monster> getMonstersInRange(int heroId){
        List<Monster> result = new ArrayList<>();
        int lane = getHeroLane(heroId);
        for (int i = 0; i < numOfMonstersInLane(lane); i++) {
            if (Math.abs(getHeroPosition(heroId).get(0)-getMonsterPosition(lane, i).get(0))<=1) {
                result.add(getMonster(lane, i));
            }
        }
        return result;
    }

    // get list of heros which are in range of current monster
    public List<Hero> getHerosInRange(int lane, int monsterInLane){
        List<Hero> result = new ArrayList<>();
        for (int i = 0; i < heros.size(); i++) {
            int heroRow = getHeroPosition(i).get(0);
            int heroCol = getHeroPosition(i).get(1);
            int monsterRow = getMonsterPosition(lane, monsterInLane).get(0);
            int monsterCol = getMonsterPosition(lane, monsterInLane).get(1);
            if (Math.abs(heroRow-monsterRow)<=1 && Math.abs(heroCol-monsterCol)<=1) {
                result.add(heros.get(i));
            }
        }
        return result;
    }

    // returns true if there is a monster at the nexus of the given lane
    public boolean monsterAlreadyInNexus(int lane){
        for (int i = 0; i < monsterPositions.get(lane).size(); i++) {
            if (monsterPositions.get(lane).get(i) == 0) {
                return true;
            }
        }
        return false;
    }

    // get the heroids in a current lane
    private List<Integer> getHerosInLane(int lane){
        List<Integer> heroIds = new ArrayList<>();
        for (int i = 0; i < heros.size(); i++) {
            if(getHeroLane(i)==lane) heroIds.add(i);
        }
        return heroIds;
    }

    /**
     * get the hero id of the hero who is in front in the given lane
     */
    private int getHeroOnFrontInLane(int lane){
        int heroId = 0;
        int lowestRow = numOfRows;
        List<Integer> herosInLane = getHerosInLane(lane);
        for (Integer i: herosInLane) {
            if(getHeroPosition(i).get(0)<lowestRow){
                heroId = i;
                lowestRow = getHeroPosition(i).get(0);
            }
        }
        return heroId;
    }

    /**
     * returns if there is a hero already in a given coordinate
     */
    private boolean heroAlreadyInGivenCoords(int rowId, int colId){
        for (int i = 0; i < heros.size(); i++) {
            if (rowId == getHeroPosition(i).get(0) && colId == getHeroPosition(i).get(1)) {
                return true;
            }
        }
        return false;
    }

    // can hero be teleported to a lane. side to teleport is 0 or 1, 0->beside hero, 1->below hero
    public boolean canTeleport(int heroId, int lane, int sideToTeleport){
        // checks if the teleportation is valid from lanes
        // second condition is to make sure hero cannot teleport to his own lane
        if (!(getHeroLane(heroId)!=lane && heroId!=lane && getHerosInLane(lane).size()>=1)) return false;

        // get the coordinates of hero in the front, then teleport to his side or below
        int heroInFront = getHeroOnFrontInLane(lane);
        int rowOfFrontHero = getHeroPosition(heroInFront).get(0);
        int colOfFrontHero = getHeroPosition(heroInFront).get(1);

        if(sideToTeleport == 1 && rowOfFrontHero == numOfRows-1) return false; // if he is at nexus

        // setting where to teleport
        int rowToTeleport = rowOfFrontHero;
        int colToTeleport = colOfFrontHero;
        if (sideToTeleport == 1) {
            rowToTeleport += 1;
        }else{
            if (colOfFrontHero%3 == 0) {
                colToTeleport += 1;
            }else{
                colToTeleport -= 1;
            }
        }
        return !heroAlreadyInGivenCoords(rowToTeleport, colToTeleport);
    }

    // teleport a hero to another lane. This should only be called after checking the canTeleport
    public void teleport(int heroId, int lane, int sideToTeleport){
        // remove hero from current tile
        updateHeroGrid(heroId, false);

        // get the coordinates of hero in the front, then teleport to his side or below
        int heroInFront = getHeroOnFrontInLane(lane);
        int rowOfFrontHero = getHeroPosition(heroInFront).get(0);
        int colOfFrontHero = getHeroPosition(heroInFront).get(1);

        // setting where to teleport
        int rowToTeleport = rowOfFrontHero;
        int colToTeleport = colOfFrontHero;
        if (sideToTeleport == 1) {
            rowToTeleport += 1;
        }else{
            if (colOfFrontHero%3 == 0) {
                colToTeleport += 1;
            }else{
                colToTeleport -= 1;
            }
        }
        heroPositions.set(heroId, Arrays.asList(rowToTeleport, colToTeleport));

        // add hero to new tile
        updateHeroGrid(heroId, true);
    }

    // returns true if hero is in market and can buy stuff
    public boolean isHeroInMarket(int heroId){
        return getHeroPosition(heroId).get(0) == numOfRows-1;
    }

    // delete monster from the list if the hp of montser is lower or equal to 0, return the level of the monster deleted
    public int monsterRecyler() {
        int level = 0;
        int laneToRemoveMonsFrom = -1;
        int monsterNumToRemove = -1;
        for (int lane = 0; lane < monsterPositions.size(); lane++) {
            for (int monsterInLane = 0; monsterInLane < monsterPositions.get(lane).size(); monsterInLane++) {
                Monster monster = getMonster(lane, monsterInLane);
                if(monster.getHp() <= 0) {
                    System.out.print("Monster ");
                    Color.print(Color.RED, monster.getName());
                    System.out.println(" is defeated!");
                    level = monster.getLevel();
                    laneToRemoveMonsFrom = lane;
                    monsterNumToRemove = monsterInLane;
                }
            }
        }
        if (laneToRemoveMonsFrom != -1) {
            removeMonster(laneToRemoveMonsFrom, monsterNumToRemove);
        }
        return level;
    }

    // check winning condition
    public boolean checkWinningCondition() {
        for(List<Integer> heroPos: heroPositions) {
            if(heroPos.get(0) == MONSTER_NEXUS_ROW) return true;
        }
        return false;
    }

    // hero recyler, move the hero back to nexus and recover the status
    public void heroRecyler() {
        for(int i = 0; i < heros.size(); i++) {
            Hero hero = heros.get(i);
            if(hero.getAttribute().getCurrHp() <= 0) {
                resetHero(i);
                // recover the attributes
                hero.getAttribute().setCurrHp(hero.getAttribute().getHp());
                hero.getAttribute().setCurrMp(hero.getAttribute().getMp());
                hero.getAttribute().resetBattleRelatedAttributes();
            } 
        }
    }

    // checking losing condition
    public boolean checkLosingCondition() {
        for(List<Integer> monsterRowPosInALane: monsterPositions) {
            for(int pos: monsterRowPosInALane) {
                if(pos == numOfRows - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}