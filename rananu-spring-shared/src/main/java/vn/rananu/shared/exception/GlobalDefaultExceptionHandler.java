package vn.rananu.shared.exception;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import vn.rananu.shared.Result;

import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;


public abstract class GlobalDefaultExceptionHandler extends ResponseEntityExceptionHandler {
    protected final MessageSource messageSource;

    protected GlobalDefaultExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
        setMessageSource(messageSource);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        BindingResult binding = ex.getBindingResult();
        Map<String, String> errors = binding.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        Result<?> result = Result.fail().errorCode(HttpStatus.BAD_REQUEST.value()).errors(errors).build();
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> handleClientException(ClientException ex, Locale locale) {
        String message = ex.getMessage();
        if (message != null) message = messageSource.getMessage(message, new Object[0], locale);
        Map<String, String> errors = ex.getErrors();
        if (errors != null)
            errors = errors.entrySet().stream().collect(Collectors.toMap(
                    Map.Entry::getKey, entry ->
                            messageSource.getMessage(entry.getValue(), new Object[0], locale))
            );

        Result<?> result = Result.fail()
                .errorCode(ex.getErrorCode())
                .errors(errors)
                .message(message)
                .build();
        ResponseStatus annotation = ex.getClass().getAnnotation(ResponseStatus.class);
        HttpStatus status = annotation.value();
        return new ResponseEntity<>(result, status);
    }
}