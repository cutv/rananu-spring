package vn.rananu.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthorizationException extends SecurityException {
    public AuthorizationException(String message) {
        super(HttpStatus.UNAUTHORIZED.value(), message);
    }
}
