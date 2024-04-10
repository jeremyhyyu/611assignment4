public class UseByCast implements UseBehavior {
    // attributes
    private Spell spell;
    private Monster target;
    // constructor
    public UseByCast(Spell spell, Monster target) {
        this.spell = spell;
        this.target = target;
    }
    // getters
    public Monster getTarget() {
        return target;
    }
    public Spell getSpell() {
        return spell;
    }
    // implement use()
    public void use(Hero hero) {
        // deal damage to target
        int damage = (int)(spell.getDamage() * (1 + hero.getAttribute().getCurrDexterity() * Spell.DEXTERITY_TO_DAMAGE));
        // print the info
        Color.print(Color.GREEN, hero.getAttribute().getName());
        System.out.print(" used a spell ");

        // display the name of spell with different color according to the type of the spell, and deal the effect on target
        if(spell instanceof SpellFire) {
            Color.print(Color.RED, spell.getName());
            getTarget().setDefense(getTarget().getDefense() - (int)(spell.getDamage() * Spell.DAMAGE_TO_EFFECT));
        }else if(spell instanceof SpellIce) {
            Color.print(Color.BLUE, spell.getName());
            getTarget().setDamage(getTarget().getDamage() - (int)(spell.getDamage() * Spell.DAMAGE_TO_EFFECT));
        }else{
            Color.print(Color.CYAN, spell.getName());
            getTarget().setDodge(getTarget().getDodge() - (int)(spell.getDamage() * Spell.DAMAGE_TO_EFFECT));
        }

        // print the rest part of the message
        System.out.print(" and dealed " + damage + " damage to ");
        Color.println(Color.RED, getTarget().getName());
        // deal the damage
        getTarget().setHp(getTarget().getHp() - damage);
        // use the mana
        hero.getAttribute().setCurrMp(hero.getAttribute().getCurrMp() - spell.getCost());

        // remove the spell from inventory
        hero.getInventory().remove(spell);
    }
}
