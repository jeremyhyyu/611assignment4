/*
 * Random.java
 * By Heyang Yu (jhyyu@bu.edu)
 * This class is to generate random result.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomGenerator {
    // generate a randon integer within a range
    public static int randInt(int l, int r) {
        Random rand = new Random();
        return rand.nextInt(r - l + 1) + l;
    }
    // generate unique coordinates
    public static List<String> generateUniqueCoordinates(int count, int size) {
        Set<String> coordinatesSet = new HashSet<>();
        Random random = new Random();
        while (coordinatesSet.size() < count) {
            int x = random.nextInt(size);
            int y = random.nextInt(size);
            coordinatesSet.add(x + "," + y);
        }
        List<String> coordinates = new ArrayList<>(coordinatesSet);
    
        Collections.shuffle(coordinates, random);
        
        return coordinates;
    }
    // roll a dice with a possibility of input to return true
    public static boolean checkProbability(double probability) {
        if (probability < 0.0 || probability > 1.0) {
            throw new IllegalArgumentException("Probability must be between 0.0 and 1.0");
        }
        Random random = new Random();
        // Generate a random value between 0.0 and 1.0
        double randomValue = random.nextDouble();
        
        // Return true if the random value is less than the input probability
        return randomValue < probability;
    }
}
