/*
 * MonstersAndHeroes.java
 * By Heyang Yu(jhyyu@bu.edu)
 * This class is the game monsters and heros, rules are defined in this file.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MonstersAndHeroes {
    // attributes
    public static final int MIN_SIDE_LENGTH = 5;
    public static final int MAX_SIDE_LENGTH = 9;
    public static final double MARKET_RATIO = 0.3;
    public static final double INACCESSIBLE_RATIO = 0.2;
    public static final int MIN_HERO = 1;
    public static final int MAX_HERO = 3;
    private Party party;
    private Map map;
    private List<Market> markets;
    private boolean partyInMarket;
    private GridMarket marketGridArch;
    // constructor
    public MonstersAndHeroes(int sideLength) {
        this.map = new Map(sideLength);
        party = new Party();
        markets = new ArrayList<>();
    }
    // getters
    public Party getParty() {
        return party;
    }
    public Map getMap() {
        return map;
    }
    public List<Market> getMarkets() {
        return markets;
    }
    public boolean getPartyInMarket() {
        return partyInMarket;
    }
    public GridMarket getMarketGridArch() {
        return marketGridArch;
    }
    // initialize markets
    public void initializeMarkets() {
        for(int i = 0; i < (int)(Math.pow(map.getSideLength(), 2) * MARKET_RATIO); i++) {
            // set all coords to be -1 before initialize the map
            markets.add(MarketFactory.generateMarket(-1, -1));
        }
    }
    // ask user to initialize the party
    public void initializeParty() {
        System.out.println("How many heros do you want in your party?");
        int numOfHeros = InputHandler.getAnIntegerInARange("number of heros", MIN_HERO, MAX_HERO);
        for(int i = 0; i < numOfHeros; i++) {
            Hero newHero = HeroFactory.selectAHero(i + 1);
            party.addHero(newHero);
        }
    }
    // refresh the map
    public void refreshMap() {
        map.initializeMap();
        int marketCnt = (int)(Math.pow(map.getSideLength(), 2) * MARKET_RATIO);
        int inaccessibleCnt = (int)(Math.pow(map.getSideLength(), 2) * INACCESSIBLE_RATIO);
        // get random coordinates
        List<String> coordinates = RandomGenerator.generateUniqueCoordinates(marketCnt + inaccessibleCnt + 1, map.getSideLength());
        int count = 0;
        for (String coord : coordinates) {
            String[] parts = coord.split(",");
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);

            if (count < marketCnt) {
                Market market = markets.get(count);
                market.setCoords(row, col);
                map.setGrid(row, col, new GridMarket(row, col, market));
            } else if (count < marketCnt + inaccessibleCnt) {
                map.setGrid(row, col, new GridInaccessible(row, col));
            } else {
                party.setCoords(row, col);
                map.setGrid(row, col, new GridParty(row, col, party));
            }
            count++;
        }
    }
    // first play, initialization needed
    public void firstPlay() {
        // initialize the markets
        HeroFactory.initialize();
        MonsterFactory.initialize();
        MarketFactory.initialize();
        // initialize the party and markets
        initializeParty();
        initializeMarkets();
        play();
    }
    // play a game
    public void play() {
        refreshMap();
        String[] directionSet = new String[]{"w", "W", "a", "A", "s", "S", "d", "D"};
        String[] validSet = new String[]{"w", "W", "a", "A", "s", "S", "d", "D", "i", "I", "h", "H", "r", "R", "m", "M", "q", "Q"};
        while(true) {
            map.displayMap();
            System.out.println("<wasd> to move, <i> for inventory, <h> for hero stats, <r> to refresh map, <m> to enter the market, <q> to quit");
            String userInput = InputHandler.getAValidChoiceString("Please input your choice: ", validSet);
            if(Arrays.asList(directionSet).contains(userInput)) {
                // move the party
                if(move(userInput)) {
                    // check the market archieve to see if the user move to a normal grid
                    if(marketGridArch == null) {
                        // roll the dice and create battle
                        if(RandomGenerator.checkProbability(0.2)) {
                            List<Hero> heroList = new ArrayList<>();
                            List<Monster> monsterList = new ArrayList<>();
                            for(Hero hero: party.getHeros()) {
                                heroList.add(hero);
                            }
                            for(int i = 0; i < heroList.size(); i++) {
                                monsterList.add(MonsterFactory.generateMonsterByHeroLevel(party.getHighestLevel()));
                            }
                            MonstersAndHeroesBattle battle = new MonstersAndHeroesBattle(monsterList, heroList);
                            battle.start();
                        }
                    }
                }
            }
            if(userInput.equalsIgnoreCase("i")) {
                // open the inventory for each hero and ask if user wants to change the weapon and armor or drink a potion
                for(Hero hero: party.getHeros()) {
                    hero.inventoryMenu();
                }
            }
            if(userInput.equalsIgnoreCase("h")) {
                // display the stats for each hero
                int index = 1;
                for(Hero hero: party.getHeros()) {
                    hero.getAttribute().display(index);
                    index++;
                }
            }
            if(userInput.equalsIgnoreCase("r")) {
                // refresh the map
                refreshMap();
                // clear the arch
                marketGridArch = null;
            }
            if(userInput.equalsIgnoreCase("m")) {
                // check and enter a market
                if(marketGridArch == null) {
                    Color.println(Color.RED, "You cannot access the store when you are not standing on a market grid!");
                }else{
                    // all heros in the party enter the market one by one
                    for(Hero hero: party.getHeros()) {
                        Color.println(Color.YELLOW, "Welcome " + hero.getAttribute().getName() + "!");
                        // trading actions
                        marketGridArch.getMarket().tradingMenu(hero);
                }
                }
            }
            if(userInput.equalsIgnoreCase("q")) {
                System.out.println("You quited the game");
                break;
            }
        }
    }
    // make a move, return true if successfully moved
    public boolean move(String dir) {
        int currRow = party.getRow();
        int currCol = party.getCol();
        int nextRow;
        int nextCol;
        // first compute the target next position
        if(dir.equalsIgnoreCase("w")) {
            nextRow = currRow - 1;
            nextCol = currCol;
        } else if(dir.equalsIgnoreCase("a")) {
            nextRow = currRow;
            nextCol = currCol - 1;
        } else if(dir.equalsIgnoreCase("s")) {
            nextRow = currRow + 1;
            nextCol = currCol;
        } else {
            nextRow = currRow;
            nextCol = currCol + 1;
        }
        // trying to move outside the map
        if(nextRow < 0 || nextRow > map.getSideLength() - 1 || nextCol < 0 || nextCol > map.getSideLength() - 1) {
            Color.println(Color.RED, "You cannot move outside the map!");
            return false;
        }else{
            if(map.getGrid(nextRow, nextCol) instanceof GridInaccessible) {
                // inaccessible grid
                Color.println(Color.RED, "Inaccessible grid!");
                return false;
            }else{
                // successfully moved!
                // first store the party grid here
                Grid partyGrid = map.getGrid(currRow, currCol);
                // check if party enter or leave a market
                // if leave a market, recover the previous market grid, else recover a normal grid
                if(partyInMarket){
                    map.setGrid(currRow, currCol, marketGridArch);
                    // clear the archieve
                    marketGridArch = null;
                }else{
                    map.setGrid(currRow, currCol, new GridNormal(currRow, currCol));
                }
                // then check the target position, if is a market grid, store it in the archieve
                if(map.getGrid(nextRow, nextCol) instanceof GridMarket) {
                    marketGridArch = (GridMarket)map.getGrid(nextRow, nextCol);
                    partyInMarket = true;
                }else{
                    partyInMarket = false;
                }
                // move the party to next grid
                map.setGrid(nextRow, nextCol, partyGrid);
                party.setCoords(nextRow, nextCol);
                return true;
            }
        }
    }
}
