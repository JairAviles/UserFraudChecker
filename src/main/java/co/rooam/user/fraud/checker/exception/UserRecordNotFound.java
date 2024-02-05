package co.rooam.user.fraud.checker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserRecordNotFound extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public UserRecordNotFound(@Nullable String message) {
        super(message);
    }
}
