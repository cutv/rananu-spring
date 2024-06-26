package vn.rananu.spring.mvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OperationException extends RestfulException {
    public OperationException(String message) {
        super(HttpStatus.BAD_REQUEST.value(), message);
    }

    public OperationException(String message, Object... arguments) {
        super(HttpStatus.BAD_REQUEST.value(), message, arguments);
    }
}
