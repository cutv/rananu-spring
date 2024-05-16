package vn.rananu.spring.shared.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public abstract class ClientException extends RuntimeException {
    //must is number: status =200 || xxxx
    protected Integer errorCode;
    protected Map<String, String> errors;
    protected Object[] arguments;

    private ClientException(Integer errorCode, Map<String, String> errors, String message, Object... arguments) {
        super(message);
        this.errorCode = errorCode;
        this.errors = errors;
        this.arguments = arguments;
    }

    public ClientException(Integer errorCode, String message) {
        this(errorCode, null, message, new Object[0]);
    }

    public ClientException(Integer errorCode, String message, Object... arguments) {
        this(errorCode, null, message, arguments);
    }

    public ClientException(Integer errorCode, Map<String, String> errors) {
        this(errorCode, errors, null);
    }

}
