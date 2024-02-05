package co.rooam.user.fraud.checker.service;

import co.rooam.user.fraud.checker.model.UserRecord;

public interface UserCheckerService {
    UserRecord getUserRecord(String email, String phone);
}
