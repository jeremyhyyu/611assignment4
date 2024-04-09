/*
 * Armor.java
 * By Heyang Yu(jhyyu@bu.edu)
 * This class is the class for equipable armors which extends item and implement equipable interface
 */

public class Armor extends Item implements Equipable {
    // class variables
    public static final int DAMAGE_REDUCTION_RATIO = 10;
    // attributes
    private int damageReduction;
    //constructor
    public Armor(String name, int price, int level, int damageReduction) {
        super(name, price, level);
        this.damageReduction = damageReduction;
    } 
    // getter
    public int getDamageReduction() {
        return damageReduction;
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
            "\tDamage Reduction: %d\n",
            "Armor", getPrice(), getLevel(), damageReduction);
        
        System.out.println(info);
    }
    // implement equipable
    public void equipBy(Hero hero) {
        hero.getInventory().remove(this);
        if(hero.getArmor() == null) {
            Color.println(Color.BLUE, "You equipped " + getName() + ".");
        }else{
            Color.println(Color.BLUE, "You replaced " + hero.getArmor() + " with " + getName() + ".");
            hero.getInventory().add(hero.getArmor());
        }
        hero.setArmor(this);
    }
}
