package co.rooam.user.fraud.checker.model;

import java.io.Serializable;
import java.util.Objects;

// Immutable object representing a user record
public record UserRecord(
        String id,
        String firstName,
        String lastName,
        String phone,
        String email,
        String countryCode) implements Serializable {
    public UserRecord {
        Objects.requireNonNull(id);
        Objects.requireNonNull(email);
    }
}
