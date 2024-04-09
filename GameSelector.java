/*
 * GameSelector.java
 * By Heyang Yu(jhyyu@bu.edu)
 * This class is used to select different games
 */

public class GameSelector {
    public static final int GAME_CNT = 1;
    public static void selectAGame() {
        while(true) {
            System.out.println("Which game do you want to play? 1: Monsters and Heros; Q: quit");
            String[] validSet = new String[]{"1", "q", "Q"};
            String gameSelection = InputHandler.getAValidChoiceString("Please input your choice: ", validSet);
            if(gameSelection.equals("1")) {
                // customize game settings
                int sideLength = InputHandler.getAnIntegerInARange("side length", MonstersAndHeroes.MIN_SIDE_LENGTH, MonstersAndHeroes.MAX_SIDE_LENGTH);
                MonstersAndHeroes mh = new MonstersAndHeroes(sideLength);
                mh.firstPlay();
            }else if(gameSelection.equalsIgnoreCase("q")) {
                System.out.println("Bye!");
                break;
            }
        }
    }
}
