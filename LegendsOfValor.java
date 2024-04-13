import java.util.Arrays;
import java.util.List;

public class LegendsOfValor extends RPGGame {
    // class variables
    public static final int INTERVAL_TO_GENERATE_MONSTERS = 10; // new monsters are generated every 10 rounds
    public static final int NUM_OF_HEROS = 3;
    public static final int NUM_OF_ROWS = 8;
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
        for(int i = 0; i < NUM_OF_HEROS; i++) {
            Hero newHero = HeroFactory.selectAHero(i + 1);
            party.addHero(newHero);
        }

        // initialize the game board
        map = new LegendsOfValorMap(NUM_OF_ROWS, party.getHeros());

        // generate the fist wave of monsters
        generateMonsters();
    }

    // generate monsters, call this method every 10 rounds
    public void generateMonsters() {
        for(int i = 0; i < party.getHeros().size(); i++) {
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
        // valid sets to for user input
        String[] directionSet = new String[]{"w", "W", "a", "A", "s", "S", "d", "D"};
        String[] validSet = new String[]{"w", "W", "a", "A", "s", "S", "d", "D", "c", "C", "p", "P", "j", "J", "k", "K", "t", "T", "r", "R", "q", "Q"};
        // ask each hero to make an input
        for(Hero hero: party.getHeros()) {
            int heroId = party.getHeros().indexOf(hero);
            while(true) {
                map.displayMap();
                System.out.println("<wasd> to move, <c> for change weapon or armor, <p> for consume potion, <j> to attack, <k> to cast a spell, <t> to teleport, <r> to recall, <q> to quit");
                String userInput = InputHandler.getAValidChoiceString("Your choice is: ", validSet);

                // move, if successfully moved, switch the turn to next hero or monster
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
                        break;
                    }
                }
                
                // change weapon or armor, if successfully changed, switch the turn to next character
                if(userInput.equalsIgnoreCase("c")) {
                    if(hero.equipmentMenu()) break;
                }

                // consume a potion, if successfully consumed, switch turn to next character
                if(userInput.equalsIgnoreCase("p")) {
                    if(hero.potionMenu()) break;
                }

                // first check if there are any monster in the attack range, then if successfully attacked, switch turn to next character
                if(userInput.equalsIgnoreCase("j")) {
                    List<Monster> monstersInRange = map.getMonstersInRange(party.getHeros().indexOf(hero));
                    if(monstersInRange.size() != 0) {
                        Monster target = selectATargetMonster(monstersInRange);
                        heroAttackMonster(hero, target);
                        break;
                    }else{
                        Color.println(Color.RED, "There's no monsters in your attack range!");
                    }
                }

                // cast a spell

                // teleport

                // recall

                // quit
            }
        }
    }

    // select a target monster
    public Monster selectATargetMonster(List<Monster> monsters) {
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
    public void heroAttackMonster(Hero hero, Monster monster) {
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
}
