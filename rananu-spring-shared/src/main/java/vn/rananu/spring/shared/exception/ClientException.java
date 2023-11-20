package vn.rananu.spring.shared.exception;

import java.util.Map;

public abstract class ClientException extends RuntimeException {
    //must is number: status =200 || xxxx
    protected Integer errorCode;
    protected Map<String, String> errors;

    private ClientException(Integer errorCode, Map<String, String> errors, String message) {
        super(message);
        this.errors = errors;
        this.errorCode = errorCode;
    }

    public ClientException(Integer errorCode, String message) {
        this(errorCode, null, message);
    }

    public ClientException(Integer errorCode, Map<String, String> errors) {
        this(errorCode, errors, null);
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
