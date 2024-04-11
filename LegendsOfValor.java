public class LegendsOfValor extends RPGGame {
    // class variables
    public static final int INTERVAL_TO_GENERATE_MONSTERS = 10; // new monsters are generated every 10 rounds
    public static final int NUM_OF_HEROS = 3;
    // attributes
    private PartyLegends party;
    // constructor
    public LegendsOfValor() {
        party = new PartyLegends();
    }
    // getters
    public PartyLegends getPartyLegends() {
        return party;
    }
    // methods
    // initialization
    public void initialize() {
        HeroFactory.initialize();
        MonsterFactory.initialize();
        MarketFactory.initialize();
        // initialize the game board


        // initialize the heros
        for(int i = 0; i < NUM_OF_HEROS; i++) {
            Hero newHero = HeroFactory.selectAHero(i + 1);
            party.addHero(newHero);
        }
        // generate the fist wave of monsters

    }

    // generate monsters, call this method every 10 rounds
    public void generateMonsters() {

    }

    // first game, initialization needed
    public void firstPlay() {
        initialize();
        play();
    }

    // play a lengends of valor game
    public void play() {
        // ask each hero to make an input
        
    }
}
