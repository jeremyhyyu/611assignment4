/*
 * GridParty.java
 * By Heyang Yu(jhyyu@bu.edu)
 * This class is for the grid where the party stand on.
 */

public class GridParty extends Grid {
    private Party party;
    public GridParty(int row, int col, Party party) {
        super(row, col);
        this.party = party;
    }
    public Party getParty() {
        return party;
    }
}
