package vn.rananu.spring.mvc.exception;

import vn.rananu.spring.shared.exception.ClientException;

import java.util.Map;

public abstract class RestfulException extends ClientException {
    public RestfulException(Integer errorCode, String message) {
        super(errorCode, message);
    }

    public RestfulException(Integer errorCode, String message, Object... arguments) {
        super(errorCode, message, arguments);
    }

    public RestfulException(Integer errorCode, Map<String, String> errors) {
        super(errorCode, errors);
    }
}
