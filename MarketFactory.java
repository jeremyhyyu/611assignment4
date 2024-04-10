/*
 * MarketFactory.java
 * By Heyang Yu (jhyyu@bu.edu)
 * This class is to read data from txt files, create market instances and refresh the item list;
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MarketFactory {
    // attributes
    private static List<String> armorData = new ArrayList<>();
    private static List<String> weaponData = new ArrayList<>();
    private static List<String> potionData = new ArrayList<>();
    private static List<String> fireSpellData = new ArrayList<>();
    private static List<String> iceSpellData = new ArrayList<>();
    private static List<String> lightningSpellData = new ArrayList<>();
    private static boolean flag = false;
    // read data from files
    public static void initialize() {
        if(flag) return;
        // read armorss data
        String filePath = "./Legends_Monsters_and_Heroes/Armory.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // skip first line
                    continue;
                }
                armorData.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // read weapons data
        filePath = "./Legends_Monsters_and_Heroes/Weaponry.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // skip first line
                    continue;
                }
                weaponData.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // read potions data
        filePath = "./Legends_Monsters_and_Heroes/Potions.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // skip first line
                    continue;
                }
                potionData.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // read ice spells data
        filePath = "./Legends_Monsters_and_Heroes/IceSpells.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // skip first line
                    continue;
                }
                iceSpellData.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // read fire spells data
        filePath = "./Legends_Monsters_and_Heroes/FireSpells.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // skip first line
                    continue;
                }
                fireSpellData.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // read lightning spells data
        filePath = "./Legends_Monsters_and_Heroes/LightningSpells.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // skip first line
                    continue;
                }
                lightningSpellData.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        flag = true;
    }
    // generate a random list of 6 items
    public static List<Item> getAListOfItems() {
        List<Item> itemList = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
            int type = RandomGenerator.randInt(1, 6);
            switch (type) {
                case 1:
                    String armor = armorData.get(RandomGenerator.randInt(0, armorData.size() - 1));
                    String[] armorParts = armor.split("\\s+");

                    if (armorParts.length >= 4) {
                        String name = armorParts[0];
                        int price = Integer.parseInt(armorParts[1]);
                        int level = Integer.parseInt(armorParts[2]);
                        int damageReduction = Integer.parseInt(armorParts[3]);

                        itemList.add(new Armor(name, price, level, damageReduction));
                    } else {
                        System.out.println("Invalid data format.");
                        return null;
                    }
                    break;
                case 2:
                    String weapon = weaponData.get(RandomGenerator.randInt(0, weaponData.size() - 1));
                    String[] weaponParts = weapon.split("\\s+");

                    if (weaponParts.length >= 5) {
                        String name = weaponParts[0];
                        int price = Integer.parseInt(weaponParts[1]);
                        int level = Integer.parseInt(weaponParts[2]);
                        int damage = Integer.parseInt(weaponParts[3]);
                        int handsRequired = Integer.parseInt(weaponParts[4]);

                        itemList.add(new Weapon(name, price, level, damage, handsRequired));
                    } else {
                        System.out.println("Invalid data format.");
                        return null;
                    }
                    break;
                case 3:
                    String potion = potionData.get(RandomGenerator.randInt(0, potionData.size() - 1));
                    String[] potionParts = potion.split("\\s+");

                    if (potionParts.length >= 5) {
                        String name = potionParts[0];
                        int price = Integer.parseInt(potionParts[1]);
                        int level = Integer.parseInt(potionParts[2]);
                        int increase = Integer.parseInt(potionParts[3]);
                        String attributesAffected = potionParts[4];

                        itemList.add(new Potion(name, price, level, increase, attributesAffected));
                    } else {
                        System.out.println("Invalid data format.");
                        return null;
                    }
                    break;
                case 4:
                    String iceSpell = iceSpellData.get(RandomGenerator.randInt(0, iceSpellData.size() - 1));
                    String[] iceSpellParts = iceSpell.split("\\s+");

                    if (iceSpellParts.length >= 5) {
                        String name = iceSpellParts[0];
                        int price = Integer.parseInt(iceSpellParts[1]);
                        int level = Integer.parseInt(iceSpellParts[2]);
                        int damage = Integer.parseInt(iceSpellParts[3]);
                        int cost = Integer.parseInt(iceSpellParts[4]);

                        itemList.add(new SpellIce(name, price, level, damage, cost));
                    } else {
                        System.out.println("Invalid data format.");
                        return null;
                    }
                    break;
                case 5:
                    String fireSpell = fireSpellData.get(RandomGenerator.randInt(0, fireSpellData.size() - 1));
                    String[] fireSpellParts = fireSpell.split("\\s+");

                    if (fireSpellParts.length >= 5) {
                        String name = fireSpellParts[0];
                        int price = Integer.parseInt(fireSpellParts[1]);
                        int level = Integer.parseInt(fireSpellParts[2]);
                        int damage = Integer.parseInt(fireSpellParts[3]);
                        int cost = Integer.parseInt(fireSpellParts[4]);

                        itemList.add(new SpellFire(name, price, level, damage, cost));
                    } else {
                        System.out.println("Invalid data format.");
                        return null;
                    }
                    break;
                case 6:
                    String lightningSpell = lightningSpellData.get(RandomGenerator.randInt(0, lightningSpellData.size() - 1));
                    String[] lightningSpellParts = lightningSpell.split("\\s+");

                    if (lightningSpellParts.length >= 5) {
                        String name = lightningSpellParts[0];
                        int price = Integer.parseInt(lightningSpellParts[1]);
                        int level = Integer.parseInt(lightningSpellParts[2]);
                        int damage = Integer.parseInt(lightningSpellParts[3]);
                        int cost = Integer.parseInt(lightningSpellParts[4]);

                        itemList.add(new SpellLightning(name, price, level, damage, cost));
                    } else {
                        System.out.println("Invalid data format.");
                        return null;
                    }
                    break;
                default:
                    break;
            }
        }
        return itemList;
    }
    // create a market
    public static Market generateMarket(int row, int col) {
        return new Market(row, col, getAListOfItems());
    } 
    //test
    public static void main(String[] args) {
        initialize();
        generateMarket(0, 0).displayItems();
    }
}
