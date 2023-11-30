package vn.rananu.spring.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import vn.rananu.spring.shared.exception.ClientException;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends ClientException {
    private static final long serialVersionUID = -7677877495802788423L;

    public ForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN.value(), message);
    }

}
