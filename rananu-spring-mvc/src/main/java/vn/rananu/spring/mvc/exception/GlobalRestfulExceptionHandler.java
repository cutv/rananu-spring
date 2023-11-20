package vn.rananu.spring.mvc.exception;

import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.rananu.spring.shared.exception.ClientException;
import vn.rananu.spring.shared.exception.GlobalDefaultExceptionHandler;

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