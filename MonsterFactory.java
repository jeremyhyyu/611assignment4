/*
 * MonsterFactory.java
 * By Heyang Yu(jhyyu@bu.edu)
 * This class is to read txt file and generate monster instances.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MonsterFactory {
    // attributes
    private static List<String> dragonData = new ArrayList<>();
    private static List<String> exoskeletonData = new ArrayList<>();
    private static List<String> spiritData = new ArrayList<>();
    private static boolean flag = false;
    // read the data from the database
    public static void initialize() {
        if(flag) return;
        // read dragons data
        String filePath = "./Legends_Monsters_and_Heroes/Dragons.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // skip first line
                    continue;
                }
                dragonData.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // read exoskeletons data
        filePath = "./Legends_Monsters_and_Heroes/Exoskeletons.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // skip first line
                    continue;
                }
                exoskeletonData.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // read spirits data
        filePath = "./Legends_Monsters_and_Heroes/Spirits.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // skip first line
                    continue;
                }
                spiritData.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        flag = true;
    }
    // generate random monster based on hero level (monster level <= hero level)
    public static Monster generateMonsterByHeroLevel(int level) {
        // first generate a random integer which represent the type of the monster
        int typeIndex = RandomGenerator.randInt(1, 3);
        switch (typeIndex) {
            case 1:
                List<String> dragons = filterByLevel(dragonData, level);
                String dragon = dragons.get(RandomGenerator.randInt(0, dragons.size() - 1));
                return parseStringToMonster(dragon, "Dragon");
            case 2:
                List<String> exoskeletons = filterByLevel(exoskeletonData, level);
                String exoskeleton = exoskeletons.get(RandomGenerator.randInt(0, exoskeletons.size() - 1));
                return parseStringToMonster(exoskeleton, "Exoskeleton");
            case 3:
                List<String> spirits = filterByLevel(spiritData, level);
                String spirit = spirits.get(RandomGenerator.randInt(0, spirits.size() - 1));
                return parseStringToMonster(spirit, "Spirit");
            
            default:
                return null;
        }
    }
    // filter monster data by level
    public static List<String> filterByLevel(List<String> originalList, int heroLevel) {
        List<String> filteredList = new ArrayList<>();

        for (String entry : originalList) {
            String[] parts = entry.split("\\s+");
            int level = Integer.parseInt(parts[1]);
            // if hero level is higher than 10, then hero can only encounter level 10 monster
            if(heroLevel <= 10) {
                // a range for monster level
                if (level >= heroLevel - 1 && level <= heroLevel) {
                    filteredList.add(entry);
                }
            } else {
                if (level == 10) {
                    filteredList.add(entry);
                }
            }
            
        }

        return filteredList;
    }
    // parse the data
    public static Monster parseStringToMonster(String monsterData, String monsterType) {
        String[] parts = monsterData.split("\\s+");

        if (parts.length >= 5) {
            String name = parts[0];
            int level = Integer.parseInt(parts[1]);
            int damage = Integer.parseInt(parts[2]);
            int defense = Integer.parseInt(parts[3]);
            int dodgeChance = Integer.parseInt(parts[4]);

            return new Monster(name, monsterType, level, damage, defense, dodgeChance);
        } else {
            System.out.println("Invalid data format.");
            return null;
        }
    }

    // testing
    public static void main(String[] args) {
        initialize();
        generateMonsterByHeroLevel(1).displayInfo(1);
    }
}
