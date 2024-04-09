/*
 * Market.java
 * By Heyang Yu (jhyyu@bu.edu)
 * This class is to manage items in a market.
 */

import java.util.List;

public class Market {
    // attributes
    private List<Item> itemList;
    private int row;
    private int col;
    // constructor
    public Market(int row, int col, List<Item> itemList) {
        this.row = row;
        this.col = col;
        this.itemList = itemList;
    }
    // getters
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public List<Item> getItemList() {
        return itemList;
    }
    // setters
    public void setCoords(int row, int col) {
        this.row = row;
        this.col = col;
    } 
    // methods
    public void addItem(Item i) {
        itemList.add(i);
    }
    public void removeItem(Item i) {
        itemList.remove(i);
    }
    // refresh items in a market
    public void refreshMarket(Hero hero) {
        if(hero.getAttribute().getGold() < 100) {
            System.out.println("You do not have enough money to refresh!");
            return;
        }
        itemList = MarketFactory.getAListOfItems();
        hero.getAttribute().setGold(hero.getAttribute().getGold() - 100);
    }
    // display items
    public void displayItems() {
        for(int i = 0; i < itemList.size(); i++) {
            itemList.get(i).display(i + 1);
        }
    }
    // trading menu
    public void tradingMenu(Hero hero) {
        String[] validSet = new String[]{"1", "2", "q", "Q"};
        while(true) {
            System.out.println("+----------------------------------+");
            System.out.println("Which action do you want to take?");
            System.out.println("1. Buying items");
            System.out.println("2. Selling items");
            System.out.println("q: quit");
            String userInput = InputHandler.getAValidChoiceString("Your choice is: ", validSet);
            if(userInput.equalsIgnoreCase("1")) {
                // buying
                buying(hero);
            }
            if(userInput.equalsIgnoreCase("2")) {
                // selling
                selling(hero);
            }
            if(userInput.equalsIgnoreCase("q")) {
                break;
            }
        }
    }
    // buying
    public void buying(Hero hero) {
        if(itemList.isEmpty()) {
            System.out.println("Sorry, the store is sold out.");
        }else{
            int itemCnt = itemList.size();
            String[] validSet = new String[itemCnt + 4];
            for(int i = 0; i < itemCnt; i++) {
                validSet[i] = String.valueOf(i + 1);
            }
            validSet[itemCnt] = "r";
            validSet[itemCnt + 1] = "R";
            validSet[itemCnt + 2] = "q";
            validSet[itemCnt + 3] = "Q";
            while (true) {
                displayItems();
                System.out.println("+----------------------------------+");
                System.out.println("Which action do you want to take?");
                System.out.printf("%d-%d: Buy an item with the index", 1, itemList.size());
                System.out.println();
                System.out.print("r: Refersh the item list, ");
                Color.print(Color.YELLOW, "100");
                System.out.println("g required.");
                System.out.println("q: Finish buying");
                System.out.print("You have ");
                Color.print(Color.YELLOW, Integer.toString(hero.getAttribute().getGold()));
                System.out.println(" gold");
                String userInput = InputHandler.getAValidChoiceString("Your choice is: ", validSet);
                if(userInput.equalsIgnoreCase("q")) {
                    break;
                }else if(userInput.equalsIgnoreCase("r")) {
                    refreshMarket(hero);
                }else{
                    int index = Integer.parseInt(userInput) - 1;
                    itemList.get(index).buy(hero, this);;
                }
            }
        }
    }
    // selling
    public void selling(Hero hero) {
        if(hero.getInventory().isEmpty()) {
            Color.println(Color.RED, "It seems that there are noting in your inventory");
        }else{
            int itemCnt = hero.getInventory().size();
            String[] validSet = new String[itemCnt + 2];
            for(int i = 0; i < itemCnt; i++) {
                validSet[i] = String.valueOf(i + 1);
            }
            validSet[itemCnt] = "q";
            validSet[itemCnt + 1] = "Q";
            while(!hero.getInventory().isEmpty()) {
                hero.displayInventory();
                System.out.println("+----------------------------------+");
                System.out.println("Which action do you want to take?");
                System.out.printf("%d-%d: Sell an item with the index", 1, hero.getInventory().size());
                System.out.println();
                System.out.println("q: Finish selling");
                String userInput = InputHandler.getAValidChoiceString("Your choice is: ", validSet);
                if(userInput.equalsIgnoreCase("q")) {
                    break;
                }else{
                    int index = Integer.parseInt(userInput) - 1;
                    hero.getInventory().get(index).sell(hero, this);
                }
            }
            Color.println(Color.RED, "You've sold all items in your inventory!");
        }
    }
}
