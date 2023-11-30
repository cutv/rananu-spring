package vn.rananu.spring.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import vn.rananu.spring.shared.exception.ClientException;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthorizationException extends ClientException {
    public AuthorizationException(String message) {
        super(HttpStatus.UNAUTHORIZED.value(), message);
    }
}
