/*
 * Potion.java
 * By Heyang Yu(jhyyu@bu.edu)
 * This is the class for potions, heroes can drink potions to gain attributes, strategy pattern is implemented here to extract behavior from this class.
 */

public class Potion extends Item {
    // attributes
    private int increase;
    private String attributesAffected;
    private UseBehavior useBehavior;
    // constructor
    public Potion(String name, int price, int level, int increase, String attributesAffected) {
        super(name, price, level);
        this.increase = increase;
        this.attributesAffected = attributesAffected;
        this.useBehavior = new UseByDrink(attributesAffected, increase);
    }
    // getters
    public int getIncrease() {
        return increase;
    }
    public String getAttributesAffected() {
        return attributesAffected;
    }
    public UseBehavior getUseBehavior() {
        return useBehavior;
    }
    // implement usable
    public void use(Hero hero) {
        useBehavior.use(hero);
        // remove the potion from hero's inventory
        hero.getInventory().remove(this);
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
            "\tIncrease: %d\n" +
            "\tAttributes Affected: %s\n",
            "Potion", getPrice(), getLevel(), increase, attributesAffected);
        
        System.out.println(info);
    }
}
