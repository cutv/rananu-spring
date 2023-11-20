package vn.rananu.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends SecurityException {
    private static final long serialVersionUID = -7677877495802788423L;

    public ForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN.value(), message);
    }

}
