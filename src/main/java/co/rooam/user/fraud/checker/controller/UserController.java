package co.rooam.user.fraud.checker.controller;

import co.rooam.user.fraud.checker.exception.BadRequestException;
import co.rooam.user.fraud.checker.model.UserRecord;
import co.rooam.user.fraud.checker.service.UserCheckerService;
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
    public ResponseEntity<UserRecord> checkUser(@RequestParam("email") Optional<String> email,
                                                    @RequestParam("phone") Optional<String> phone) {
        if (email.isEmpty() && phone.isEmpty()) {
            String errorMessage= "Must provide at least one request parameter";
            log.error(errorMessage);

            throw new BadRequestException(errorMessage);
        }

        // Get user record
        UserRecord userRecord = userCheckerService.getUserRecord(email.orElse(null), phone.orElse(null));

        return ResponseEntity.ok(userRecord);
    }
}
