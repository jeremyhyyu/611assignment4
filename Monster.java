/*
 * Monster.java
 * By Heyang Yu(jhyyu@bu.edu)
 * This is the class for monsters, manage the hero attributes and provide some necessary methods.
 */

public class Monster {
    // class attributes
    public static final double DODGE_RATIO = 0.01;
    public static final int DAMAGE_REDUCTION = 10;
    // attributes
    private String name;
    private String type;
    private int level;
    private int hp;
    private int damage;
    private int defense;
    private int dodge;
    // constructor
    public Monster(String name, String type, int level, int damage, int defense, int dodge) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.hp = level * 100;
        this.damage = damage;
        this.defense = defense;
        this.dodge = dodge;
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
    public int getHp() {
        return hp;
    }
    public int getDamage() {
        return damage;
    }
    public int getDefense() {
        return defense;
    }
    public int getDodge() {
        return dodge;
    }
    // setters
    public void setDamage(int damage) {
        if(damage <= 0) this.damage = 0;
        else this.damage = damage;
    }
    public void setDefense(int defense) {
        if(defense <= 0) this.defense = 0;
        else this.defense = defense;
    }
    public void setDodge(int dodge) {
        if(dodge <= 0) this.dodge = 0;
        else this.dodge = dodge;
    }
    // methods
    public void displayInfo(int index) {
        System.out.print(index + ": Name: ");
        Color.println(Color.RED, name);
        String info = String.format(
            "\tType: %s\n" +
            "\tLevel: %d\n" +
            "\tHealth: %d\n" +
            "\tDamage: %d\n" +
            "\tDefense: %d\n" +
            "\tDodge Chance: %d%%\n",
            type, level, hp, damage, defense, dodge);
        
        System.out.println(info);
    }
    // set hp (get damage)
    public void setHp(int hp) {
        if(hp <= 0) this.hp = 0;
        else this.hp = hp;
    }
    // monster attack hero
    public void attack(Hero hero) {
        // first roll the dice
        if(RandomGenerator.checkProbability(hero.getAttribute().getCurrAgility() * Hero.AGILITY_TO_MISS)) {
            System.out.print("The attack from ");
            Color.print(Color.RED, name);
            System.out.print(" to ");
            Color.print(Color.GREEN, hero.getAttribute().getName());
            System.out.println(" is missed!");
        }else{
            // deal the damage
            int damage = this.damage / DAMAGE_REDUCTION;
            if(hero.getArmor() != null) {
                damage -= hero.getArmor().getDamageReduction() / Armor.DAMAGE_REDUCTION_RATIO;
                if(damage < 0) damage = 0;
            }
            hero.getAttribute().setCurrHp(hero.getAttribute().getCurrHp() - damage);
            Color.print(Color.RED, name);
            System.out.print(" deals " + damage + " damage to ");
            Color.println(Color.GREEN, hero.getAttribute().getName());
        }
    }
}
