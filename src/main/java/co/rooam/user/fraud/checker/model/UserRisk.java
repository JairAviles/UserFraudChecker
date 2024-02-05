package co.rooam.user.fraud.checker.model;

import java.io.Serializable;
import java.util.List;

public record UserRisk(
        UserRecord user,
        String riskLevel,
        List<String> riskFactors
) implements Serializable {
}
