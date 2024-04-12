/*
 * Factory class to create tiles for each of the game.
 * It is a singleton class so that only one instance is created
 * of the factory
 */
public class GridFactory {
    public static final String BushLegendsGrid = "BushLegendsGrid";
    public static final String CaveLegendsGrid = "CaveLegendsGrid";
    public static final String Grid = "Grid";
    public static final String GridFactory = "GridFactory";
    public static final String GridInaccessible = "GridInaccessible";
    public static final String GridMarket = "GridMarket";
    public static final String GridNormal = "GridNormal";
    public static final String GridParty = "GridParty";
    public static final String HerosNexusGrid = "HerosNexusGrid";
    public static final String InaccessibleLegendsGrid = "InaccessibleLegendsGrid";
    public static final String KoulouLegendsGrid = "KoulouLegendsGrid";
    public static final String MonstersNexusGrid = "MonstersNexusGrid";
    public static final String NexusLegendsGrid = "NexusLegendsGrid";
    public static final String PlainLegendsGrid = "PlainLegendsGrid";

    // Gridfactory variable to store the only single instance  
    private static final GridFactory instance = new GridFactory();

    // get the instance
    public static GridFactory get(){
        return instance;
    }

    // create the grid instance using the string provided
    public Grid getGrid(String gridName, int row, int col){
        switch (gridName) {
            case BushLegendsGrid:
                return new BushLegendsGrid(row, col);
            case CaveLegendsGrid:
                return new CaveLegendsGrid(row, col);
            case GridInaccessible:
                return new GridInaccessible(row, col);
            case HerosNexusGrid:
                return new HerosNexusGrid(row, col);
            case InaccessibleLegendsGrid:
                return new InaccessibleLegendsGrid(row, col);
            case KoulouLegendsGrid:
                return new KoulouLegendsGrid(row, col);
            case MonstersNexusGrid:
                return new MonstersNexusGrid(row, col);
            case NexusLegendsGrid:
                return new NexusLegendsGrid(row, col);
            case PlainLegendsGrid:
                return new PlainLegendsGrid(row, col);
            default:
                return new Grid(row, col);
        }
    }

    // empty constructor
    private GridFactory(){}
}
