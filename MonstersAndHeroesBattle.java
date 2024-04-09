/*
 * MonsterAndHeroesBattle.java
 * By Heyang Yu(jhyyu@bu.edu)
 * This class define a process of a battle between monsters and heroes.
 */

import java.util.ArrayList;
import java.util.List;

public class MonstersAndHeroesBattle {
    // class attributes
    public static final int HERO_DAMAGE_RATIO = 20;
    public static final int MONSTER_DAMAGE_REDUCTION_BY_DEFENSE = 50;
    public static final int GOLD_REWARD = 100;
    public static final int EXP_REWARD = 2;
    public static final int RECOVERY_RATE = 10;
    public static final int REWARD_DEDUCTION = 2;
    public static final int REVIVE = 2;
    // attributes
    private List<Monster> monsters;
    private List<Hero> heroesAlive;
    private List<Hero> defeatedHeroes;
    private List<Monster> defeatedMonsters;
    // getters
    public List<Monster> getMonsters() {
        return monsters;
    }
    public List<Hero> getHeroesAlive() {
        return heroesAlive;
    }
    public List<Hero> getDefeatedHeros() {
        return defeatedHeroes;
    }
    public List<Monster> getDefeatedMonsters() {
        return defeatedMonsters;
    }
    // constructor
    public MonstersAndHeroesBattle(List<Monster> monsters, List<Hero> heroes) {
        this.monsters = monsters;
        this.heroesAlive = heroes;
        this.defeatedHeroes = new ArrayList<>();
        this.defeatedMonsters = new ArrayList<>();
    }
    // battle start
    public void start() {
        Color.println(Color.RED, "Oops! You are unlucky!");
        Color.println(Color.RED, "Battle starts!");
        loop:
        while(!monsters.isEmpty() && !heroesAlive.isEmpty()) {
            // print monsters and heros info
            displayMonstersAndHeros();
            // ask all hero to make a input
            for(Hero hero: heroesAlive) {
                // if after this hero action hero team wins
                if(askHeroToMakeAnInput(hero)) {
                    break loop;
                }
            }
            for(Monster monster: monsters) {
                // randomly attack a hero
                int target = RandomGenerator.randInt(1, heroesAlive.size());
                Hero targetHero = heroesAlive.get(target - 1);
                monster.attack(targetHero);
                if(targetHero.getAttribute().getCurrHp() <= 0) {
                    System.out.print("Hero ");
                    Color.print(Color.GREEN, targetHero.getAttribute().getName());
                    System.out.println(" is defeated!");
                    heroesAlive.remove(targetHero);
                    defeatedHeroes.add(targetHero);
                    // if all heros are defeated game over
                    if(heroesAlive.isEmpty()) {
                        Color.println(Color.RED, "All heros defeated, game over!");
                        System.exit(0);
                    }
                }
            }
            // undefeated heros regain hp and mana
            regain_hpmp();
        }
        // revive defeated heros
        reviveDefeatedHeros();
        // get rewards
        getRewards();
        // reset all battle related attributes
        resetHeroAttri();
    }
    // display monsters and heros info
    public void displayMonstersAndHeros() {
        Color.println(Color.RED, "Monsters");
        Color.println(Color.RED, "+---------------------------------+");
        int index = 1;
        for(Monster monster: monsters) {
            monster.displayInfo(index);
            index++;
        }
        Color.println(Color.GREEN, "Heros");
        Color.println(Color.GREEN, "+---------------------------------+");
        index = 1;
        for(Hero hero: heroesAlive) {
            hero.getAttribute().display(index);
            index++;
        }
    }
    // ask hero to make an input, return true if after this move hero wins
    public boolean askHeroToMakeAnInput(Hero hero) {
        while(true) {
            System.out.println("+----------------------------+");
            System.out.print("Hero ");
            Color.print(Color.GREEN, hero.getAttribute().getName());
            System.out.println("'s turn!'");
            System.out.println("Please input your action");
            System.out.printf("%d-%d: Attack the monster with corresponding index", 1, monsters.size());
            System.out.println();
            System.out.println("c: cast a spell");
            System.out.println("p: use a potion");
            System.out.println("e: equit a weapon or armor");
            System.out.println("i: check stats");
            int itemCnt = monsters.size();
            String[] validSet = new String[itemCnt + 8];
            for(int i = 0; i < itemCnt; i++) {
                validSet[i] = String.valueOf(i + 1);
            }
            validSet[itemCnt] = "c";
            validSet[itemCnt + 1] = "C";
            validSet[itemCnt + 2] = "p";
            validSet[itemCnt + 3] = "P";
            validSet[itemCnt + 4] = "e";
            validSet[itemCnt + 5] = "E";
            validSet[itemCnt + 6] = "i";
            validSet[itemCnt + 7] = "I";
            String userInput = InputHandler.getAValidChoiceString("Your choice: ", validSet);
            if(userInput.equalsIgnoreCase("c")) {
                // cast a spell from inventory
                if(hero.spellMenu(monsters)) {
                    if(allMonstersDefeated()) return true;
                    else return false;
                }
            }else if(userInput.equalsIgnoreCase("p")) {
                // drink a potion from inventory
                if(hero.potionMenu()) {
                    return false;
                }
            }else if(userInput.equalsIgnoreCase("e")) {
                // equip a weapon or armor from inventory
                if(hero.equipmentMenu()) {
                    return false;
                }
            }else if(userInput.equalsIgnoreCase("i")) {
                // display the stats
                displayMonstersAndHeros();
                // does not take turn, keep asking for input
            }else{
                // attack a monster with weapon
                int index = Integer.parseInt(userInput) - 1;
                Monster monster = monsters.get(index);
                // first roll the dice
                if(RandomGenerator.checkProbability(monster.getDodge() * Monster.DODGE_RATIO)) {
                    System.out.print("Oops! ");
                    Color.print(Color.RED, monster.getName());
                    System.out.println(" dodged!");
                    return false;
                }else{
                    int damage = 0;
                    if(hero.getWeapon() != null) {
                        damage = (hero.getAttribute().getCurrStrength() + hero.getWeapon().getDamage()) / HERO_DAMAGE_RATIO - monster.getDefense() / MONSTER_DAMAGE_REDUCTION_BY_DEFENSE;
                    }else{
                        damage = hero.getAttribute().getCurrStrength() / HERO_DAMAGE_RATIO - monster.getDefense() / MONSTER_DAMAGE_REDUCTION_BY_DEFENSE;
                    }
                    if(damage < 0) damage = 0;
                    // deal damage to monster
                    if(dealDamageToMonster(hero, monster, damage)) {
                        // all monsters are dead
                        return true;
                    }else return false;
                }
            }
        }
    }
    // deal damage to monster, return true if all monsters are dead
    public boolean dealDamageToMonster(Hero hero, Monster monster, int damage) {
        Color.print(Color.GREEN, hero.getAttribute().getName());
        System.out.print(" deals " + damage + " damage to ");
        Color.println(Color.RED, monster.getName());
        monster.setHp(monster.getHp() - damage);
        if(monster.getHp() == 0) {
            Color.print(Color.RED, monster.getName());
            System.out.println(" is defeated!");
            defeatedMonsters.add(monster);
            monsters.remove(monster);
            // check if all monsters are dead
            if(monsters.isEmpty()) {
                return true;
            }
        }
        return false;
    }
    // monster recyler
    public void monsterRecyler() {
        for(Monster monster: monsters) {
            if(monster.getHp() <= 0) {
                defeatedMonsters.add(monster);
            }
        }
        for(Monster monster: defeatedMonsters) {
            if(monsters.contains(monster)) {
                Color.print(Color.RED, monster.getName());
                System.out.println(" is defeated!");
                monsters.remove(monster);
            }
        }
    }
    // check if there are alive monsters
    public boolean allMonstersDefeated() {
        monsterRecyler();
        if(monsters.isEmpty()) return true;
        else return false;
    }
    // get rewards
    public void getRewards() {
        int gold = 0;
        int exp = 0;
        for(Monster monster: defeatedMonsters) {
            gold += monster.getLevel() * GOLD_REWARD;
            exp += monster.getLevel() * EXP_REWARD;
        }
        for(Hero hero: heroesAlive) {
            hero.getAttribute().setGold(hero.getAttribute().getGold() + gold);
            hero.getAttribute().setExperience(hero.getAttribute().getExperience() + exp);
        }
        for(Hero hero: defeatedHeroes) {
            hero.getAttribute().setGold(hero.getAttribute().getGold() + gold / REWARD_DEDUCTION);
            hero.getAttribute().setExperience(hero.getAttribute().getExperience() + exp / REWARD_DEDUCTION);
        }
    }
    // revive all defeated hero
    public void reviveDefeatedHeros() {
        for(Hero hero: defeatedHeroes) {
            hero.getAttribute().setCurrHp(hero.getAttribute().getHp() / REVIVE);
            hero.getAttribute().setCurrMp(hero.getAttribute().getMp() / REVIVE);
        }
    }
    // regain hp mp for all undefeated heros
    public void regain_hpmp() {
        for(Hero hero: heroesAlive) {
            hero.getAttribute().setCurrHp(hero.getAttribute().getCurrHp() + hero.getAttribute().getHp() / RECOVERY_RATE);
            hero.getAttribute().setCurrMp(hero.getAttribute().getCurrMp() + hero.getAttribute().getMp() / RECOVERY_RATE);
        }
    }
    // reset battle related attri for all heros
    public void resetHeroAttri() {
        for(Hero hero: heroesAlive) {
            hero.getAttribute().resetBattleRelatedAttributes();
        }
    }
}