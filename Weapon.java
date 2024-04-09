/*
 * Weapon.java
 * By Heyang Yu (jhyyu@bu.edu)
 * Class for weapons
 */

public class Weapon extends Item implements Equipable {
    // attributes
    private int damage;
    private int handsRequired;
    // constructor
    public Weapon(String name, int price, int level, int damage, int handsRequired) {
        super(name, price, level);
        this.damage = damage;
        this.handsRequired = handsRequired;
    }
    // getters
    public int getDamage() {
        return damage;
    }
    public int getHandsRequired() {
        return handsRequired;
    }
    // display info
    public void display(int index) {
        System.out.print(index + ": Name: ");
        int level = getLevel();
        if(level <= 3) {
            Color.println(Color.BLUE, getName());
        } else if (level > 3 && level <= 7) {
            Color.println(Color.PURPLE, getName());
        } else {
            Color.println(Color.RED, getName());
        }
        String info = String.format(
            "\tType: %s\n" +
            "\tPrice: %d\n" +
            "\tLevel: %d\n" +
            "\tDamage: %d\n" +
            "\tHands Required: %d\n",
            "Weapon", getPrice(), getLevel(), damage, handsRequired);
        
        System.out.println(info);
    }
    // implement equipable
    public void equipBy(Hero hero) {
        hero.getInventory().remove(this);
        if(hero.getWeapon() == null) {
            Color.println(Color.BLUE, "You equipped " + getName() + ".");
        }else{
            Color.println(Color.BLUE, "You replaced " + hero.getWeapon().getName() + " with " + getName() + ".");
            hero.getInventory().add(hero.getWeapon());
        }
        hero.setWeapon(this);
    }
}
