package co.rooam.user.fraud.checker.controller;

import co.rooam.user.fraud.checker.exception.BadRequestException;
import co.rooam.user.fraud.checker.exception.UserRecordNotFound;
import co.rooam.user.fraud.checker.model.UserRecord;
import co.rooam.user.fraud.checker.model.UserRisk;
import co.rooam.user.fraud.checker.service.UserCheckerService;
import co.rooam.user.fraud.checker.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Optional;

@RestController
@RequestMapping("/api/users")
class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserCheckerService userCheckerService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRisk> checkUser(@RequestParam("email") Optional<String> email, @RequestParam("phone") Optional<String> phone) {
        // Validates at least one request parameter is provided
        if (email.isEmpty() && phone.isEmpty()) {
            String errorMessage= "Must provide at least one request parameter";
            log.error(errorMessage);

            throw new BadRequestException(errorMessage);
        }

        // Validate email and phone number formats, when provided
        if (email.isPresent() && !ValidationUtil.isValidEmail(email.get())) {
            String errorMessage = "Invalid email format";
            log.error(errorMessage);

            throw new BadRequestException(errorMessage);
        }

        if (phone.isPresent() && !ValidationUtil.isValidPhoneNumber(phone.get())) {
            String errorMessage = "Invalid phone format";
            log.error(errorMessage);

            throw new BadRequestException(errorMessage);
        }

        // Get user record
        UserRecord userRecord = userCheckerService.findUserRecord(email.orElse(null), phone.orElse(null));

        // If no user record is found, throw a 404
        if (userRecord == null) {
            String errorMessage = "User record not found";
            log.error(errorMessage);

            throw new UserRecordNotFound(errorMessage);
        }

        UserRisk userRisk = userCheckerService.returnUserRisk(userRecord);

        return ResponseEntity.ok(userRisk);
    }
}
