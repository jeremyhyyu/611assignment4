/*
 * HeroAttribute.java
 * By Heyang Yu (jhyyu@bu.edu)
 * This class is to manage hero attributes.
 */

public class HeroAttribute {
    // attributes
    private String name;
    private final String type;
    private int level;
    private int experience;
    private int hp;
    private int mp;
    private int strength;
    private int dexterity;
    private int defense;
    private int agility;
    private int gold;
    // battle related stat has curr/max
    private int currHp;
    private int currMp;
    private int currStrength;
    private int currDexterity;
    private int currDefense;
    private int currAgility;
    // constructor
    public HeroAttribute(String name, String type, int level, int experience, int mp, int strength, int dexterity, int defense, int agility, int gold) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.experience = experience;
        this.hp = level * 100;
        this.mp = mp;
        this.strength = strength;
        this.dexterity = dexterity;
        this.defense = defense;
        this.agility = agility;
        this.gold = gold;
        // battle related curr attri
        currHp = hp;
        currMp = mp;
        currStrength = strength;
        currDexterity = dexterity;
        currDefense = defense;
        currAgility = agility;
    }
    // getters
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public int getLevel() {
        return level;
    }
    public int getExperience() {
        return experience;
    }
    public int getHp() {
        return hp;
    }
    public int getMp() {
        return mp;
    }
    public int getStrength() {
        return strength;
    }
    public int getDexterity() {
        return dexterity;
    }
    public int getAgility() {
        return agility;
    }
    public int getGold() {
        return gold;
    }
    public int getDefense() {
        return defense;
    }
    public int getCurrHp() {
        return currHp;
    }
    public int getCurrMp() {
        return currMp;
    }
    public int getCurrStrength() {
        return currStrength;
    }
    public int getCurrDexterity() {
        return currDexterity;
    }
    public int getCurrDefense() {
        return currDefense;
    }
    public int getCurrAgility() {
        return currAgility;
    }
    // setters
    public void setLevel(int level) {
        this.level = level;
    }

    public void setExperience(int experience) {
        this.experience = experience;
        if(this.experience > level * 10) {
            setExperience(this.experience - level * 10);
            level++;
            // level up 
            levelUp();
            Color.print(Color.GREEN, name);
            System.out.println(" level up!");
        }
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
    // battle related curr attri
    public void setCurrHp(int currHp) {
        if(currHp > hp) {
            this.currHp = hp;
        } else {
            this.currHp = currHp;
        }
    }
    
    public void setCurrMp(int currMp) {
        if(currMp > mp) {
            this.currMp = mp;
        } else {
            this.currMp = currMp;
        }
    }
    
    public void setCurrStrength(int currStrength) {
        this.currStrength = currStrength;
    }
    
    public void setCurrDexterity(int currDexterity) {
        this.currDexterity = currDexterity;
    }
    
    public void setCurrDefense(int currDefense) {
        this.currDefense = currDefense;
    }
    
    public void setCurrAgility(int currAgility) {
        this.currAgility = currAgility;
    }
    // display attribute
    public void display(int index) {
        System.out.print(index + ": Name: ");
        Color.println(Color.GREEN, name);
        System.out.println("\tType: " + type);
        System.out.println("\tLevel: " + level);
        System.out.println("\tCurrent Experience: " + experience);
        // hp
        System.out.print("\tHP: ");
        Color.print(Color.GREEN, Integer.toString(currHp));
        System.out.println(" / " + hp);
        // mp
        System.out.print("\tMP: ");
        Color.print(Color.BLUE, Integer.toString(currMp));
        System.out.println(" / " + mp);

        System.out.println("\tStrength: " + currStrength);
        System.out.println("\tDexterity: " + currDexterity);
        System.out.println("\tAgility: " + currAgility);
        System.out.print("\tCurrent Gold: ");
        Color.println(Color.YELLOW, Integer.toString(gold));
    } 
    // level up
    public void levelUp() {
        hp = level * 100;
        currHp = hp;
        mp = (int)(mp * 1.1);
        currMp = mp;
        if(type.equalsIgnoreCase("Warrior")) {
            strength = (int)(strength * 1.1);
            dexterity = (int)(dexterity * 1.05);
            agility = (int)(agility * 1.1);
        }else if(type.equalsIgnoreCase("Sorcerer")) {
            strength = (int)(strength * 1.05);
            dexterity = (int)(dexterity * 1.1);
            agility = (int)(agility * 1.1);
        }else{
            strength = (int)(strength * 1.1);
            dexterity = (int)(dexterity * 1.1);
            agility = (int)(agility * 1.05);
        }
        currStrength = strength;
        currDexterity = dexterity;
        currAgility = agility;
    }

    // reset battle related attributes
    public void resetBattleRelatedAttributes() {
        currStrength = strength;
        currDexterity = dexterity;
        currAgility = agility;
    }
}
