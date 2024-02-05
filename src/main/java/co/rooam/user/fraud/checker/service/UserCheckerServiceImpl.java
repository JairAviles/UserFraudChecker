package co.rooam.user.fraud.checker.service;

import co.rooam.user.fraud.checker.model.UserRecord;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

import static co.rooam.user.fraud.checker.util.RandomUtil.generateWeightedRandomBoolean;

@Service
public class UserCheckerServiceImpl implements UserCheckerService {

    private static final Logger log = LoggerFactory.getLogger(UserCheckerServiceImpl.class);
    private final Faker faker = new Faker();
    private final Random random = new Random();

    // This function contains the logic definition for checking the user.
    // For the sake of this code challenge, I'll stub user record via Faker library
    public UserRecord getUserRecord(String email, String phone) {

        String recordEmail = email != null ? email : faker.internet().emailAddress();
        String recordPhone = phone != null ? phone : faker.phoneNumber().phoneNumber();

        log.info("Checking record for user with email: " + recordEmail + " and phone: " + recordPhone);

        /* STUB implementation.
           The following lines can be replaced
           by an external API call, DB query request,
           yadda yadda...
         */

        // Random function to return null record
        if (shouldReturnNullRecord()) {
            log.warn("User record not found.");
            return null;
        }

        // If no null returned, retrieve a user record instead
        return new UserRecord(
                UUID.randomUUID().toString(),
                faker.name().firstName(),
                faker.name().lastName(),
                recordPhone,
                recordEmail,
                faker.address().countryCode()
        );
    }

    // This function is used to simulate a null record found
    // 25% chance (out of 100%) of returning true.
    private boolean shouldReturnNullRecord() {
        return generateWeightedRandomBoolean(100, 25);
    }
}
