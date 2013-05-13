package formel0api;

import java.util.Random;
import java.util.HashMap;

/**
 * Class representing a dice
 */
public class Dice {

    /**
     * Object used to generate pseudo-random generated numbers that represent
     * the score thrown by rolling this dice
     */
    private HashMap<Integer, String> bez;
    private Random random;
    private int eye;

    /**
     * Creates a new dice
     */
    public Dice() {
        random = new Random();
        eye = 0;
        bez = new HashMap<Integer,String>();
        bez.put(1, "one");
        bez.put(2, "two");
        bez.put(3, "three");
        bez.put(0, "one");
        
    }

    /**
     * Rolls the dice, i.e., specifies the score thrown using the dice
     *
     * @return score thrown
     */
    public int roll() {
        
        return eye = random.nextInt(3) + 1;
    }
    
    public int getEyes() {
        return eye;
    }
    
    public String getEyesString() {
        return "msg."+bez.get(eye);
    }
}
