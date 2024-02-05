package co.rooam.user.fraud.checker.service;

import co.rooam.user.fraud.checker.model.UserRecord;
import co.rooam.user.fraud.checker.model.UserRisk;

public interface UserCheckerService {
    UserRecord findUserRecord(String email, String phone);
    UserRisk returnUserRisk(UserRecord userRecord);
}
