package co.rooam.user.fraud.checker.service;

import co.rooam.user.fraud.checker.model.UserRecord;
import co.rooam.user.fraud.checker.model.UserRisk;
import co.rooam.user.fraud.checker.util.CountryCodeRiskLevel;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static co.rooam.user.fraud.checker.util.MiscUtil.*;
import static co.rooam.user.fraud.checker.util.RiskLevel.*;

@Service
public class UserCheckerServiceImpl implements UserCheckerService {

    private static final Logger LOG = LoggerFactory.getLogger(UserCheckerServiceImpl.class);
    private final Faker FAKER = new Faker();
    private final List<String> riskFactors = new ArrayList<>();

    // This function contains the logic definition for checking the user.
    // For the sake of this code challenge, I'll stub user record via Faker library
    @Override
    public UserRecord findUserRecord(String email, String phone) {

        String recordEmail = email != null ? email : generateEmail();
        String recordPhone = phone != null ? phone : generatePhone();

        LOG.info("Checking record for user with email: " + recordEmail + " and phone: " + recordPhone);

        // This function is used to simulate a null record found
        // 25% chance (out of 100%) of returning true.
        if (generateWeightedRandomBoolean(100, 25)) {
            LOG.warn("User record not found.");
            return null;
        }

        // If no null returned, retrieve a user record instead
        return generateUserRecord(recordEmail, recordPhone);
    }

    // This function returns the risk level of the user based on the score calculated
    @Override
    public UserRisk returnUserRisk(UserRecord userRecord) {
        riskFactors.clear(); // Clear risk factors list

        Integer score = 0; // initial value is low score
        // Get country risk score
        score += getCountryRiskScore(userRecord.countryCode());
        // Check if email is blacklisted
        score += getParamBlockListedRiskScore(userRecord.email(), "Email");
        // Check if phone is blacklisted
        score += getParamBlockListedRiskScore(userRecord.phone(), "Phone");
        // Calculate risk level based on the score
        String riskLevel = getRiskLevel(score);

        return new UserRisk(userRecord, riskLevel, riskFactors);
    }

    private Integer getCountryRiskScore(String countryCode) {
        String countryRisk =
                CountryCodeRiskLevel.isHighRiskCountryCode(countryCode) ? HIGH.toString() :
                CountryCodeRiskLevel.isMidRiskCountryCode(countryCode) ? MID.toString() :
                        LOW.toString();

        if (countryRisk.equals("HIGH")) {
            riskFactors.add("Country risk is high.");
        } else if (countryRisk.equals("MID")) {
            riskFactors.add("Country risk is mid.");
        }

        return switch (countryRisk) {
            case "HIGH" -> 50;
            case "MID" -> 20;
            default -> 0;
        };
    }

    private Integer getParamBlockListedRiskScore(String parameter, String parameterType) {
        // Stub. 25% chance (out of 100%) of returning true.
        boolean isEmailBlackListed = generateWeightedRandomBoolean(100, 20);

        if (isEmailBlackListed) {
            riskFactors.add(parameterType + " " + parameter + " is blocklisted.");
            return 20;
        }
        return 0;
    }

    private String getRiskLevel(Integer score) {
        if (score >= 50) {
            return HIGH.toString();
        } else if (score >= 20) {
            return MID.toString();
        } else {
            return LOW.toString();
        }
    }
}
