/*
 * GridMarket.java
 * By Heyang Yu(jhyyu@bu.edu)
 * This class is for market grids, it has a reference to a market instance.
 */

public class GridMarket extends Grid {
    private Market market;
    public GridMarket(int row, int col, Market market) {
        super(row, col);
        this.market = market;
    }
    public Market getMarket() {
        return market;
    }
}
