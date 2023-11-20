package vn.rananu.security.exception;

import vn.rananu.shared.exception.ClientException;

import java.util.Map;

public abstract class SecurityException extends ClientException {
    public SecurityException(Integer errorCode, String message) {
        super(errorCode, message);
    }

    public SecurityException(Integer errorCode, Map<String, String> errors) {
        super(errorCode, errors);
    }
}
