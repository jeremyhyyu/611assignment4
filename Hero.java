/*
 * Hero.java
 * By Heyang Yu(jhyyu@bu.edu)
 * This class is to manage the attribute instance and inventory of the hero, which provides some interfaces of the inventory.
 */

import java.util.ArrayList;
import java.util.List;

public class Hero {
    // class variable
    public static final double AGILITY_TO_MISS = 0.0002;
    //attributes
    private HeroAttribute attribute;
    private List<Item> inventory;
    private Armor armor;
    private Weapon weapon;
    // constructor
    public Hero(HeroAttribute attribute, List<Item> inventory) {
        this.attribute = attribute;
        this.inventory = inventory;
    }
    // getters
    public HeroAttribute getAttribute() {
        return attribute;
    }
    public List<Item> getInventory() {
        return inventory;
    }
    public Armor getArmor() {
        return armor;
    }
    public Weapon getWeapon() {
        return weapon;
    }
    // setters
    public void setArmor(Armor armor) {
        this.armor = armor;
    }
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    // methods
    public void displayInventory() {
        if(inventory.isEmpty()) {
            Color.println(Color.RED, "It seems that there are noting in your inventory");
        }else{
            for(int i = 0; i < inventory.size(); i++) {
                inventory.get(i).display(i + 1);
            }
        }
    }
    // inventory menu
    public void inventoryMenu() {
        System.out.print("Hero ");
        Color.print(Color.GREEN, getAttribute().getName());
        System.out.println("'s inventory: ");
        if(inventory.isEmpty()) {
            Color.println(Color.RED, "It seems that there are noting in your inventory");
            return;
        }
        while (!inventory.isEmpty()) {
            displayInventory();
            System.out.println("+------------------------------+");
            System.out.println("Which action do you want to take?");
            System.out.printf("%d-%d: Equip an equipment / Use a potion", 1, inventory.size());
            System.out.println();
            System.out.println("q: Return");
            int itemCnt = inventory.size();
            String[] validSet = new String[itemCnt + 2];
            for(int i = 0; i < itemCnt; i++) {
                validSet[i] = String.valueOf(i + 1);
            }
            validSet[itemCnt] = "q";
            validSet[itemCnt + 1] = "Q";
            String userInput = InputHandler.getAValidChoiceString("Your choice is: ", validSet);
            if(userInput.equalsIgnoreCase("q")) {
                return;
            }else{
                int index = Integer.parseInt(userInput) - 1;
                Item item = inventory.get(index);
                if(item instanceof Armor) {
                    Armor armor = (Armor)item;
                    armor.equipBy(this);
                }else if(item instanceof Weapon) {
                    Weapon weapon = (Weapon)item;
                    weapon.equipBy(this);
                }else if(item instanceof Potion){
                    Potion potion = (Potion)item;
                    potion.use(this);
                }else{
                    // cannot use spell
                    Color.println(Color.RED, "You cannot use spell outside the battle!");
                }
            }
        }
        Color.println(Color.RED, "You've used all items in your inventory!");
    }
    // potion menu, return true if successfully drink a potion
    public boolean potionMenu() {
        List<Potion> potions = new ArrayList<>();
        for(Item item: inventory) {
            if(item instanceof Potion) {
                potions.add((Potion)item);
            }
        }
        // if empty return false
        if(potions.isEmpty()) {
            Color.println(Color.RED, "You don't have any potion!");
            return false;
        }
        int index = 1;
        for(Potion potion: potions) {
            potion.display(index);
            index++;
        }
        int itemCnt = potions.size();
        String[] validSet = new String[itemCnt + 2];
        for(int i = 0; i < itemCnt; i++) {
            validSet[i] = String.valueOf(i + 1);
        }
        validSet[itemCnt] = "q";
        validSet[itemCnt + 1] = "q";
        System.out.println("+----------------------------------+");
        System.out.println("Do you want to use a potion?");
        System.out.printf("%d-%d: Use potion with corresponding index", 1, potions.size());
        System.out.println();
        System.out.println("q: cancel");
        String userInput = InputHandler.getAValidChoiceString("Your choice is:", validSet);
        if(userInput.equalsIgnoreCase("q")) {
            return false;
        }else{
            int potionIndex = Integer.parseInt(userInput) - 1;
            potions.get(potionIndex).use(this); 
            return true;
        }
    }
    // spell menu, return true if successfully cast a spell
    public boolean spellMenu(List<Monster> monsters) {
        List<Spell> spells = new ArrayList<>();
        for(Item item: inventory) {
            if(item instanceof Spell) {
                spells.add((Spell)item);
            }
        }
        if(spells.isEmpty()) {
            Color.println(Color.RED, "You do not have any spell!");
            return false;
        }
        // display all spells
        int index = 1;
        for(Spell spell: spells) {
            spell.display(index);
            index++;
        }
        // make the valid string set
        int itemCnt = spells.size();
        String[] validSet = new String[itemCnt + 2];
        for(int i = 0; i < itemCnt; i++) {
            validSet[i] = String.valueOf(i + 1);
        }
        validSet[itemCnt] = "q";
        validSet[itemCnt + 1] = "Q";
        // ask the hero to input
        System.out.println("+----------------------------------+");
        System.out.println("Do you want to cast a spell?");
        System.out.printf("%d-%d: Cast spell with corresponding index", 1, spells.size());
        System.out.println();
        System.out.println("q: cancel");
        String userInput = InputHandler.getAValidChoiceString("Your choice is:", validSet);
        if(userInput.equalsIgnoreCase("q")) {
            return false;
        }else{
            int spellIndex = Integer.parseInt(userInput) - 1;
            Spell spell = spells.get(spellIndex);
            // check mana
            if(spell.getCost() > getAttribute().getCurrMp()) {
                Color.println(Color.RED, "You do not have enough mana!");
                return false;
            }else{
                // select the target
                int monsterIndex = 1;
                for(Monster monster: monsters) {
                    monster.displayInfo(monsterIndex);
                    monsterIndex++;
                }
                int monsterCnt = monsters.size();
                String[] validMonsterSelection = new String[monsterCnt + 2];
                for(int i = 0; i < monsterCnt; i++) {
                    validMonsterSelection[i] = String.valueOf(i + 1);
                }
                validMonsterSelection[monsterCnt] = "q";
                validMonsterSelection[monsterCnt + 1] = "q";
                System.out.println("+----------------------------------+");
                System.out.println("Please select your target");
                System.out.printf("%d-%d: set the corresponding monster as target", 1, monsters.size());
                System.out.println();
                System.out.println("q: cancel");
                String targetString = InputHandler.getAValidChoiceString("Your choice is: ", validMonsterSelection);
                if(targetString.equalsIgnoreCase("q")) {
                    Color.println(Color.BLACK, "You canceled a spell.");
                    return false;
                }else{
                    // cast the spell
                    spell.setTarget(monsters.get(Integer.parseInt(targetString) - 1));
                    spell.use(this);
                    return true;
                }
            }
        }
    }
    // equipments menu, return true if successfullt equip an armor or weapon
    public boolean equipmentMenu() {
        List<Weapon> weapons = new ArrayList<>();
        List<Armor> armors = new ArrayList<>();
        for(Item item: inventory) {
            if(item instanceof Weapon) {
                weapons.add((Weapon)item);
            }
            if(item instanceof Armor) {
                armors.add((Armor)item);
            }
        }
        if(weapons.isEmpty() && armors.isEmpty()) {
            Color.println(Color.RED, "You don't have any armors or weapons!");
            return false;
        }else{
            // display weapons and armors
            int index = 1;
            System.out.println("Armors:");
            System.out.println("+------------------------------+");
            for(Armor armor: armors) {
                armor.display(index);
                index++;
            }
            System.out.println("Weapons:");
            System.out.println("+------------------------------+");
            for(Weapon weapon: weapons) {
                weapon.display(index);
                index++;
            }
            String[] validSet = new String[index + 1];
            for(int i = 0; i < index - 1; i++) {
                validSet[i] = String.valueOf(i + 1);
            }
            validSet[index - 1] = "q";
            validSet[index] = "Q";
            System.out.println("+----------------------------------+");
            System.out.println("Which weapon/armor do you want to equip?");
            System.out.printf("%d-%d: equip the weapon/armor", 1, index - 1);
            System.out.println("q: cancel");
            String userInput = InputHandler.getAValidChoiceString("Your choice is: ", validSet);
            if(userInput.equalsIgnoreCase("q")) {
                return false;
            }else{
                int e_index = Integer.parseInt(userInput);
                if(e_index <= armors.size()) {
                    armors.get(e_index - 1).equipBy(this);
                    return true;
                }else{
                    weapons.get(e_index - armors.size() - 1).equipBy(this);
                    return true;
                }
            }
        }
    }
}
