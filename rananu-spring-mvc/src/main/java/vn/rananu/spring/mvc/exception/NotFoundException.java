package vn.rananu.spring.mvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RestfulException {
    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND.value(), message);
    }
}