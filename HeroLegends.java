/*
 * HeroLegends.java
 * By Heyang Yu(jhyyu@bu.edu)
 * This class is the class of hero for the game Legends of Valor, and it's a subclass of Hero. We implemented observer pattern
 * here, hero in Legends of Valor observe the map and is notified everytime it changes its position.
 */

import java.util.List;

public class HeroLegends extends Hero implements TerrainObserver {
    // constructor
    public HeroLegends(HeroAttribute attribute, List<Item> inventory) {
        super(attribute, inventory);
    }

    // implement the observer pattern, the input is the grid which the hero is standing on
    public void update(Grid grid) {
        // update different attributes according to the type of the grid
    }
}
