package vn.rananu.spring.mvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends RestfulException {
    public ValidationException(Map<String, String> errors) {
        super(HttpStatus.BAD_REQUEST.value(), errors);
    }


    public ValidationException(String field, String message) {
        this(new HashMap<>() {{
            put(field, message);
        }});
    }

    public ValidationException(String message) {
        super(HttpStatus.BAD_REQUEST.value(), message);
    }

    public ValidationException(String message, Object... arguments) {
        super(HttpStatus.BAD_REQUEST.value(), message, arguments);
    }
}
