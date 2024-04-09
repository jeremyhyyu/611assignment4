/*
 * Party.java
 * By Heyang Yu(jhyyu@bu.edu)
 * This is the class for hero team.
 */

import java.util.ArrayList;
import java.util.List;

public class Party {
    // attributes
    List<Hero> heros;
    private int row;
    private int col;
    // constructor
    public Party() {
        this.heros = new ArrayList<>();
    }
    // getters
    public List<Hero> getHeros() {
        return heros;
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    // setters
    public void setRow(int row) {
        this.row = row;
    }
    public void setCol(int col) {
        this.col = col;
    }
    public void setCoords(int row, int col) {
        setRow(row);
        setCol(col);
    }
    // get highest level
    public int getHighestLevel() {
        if(heros == null) return 0;
        int max = 0;
        for(Hero hero: heros) {
            if(hero.getAttribute().getLevel() > max) max = hero.getAttribute().getLevel();
        }
        return max;
    }
    // add hero to party
    public void addHero(Hero hero) {
        heros.add(hero);
    }
    // remove hero from party
    public void removeHero(Hero hero) {
        heros.remove(hero);
    }
}
