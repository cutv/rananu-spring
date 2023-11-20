package vn.rananu.shared.exception;

import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RestControllerAdvice
public class GlobalRestfulExceptionHandler extends GlobalDefaultExceptionHandler {

    public GlobalRestfulExceptionHandler(MessageSource messageSource) {
        super(messageSource);
    }

    @Override
    @ExceptionHandler({RestfulException.class})
    public ResponseEntity<?> handleClientException(ClientException ex, Locale locale) {
        return super.handleClientException(ex, locale);
    }
}