package vn.rananu.spring.security.exception;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.rananu.spring.shared.Result;
import vn.rananu.spring.shared.exception.ClientException;
import vn.rananu.spring.shared.exception.GlobalDefaultExceptionHandler;

import java.util.Locale;

@RestControllerAdvice
public class GlobalSecurityExceptionHandler extends GlobalDefaultExceptionHandler {
    protected GlobalSecurityExceptionHandler(MessageSource messageSource) {
        super(messageSource);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<?> handleException(AccessDeniedException exception, Locale locale) {
        String message = messageSource.getMessage("error.403", new Object[0], locale);
        Result<?> result = Result.fail()
                .message(message)
                .build();
        return new ResponseEntity<>(result, HttpStatus.FORBIDDEN);
    }

    @Override
    @ExceptionHandler({SecurityException.class})
    public ResponseEntity<?> handleClientException(ClientException ex, Locale locale) {
        return super.handleClientException(ex, locale);
    }
}