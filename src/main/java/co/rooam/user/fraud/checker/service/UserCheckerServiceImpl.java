package co.rooam.user.fraud.checker.service;

import co.rooam.user.fraud.checker.model.UserRecord;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserCheckerServiceImpl implements UserCheckerService {

    private static final Logger log = LoggerFactory.getLogger(UserCheckerServiceImpl.class);
    private final Faker faker = new Faker();

    // This function contains the logic definition for checking the user.
    // For the sake of this code challenge, I'll stub user record via Faker library
    public UserRecord getUserRecord(String email, String phone) {
        String recordEmail = email != null ? email : faker.internet().emailAddress();
        String recordPhone = phone != null ? phone : faker.phoneNumber().phoneNumber();

        log.info("Checking record for user with email: " + recordEmail + " and phone: " + recordPhone);

        return new UserRecord(
                UUID.randomUUID().toString(),
                faker.name().firstName(),
                faker.name().lastName(),
                recordPhone,
                recordEmail,
                faker.address().countryCode()
        );
    }
}
