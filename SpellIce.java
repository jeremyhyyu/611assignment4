/*
 * SpellIce.java
 * By Heyang Yu (jhyyu@bu.edu)
 * Ice Spell
 */

public class SpellIce extends Spell {

    // constructor
    public SpellIce(String name, int price, int level, int damage, int cost) {
        super(name, price, level, damage, cost);
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
            "\tMana Cost: %d\n",
            "Ice Spell", getPrice(), getLevel(), getDamage(), getCost());
        
        System.out.println(info);
    }
    // implement use
    public void use(Hero hero) {
        // deal damage to target
        int damage = (int)(getDamage() * (1 + hero.getAttribute().getCurrDexterity() * Spell.DEXTERITY_TO_DAMAGE));
        // print the info
        Color.print(Color.GREEN, hero.getAttribute().getName());
        System.out.print(" used a spell ");
        Color.print(Color.BLUE, getName());
        System.out.print(" and dealed " + damage + " damage to ");
        Color.println(Color.RED, getTarget().getName());
        // deal the damage
        getTarget().setHp(getTarget().getHp() - damage);
        // deal effect to target
        getTarget().setDamage(getTarget().getDamage() - (int)(getDamage() * Spell.DAMAGE_TO_EFFECT));
        // remove the spell from inventory
        hero.getInventory().remove(this);
    }
}
