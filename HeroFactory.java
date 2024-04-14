/*
 * HeroFactory.java
 * By Heyang Yu(jhyyu@bu.edu)
 * This is to create hero insance base on txt data.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HeroFactory {
    // attributes
    private static List<String> warriorData = new ArrayList<>();
    private static List<String> sorcererData = new ArrayList<>();
    private static List<String> paladinData = new ArrayList<>();
    private static boolean flag = false;
    public static final int TYPE_OF_HEROS = 3;
    // read the data from the file
    public static void initialize() {
        // if already initialized then return
        if(flag) return;
        // read warriors data
        String filePath = "./Legends_Monsters_and_Heroes/Warriors.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // skip first line
                    continue;
                }
                warriorData.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // read sorcerers data
        filePath = "./Legends_Monsters_and_Heroes/Sorcerers.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // skip first line
                    continue;
                }
                sorcererData.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // read paladins data
        filePath = "./Legends_Monsters_and_Heroes/Paladins.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // skip first line
                    continue;
                }
                paladinData.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // update the flag
        flag = true;
    }
    // create a hero of a type
    public static Hero selectAHeroByType(String type, int index) {
        System.out.println("Please select your hero " + index + ".");
        System.out.println(String.format("%-5s %-20s %-10s %-10s %-10s %-10s %-10s %-10s", 
                                 "No.", "Name", "Mana", "Strength", "Agility", "Dexterity", "Starting Money", "Starting Exp"));
        List<String> heroSet = new ArrayList<>();
        if(type.equals("Warrior")) {
            heroSet = warriorData;
        }else if(type.equals("Sorcerer")) {
            heroSet = sorcererData;
        }else{
            heroSet = paladinData;
        }
        int i = 1;
        for(String hero: heroSet) {
            String[] parts = hero.split("\\s+");
            System.out.println(String.format("%-5d %-20s %-10s %-10s %-10s %-10s %-14s %-10s", 
                                            i,
                                            parts[0].replace('_', ' '),
                                            parts[1], 
                                            parts[2], 
                                            parts[3], 
                                            parts[4], 
                                            parts[5], 
                                            parts[6]));
            i++;

        }
        int selection = InputHandler.getAnIntegerInARange("hero you select", 1, heroSet.size());
        String hero = heroSet.get(selection - 1);
        String[] heroStats = hero.split("\\s+");
        HeroAttribute attribute = new HeroAttribute(heroStats[0], type, 1, 
            Integer.parseInt(heroStats[6]),
            Integer.parseInt(heroStats[1]),
            Integer.parseInt(heroStats[2]),
            Integer.parseInt(heroStats[4]),
            0,
            Integer.parseInt(heroStats[3]),
            Integer.parseInt(heroStats[5]));
        return new Hero(attribute, new ArrayList<>());
    }
    // select a type
    public static String selectType(int index) {
        System.out.println("Please select the type of your hero " + index + ". 1: Warrior; 2: Sorcerer; 3: Paladin.");
        int type = InputHandler.getAnIntegerInARange("type of hero", 1, TYPE_OF_HEROS);
        if(type == 1) {
            return "Warrior";
        }else if(type == 2) {
            return "Sorcerer";
        }else{
            return "Paladin";
        }
    }
    // select a hero
    public static Hero selectAHero(int index) {
        String type = selectType(index);
        return selectAHeroByType(type, index);
    }


    // create a hero of legends of valor
    public static HeroLegends selectAHeroLegendsByType(String type, int index) {
        System.out.println("Please select your hero " + index + ".");
        System.out.println(String.format("%-5s %-20s %-10s %-10s %-10s %-10s %-10s %-10s", 
                                 "No.", "Name", "Mana", "Strength", "Agility", "Dexterity", "Starting Money", "Starting Exp"));
        List<String> heroSet = new ArrayList<>();
        if(type.equals("Warrior")) {
            heroSet = warriorData;
        }else if(type.equals("Sorcerer")) {
            heroSet = sorcererData;
        }else{
            heroSet = paladinData;
        }
        int i = 1;
        for(String hero: heroSet) {
            String[] parts = hero.split("\\s+");
            System.out.println(String.format("%-5d %-20s %-10s %-10s %-10s %-10s %-14s %-10s", 
                                            i,
                                            parts[0].replace('_', ' '),
                                            parts[1], 
                                            parts[2], 
                                            parts[3], 
                                            parts[4], 
                                            parts[5], 
                                            parts[6]));
            i++;

        }
        int selection = InputHandler.getAnIntegerInARange("hero you select", 1, heroSet.size());
        String hero = heroSet.get(selection - 1);
        String[] heroStats = hero.split("\\s+");
        HeroAttribute attribute = new HeroAttribute(heroStats[0], type, 1, 
            Integer.parseInt(heroStats[6]),
            Integer.parseInt(heroStats[1]),
            Integer.parseInt(heroStats[2]),
            Integer.parseInt(heroStats[4]),
            0,
            Integer.parseInt(heroStats[3]),
            Integer.parseInt(heroStats[5]));
        return new HeroLegends(attribute, new ArrayList<>());
    }

    // return a hero legends
    public static HeroLegends selectAHeroLegends(int index) {
        String type = selectType(index);
        return selectAHeroLegendsByType(type, index);
    }
    public static void main(String[] args) {
        initialize();
        Hero h = selectAHero(1);
        System.out.println(h.getAttribute().getName());
    }
}
