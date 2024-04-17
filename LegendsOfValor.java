/*
 * This is the class for the game legends of valor, the rule of the game and the in-game operations are defined in this class.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LegendsOfValor extends RPGGame {
    // class variables
    public static final int INTERVAL_TO_GENERATE_MONSTERS = 8; // new monsters are generated every 6 rounds
    public static final int NUM_OF_HEROS = 3;
    public static final int NUM_OF_ROWS = 8;
    public static final int MONEY_REWARD_RATIO = 500;
    public static final int EXP_REWARD_RATIO = 2;
    public static final double RECOVERY_RATE = 0.1;
    // attributes
    private PartyLegends party;
    private LegendsOfValorMap map;
    // constructor
    public LegendsOfValor() {
        party = new PartyLegends();
    }
    // getters
    public PartyLegends getPartyLegends() {
        return party;
    }
    public LegendsOfValorMap getMap() {
        return map;
    }
    // methods
    // initialization
    public void initialize() {
        HeroFactory.initialize();
        MonsterFactory.initialize();
        MarketFactory.initialize();

        // initialize the heros
        List<HeroLegends> heroList = new ArrayList<>();
        for(int i = 0; i < NUM_OF_HEROS; i++) {
            Hero newHero = HeroFactory.selectAHeroLegends(i + 1);
            party.addHero(newHero);
            heroList.add((HeroLegends)newHero);
        }

        // initialize the game board
        map = new LegendsOfValorMap(NUM_OF_ROWS, heroList);

        // generate the fist wave of monsters
        generateMonsters();
    }

    // generate monsters, call this method every n rounds
    public void generateMonsters() {
        for(int i = 0; i < party.getHeros().size(); i++) {
            if (map.monsterAlreadyInNexus(i)) continue;
            map.addMonster(i, MonsterFactory.generateMonsterByHeroLevel(party.getHighestLevel()));
        }
    }

    // first game, initialization needed
    public void firstPlay() {
        initialize();
        play();
    }

    // play a lengends of valor game
    public void play() {
        int roundCnt = 0;
        while(true) {
            // ask each hero to make an input
            for(Hero hero: party.getHeros()) {
                // terminate the game if hero choose quit
                if(askHeroForAnInput(hero)) return;
                
                // check if any monsters is defeated
                int levelOfMonster = map.monsterRecyler();
                if(levelOfMonster > 0) {
                    // this means that the previous action killed a monster, give all heroes rewards
                    getRewards(levelOfMonster);
                }

                // check winning condition
                if(map.checkWinningCondition()) {
                    Color.println(Color.GREEN, "Congratulations! Hero team wins!");
                    // terminate the game
                    return;
                }
            }

            // monster actions
            for(int i = 0; i < NUM_OF_HEROS; i++) {
                monsterManagement(i);

                // check hero status, move the hero back to the nexus if hero is defeated
                map.heroRecyler();

                // check winning condition for monster team
                if(map.checkLosingCondition()) {
                    Color.println(Color.RED, "Hero team losed the game!");
                    // terminate the game
                    return;
                }
            }

            // all heroes regain hp and mp
            regainHpMp();
            
            // generate new monsters every few rounds
            roundCnt++;
            if(roundCnt % INTERVAL_TO_GENERATE_MONSTERS == 0) generateMonsters();
        }
    }

    // ask a hero to make an input, return true if user select quit
    public boolean askHeroForAnInput(Hero hero) {
        // valid sets to for user input
        String[] directionSet = new String[]{"w", "W", "a", "A", "s", "S", "d", "D"};
        String[] validSet = new String[]{"w", "W", "a", "A", "s", "S", "d", "D", "c", "C", "p", "P", "j", "J", "k", "K", "t", "T", "r", "R", "q", "Q", "m", "M", "h", "H", "g", "G"};
        int heroId = party.getHeros().indexOf(hero);
        while(true) {
            map.displayMap();
            System.out.print("Hero ");
            Color.print(Color.GREEN, hero.getAttribute().getName());
            System.out.println("'s turn!");
            System.out.println("<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <m> for shopping, <g> for game info, <h> for hero stats, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit");
            String userInput = InputHandler.getAValidChoiceString("Your choice is: ", validSet);

            // move, if successfully moved, return false
            if(Arrays.asList(directionSet).contains(userInput)) {
                // first convert the input into direction string
                String dir = "";
                if(userInput.equalsIgnoreCase("w")) dir = "UP";
                if(userInput.equalsIgnoreCase("s")) dir = "DOWN";
                if(userInput.equalsIgnoreCase("a")) dir = "LEFT";
                if(userInput.equalsIgnoreCase("d")) dir = "RIGHT";
                // check if hero is movable in this direction
                if(map.isHeroMovable(heroId, dir)) {
                    map.moveHero(heroId, dir);
                    return false;
                }else{
                    Color.println(Color.RED, "Invalid move!");
                }
            }

            // change weapon or armor, if successfully changed, return false
            if(userInput.equalsIgnoreCase("c")) {
                if(hero.equipmentMenu()) return false;
            }

            // consume a potion, if successfully consumed, switch turn to next character
            if(userInput.equalsIgnoreCase("p")) {
                if(hero.potionMenu()) return false;
            }

            // if hero is in the nexus, then hero can enter the store
            if(userInput.equalsIgnoreCase("m")) {
                // first check if user is on the nexus grid
                if(!map.isHeroInMarket(heroId)) {
                    Color.println(Color.RED, "You can only enter the market when you are in nexus!");
                }else{
                    // enter the market
                    map.enterMarket(heroId);
                }
            }

            // show the game info, rules of the game, color of grids...
            if(userInput.equalsIgnoreCase("g")) {
                System.out.println("In this game, your goal is reaching the monsters' nexus and prevent your nexus from being accessed by monsters.");
                System.out.println("Different colors represent different terrian: ");
                Color.print(Color.RED, "\tred - ");
                System.out.println("Monster nexus, where monsters are generated");
                Color.print(Color.PURPLE, "\tpurple - ");
                System.out.println("Hero nexus, where hero can access the market and reborn");
                Color.print(Color.YELLOW, "\tyellow - ");
                System.out.println("Cave, hero have a buff of 10% agility on these grids");
                Color.print(Color.GREEN, "\tgreen - ");
                System.out.println("Koulou, hero have a buff of 10% strength on these grids");
                Color.print(Color.BLUE, "\tblue - ");
                System.out.println("Bush, hero have a buff of 10% dexterity on these grids");
                Color.print(Color.CYAN, "\tcyan - ");
                System.out.println("normal grid");
                Color.print(Color.BLACK, "\tblack - ");
                System.out.println("inaccessible grid");
            }

            // check the stats of hero
            if(userInput.equalsIgnoreCase("h")) {
                hero.getAttribute().display(heroId + 1);
            }

            // first check if there are any monster in the attack range, then if successfully attacked, return false
            if(userInput.equalsIgnoreCase("j")) {
                List<Monster> monstersInRange = map.getMonstersInRange(party.getHeros().indexOf(hero));
                if(!monstersInRange.isEmpty()) {
                    // check if hero equiped a weapon, hero cannot attack if not equiped a weapon
                    if(hero.getWeapon() == null) {
                        Color.println(Color.RED, "You cannot attack a monster without a weapon!");
                    }else{
                        // attack
                        Monster target = selectATargetMonster(monstersInRange);
                        heroAttackMonster(hero, target);
                        return false;
                    }
                }else{
                    Color.println(Color.RED, "There's no monsters in your attack range!");
                }
            }

            // cast a spell
            if(userInput.equalsIgnoreCase("k")) {
                List<Monster> monstersInRange = map.getMonstersInRange(party.getHeros().indexOf(hero));
                // if no montsers in range
                if(monstersInRange.isEmpty()) {
                    Color.println(Color.RED, "Thers's no monsters in your range!");
                }else{
                    // if successfully casted a spell return false
                    if(hero.spellMenu(monstersInRange)) return false;
                }
            }

            // teleport
            if (userInput.equalsIgnoreCase("t")) {
                int lane = selectLaneToTeleport();
                int side = selectSideToTeleportTo();
                if (!map.canTeleport(heroId, lane, side)) {
                    Color.println(Color.RED, "Invalid teleportation! (use recall to go back)");
                }else{
                    map.teleport(heroId, lane, side);
                    return false;
                }
            }

            // recall
            if(userInput.equalsIgnoreCase("r")) {
                map.resetHero(heroId);
                return false;
            }

            // quit
            if(userInput.equalsIgnoreCase("q")) {
                System.out.println("You quited the game!");
                return true;
            }
        }
    }

    /**
     * takes input to select lane to teleport to
     */
    private int selectLaneToTeleport(){
        int lane = InputHandler.getAnIntegerInARange("lane(left to right)", 1, NUM_OF_HEROS);
        lane -= 1;
        return lane;
    }

    /**
     * select a side to where the current hero should teleport to in the given lane
     */
    private int selectSideToTeleportTo(){
        int side = InputHandler.getAnIntegerInARange("side(0 to teleport beside hero, 1 to teleport behind hero)", 0, 1);
        return side;
    }

    // select a target monster
    private Monster selectATargetMonster(List<Monster> monsters) {
        // first display all the monsters in the range
        for(int i = 0; i < monsters.size(); i++) {
            monsters.get(i).displayInfo(i + 1);
        }
        // then get the user selection
        int userSelection = InputHandler.getAnIntegerInARange("target", 1, monsters.size());
        // return the corresponding monster
        return monsters.get(userSelection - 1);
    }

    // hero attack a monster
    private void heroAttackMonster(Hero hero, Monster monster) {
        // first roll the dice
        if(RandomGenerator.checkProbability(monster.getDodge() * Monster.DODGE_RATIO)) {
            System.out.print("Oops! ");
            Color.print(Color.RED, monster.getName());
            System.out.println(" dodged!");
            return;
        }

        // if not dodged
        // first calculate the damage
        int damage = 0;
        if(hero.getWeapon() != null) {
            damage = (hero.getAttribute().getCurrStrength() + hero.getWeapon().getDamage()) / MonstersAndHeroesBattle.HERO_DAMAGE_RATIO - monster.getDefense() / MonstersAndHeroesBattle.MONSTER_DAMAGE_REDUCTION_BY_DEFENSE;
        }else{
            damage = hero.getAttribute().getCurrStrength() / MonstersAndHeroesBattle.HERO_DAMAGE_RATIO - monster.getDefense() / MonstersAndHeroesBattle.MONSTER_DAMAGE_REDUCTION_BY_DEFENSE;
        }
        if(damage < 0) damage = 0;
        // then deal the damage to monster
        Color.print(Color.GREEN, hero.getAttribute().getName());
        System.out.print(" deals " + damage + " damage to ");
        Color.println(Color.RED, monster.getName());
        monster.setHp(monster.getHp() - damage);
    }

    // get the money and experience rewards for all heroes
    private void getRewards(int monsterLevel) {
        int moneyReward = monsterLevel * MONEY_REWARD_RATIO;
        int expReward = monsterLevel * EXP_REWARD_RATIO;

        // all heroes get the same reward
        for(Hero hero: party.getHeros()) {
            hero.getAttribute().setExperience(hero.getAttribute().getExperience() + expReward);
            hero.getAttribute().setGold(hero.getAttribute().getGold() + moneyReward);
        }
    }

    // manage the action of all monsters in a lane
    private void monsterManagement(int laneId) {
        // first get all monsters in this lane
        List<Monster> monsters = map.getMonstersInLane(laneId);
        // then decide the action for each monster in this lane
        for(int i = 0; i < monsters.size(); i++) {
            Monster monster = monsters.get(i);
            // first goal is to reach the hero's nexus, so move forward has the highest priorty
            if(map.isMonsterMovable(laneId, i)) {
                map.moveMonster(laneId, i);
            }else{
                // which means block by a hero or monster
                // check if there are any hero in range
                List<Hero> herosInRange = map.getHerosInRange(laneId, i);
                // if the list is not empty, attack random hero
                if(!herosInRange.isEmpty()) {
                    int target = RandomGenerator.randInt(1, herosInRange.size());
                    monster.attack(herosInRange.get(target - 1));
                }
            }
        }
    }

    // regain hp and mp for heroes
    private void regainHpMp() {
        for(Hero hero: party.getHeros()) {
            hero.getAttribute().setCurrHp(hero.getAttribute().getCurrHp() + (int)(hero.getAttribute().getHp() * RECOVERY_RATE));
            hero.getAttribute().setCurrMp(hero.getAttribute().getCurrMp() + (int)(hero.getAttribute().getMp() * RECOVERY_RATE));
        }
    }
}
