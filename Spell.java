/*
 * Spell.java
 * By Heyang Yu (jhyyu@bu.edu)
 * This is the super class for all kinds of spells.
 */

public class Spell extends Item {
    // class variable
    public static final double DEXTERITY_TO_DAMAGE = 0.00001;
    public static final double DAMAGE_TO_EFFECT = 0.1;
    // attributes
    private int damage;
    private int cost;
    private Monster target;
    private UseBehavior useBehavior;

    // constructor
    public Spell(String name, int price, int level, int damage, int cost) {
        super(name, price, level);
        this.damage = damage;
        this.cost = cost;
    }

    // getters
    public int getDamage() {
        return damage;
    }
    public int getCost() {
        return cost;
    }
    public Monster getTarget() {
        return target;
    }
    public UseBehavior getUseBehavior() {
        return useBehavior;
    }
    // setters
    public void setTarget(Monster target) {
        this.target = target;
        useBehavior = new UseByCast(this, target);
    }

    // display
    public void display(int index) {
        System.out.println("default spell display for spell " + index);
    }
    // implement usable
    public void use(Hero hero) {
        // default use
        useBehavior.use(hero);
    }
}
