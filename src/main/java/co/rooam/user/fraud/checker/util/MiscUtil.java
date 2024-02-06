package co.rooam.user.fraud.checker.util;

import co.rooam.user.fraud.checker.model.UserRecord;
import net.datafaker.Faker;

import java.util.Random;
import java.util.UUID;

public class MiscUtil {

    private static final Faker FAKER = new Faker();

    public static UserRecord generateUserRecord(String email, String phone) {
        return new UserRecord(
                UUID.randomUUID().toString(),
                FAKER.name().firstName(),
                FAKER.name().lastName(),
                phone,
                email,
                FAKER.address().countryCode()
        );
    }

    public static String generateEmail() {
        return FAKER.internet().emailAddress();
    }

    public static String generatePhone() {
        return FAKER.phoneNumber().phoneNumber();
    }

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
