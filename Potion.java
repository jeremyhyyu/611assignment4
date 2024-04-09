/*
 * Potion.java
 * By Heyang Yu(jhyyu@bu.edu)
 * This is the class for potions, heroes can drink potions to gain attributes.
 */

public class Potion extends Item implements Usable {
    // attributes
    private int increase;
    private String attributesAffected;
    //constructor
    public Potion(String name, int price, int level, int increase, String attributesAffected) {
        super(name, price, level);
        this.increase = increase;
        this.attributesAffected = attributesAffected;
    }
    // getters
    public int getIncrease() {
        return increase;
    }
    public String getAttributesAffected() {
        return attributesAffected;
    }
    // implement usable
    public void use(Hero hero) {
        for(String attribute: attributesAffected.split("/")) {
            switch (attribute) {
                case "Health":
                    hero.getAttribute().setCurrHp(hero.getAttribute().getCurrHp() + increase);
                    break;
                case "Mana":
                    hero.getAttribute().setCurrMp(hero.getAttribute().getCurrMp() + increase);
                    break;
                case "Strength":
                    hero.getAttribute().setCurrStrength(hero.getAttribute().getCurrStrength() + increase);
                    break;
                case "Dexerity":
                    hero.getAttribute().setCurrDexterity(hero.getAttribute().getCurrDexterity() + increase);
                    break;
                case "Defense":
                    hero.getAttribute().setCurrDefense(hero.getAttribute().getCurrDefense() + increase);
                    break;
                case "Agility":
                    hero.getAttribute().setCurrAgility(hero.getAttribute().getCurrAgility() + increase);
                    break;
                default:
                    System.out.println("Attribute " + attribute + " not recognized.");
                    break;
            }
        }
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
