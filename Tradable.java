/*
 * Tradable.java
 * By Heyang Yu (jhyyu@bu.edu)
 * This class is an interface for all tradable items, all items are tradable in the current version.
 */

public interface Tradable {
    boolean buy(Hero hero, Market market);
    void sell(Hero hero, Market market);
}
