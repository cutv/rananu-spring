package vn.rananu.shared;


import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.Map;

@Getter
public class Result<T> {
    private final T data;
    private final Integer errorCode;
    private final String message;
    private final Map<String, String> errors;

    private Result(T data, Integer errorCode, String message, Map<String, String> errors) {
        this.data = data;
        this.errorCode = errorCode;
        this.message = message;
        this.errors = errors;
    }


    public interface ResultBuilder {
        ResultBuilder message(String message);

        <T> Result<T> build();
    }

    public interface SuccessBuilder extends ResultBuilder {
        <T> SuccessBuilder data(T data);
    }

    public interface FailureBuilder extends ResultBuilder {
        FailureBuilder errorCode(Integer errorCode);

        FailureBuilder errors(Map<String, String> errors);
    }

    @Accessors(chain = true)
    private static class DefaultBuilder implements SuccessBuilder, FailureBuilder {
        protected Object data;
        protected Integer errorCode;
        protected String message;
        protected Map<String, String> errors;

        @Override
        public <T> SuccessBuilder data(T data) {
            this.data = data;
            return this;
        }

        @Override
        public SuccessBuilder message(String message) {
            this.message = message;
            return this;
        }

        @Override
        public FailureBuilder errorCode(Integer errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        @Override
        public FailureBuilder errors(Map<String, String> errors) {
            this.errors = errors;
            return this;
        }


        @Override
        public <T> Result<T> build() {
            return new Result<>((T) data, errorCode, message, errors);
        }
    }

    public static SuccessBuilder success() {
        return new DefaultBuilder();
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(data, null, null, null);
    }

    public static FailureBuilder fail() {
        return new DefaultBuilder();
    }


}
