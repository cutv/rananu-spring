package vn.rananu.spring.shared;


import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

@Getter
public class Result<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 8441050065523394019L;
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

    public interface ResultBuilder<T> {
        ResultBuilder<T> message(String message);

        Result<T> build();
    }

    public interface SuccessBuilder<T> extends ResultBuilder<T> {
        SuccessBuilder<T> data(T data);
    }

    public interface FailureBuilder<T> extends ResultBuilder<T> {
        FailureBuilder<T> errorCode(Integer errorCode);

        FailureBuilder<T> errors(Map<String, String> errors);
    }

    @Accessors(chain = true)
    private static class DefaultBuilder<T> implements SuccessBuilder<T>, FailureBuilder<T> {
        protected T data;
        protected Integer errorCode;
        protected String message;
        protected Map<String, String> errors;

        @Override
        public SuccessBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        @Override
        public ResultBuilder<T> message(String message) {
            this.message = message;
            return this;
        }

        @Override
        public FailureBuilder<T> errorCode(Integer errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        @Override
        public FailureBuilder<T> errors(Map<String, String> errors) {
            this.errors = errors;
            return this;
        }

        @Override
        public Result<T> build() {
            return new Result<>(data, errorCode, message, errors);
        }
    }

    public static <T> SuccessBuilder<T> success() {
        return new DefaultBuilder<>();
    }

    public static <T> FailureBuilder<T> fail() {
        return new DefaultBuilder<>();
    }
}
