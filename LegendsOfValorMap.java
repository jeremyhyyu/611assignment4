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

    // can hero be teleported to a lane
    public boolean canTeleport(int heroId, int lane){
        // second condition is to make sure hero is currently in his lane
        return getHeroLane(heroId)!=lane && getHeroPosition(heroId).get(1)%3==heroId;
    }

    // teleport a hero to another lane. This should only be called after checking the canTeleport
    public void teleport(int heroId, int lane){
        int numHerosInTheLane = 0;
        int otherHeroRow = 0;
        for (int i = 0; i < heros.size(); i++) {
            if(i==heroId) continue;
            numHerosInTheLane += getHeroLane(i)==lane?1:0;
            if (getHeroLane(i)==lane) otherHeroRow = getHeroPosition(i).get(0);
        }

        // remove hero from current tile
        updateHeroGrid(heroId, false);

        // no one in new lane
        if (numHerosInTheLane==0) {
            heroPositions.set(heroId, Arrays.asList(getHeroPosition(heroId).get(0),3*lane));
        }

        // one hero already in lane
        if (numHerosInTheLane==1) {
            heroPositions.set(heroId, Arrays.asList(otherHeroRow, 3*lane+1));
        }

        // both heros already in lane
        if (numHerosInTheLane == 2) {
            if (otherHeroRow+1>=numOfRows) {
                return;
            }
            heroPositions.set(heroId, Arrays.asList(otherHeroRow+1, 3*lane));
        }

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