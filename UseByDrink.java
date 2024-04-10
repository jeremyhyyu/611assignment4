public class UseByDrink implements UseBehavior {
    // attributes
    private String attributesAffected;
    private int increase;
    // constructor
    public UseByDrink(String attributesAffected, int increase) {
        this.attributesAffected = attributesAffected;
        this.increase = increase;
    }
    // getters
    public String getAttributesAffected() {
        return attributesAffected;
    }
    public int getIncrease() {
        return increase;
    }
    // implement the use method
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
    }
}
