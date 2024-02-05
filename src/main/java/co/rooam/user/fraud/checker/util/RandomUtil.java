package co.rooam.user.fraud.checker.util;

import java.util.Random;

public class RandomUtil {

    /**
     * Generates a boolean value based on a weighted random chance.
     *
     * @param maxValue The exclusive upper bound on the random number generator. This should be a positive integer.
     * @param weightValue The threshold for determining the boolean value. If the generated random number is less than this value, the method returns true.
     * @return A boolean value. The probability of this being true is weightValue/maxValue.
     */
    public static boolean generateWeightedRandomBoolean(int maxValue, int weightValue) {
        Random random = new Random();
        return random.nextInt(maxValue) < weightValue;
    }
}
