/*
 * Item.java
 * By Heyang Yu(jhyyu@bu.edu)
 * This is the super class for all items, including armors, weapons, spells and potions.
 */

import java.util.List;
import java.util.stream.Collectors;

public abstract class Item implements Tradable {
    // attributes
    private String name;
    private int price;
    private int level;
    // constructor
    public Item(String name, int price, int level) {
        this.name = name;
        this.price = price;
        this.level = level;
    }
    // getters
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public int getLevel() {
        return level;
    }
    // implement tradable
    public boolean buy(Hero hero, Market market) {
        if(hero.getAttribute().getLevel() < level) {
            Color.println(Color.RED, "You cannot purchase an item with a higher level than yourself!");
            return false;
        }
        if(hero.getAttribute().getGold() < price) {
            Color.println(Color.RED, "You don't have enough gold!");
            return false;
        }
        hero.getAttribute().setGold(hero.getAttribute().getGold() - price);
        market.removeItem(this);
        hero.getInventory().add(this);
        return true;
    }
    public void sell(Hero hero, Market market) {
        hero.getAttribute().setGold(hero.getAttribute().getGold() + price / 2);
        market.addItem(this);
        hero.getInventory().remove(this);
    }
    // get all elements of a class in a list
    public static <T> List<T> getItemsOfType(List<Item> items, Class<T> itemType) {
        return items.stream()
                    .filter(itemType::isInstance)
                    .map(itemType::cast)
                    .collect(Collectors.toList());
    }
    // display an item, to be overriden in the subclasses
    public void display(int index) {
        System.out.println("Default item display for item " + index);
    }
}
