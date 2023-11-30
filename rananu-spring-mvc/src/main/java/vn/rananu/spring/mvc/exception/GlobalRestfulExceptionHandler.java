package vn.rananu.spring.mvc.exception;

import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.rananu.spring.shared.exception.ClientException;
import vn.rananu.spring.shared.exception.AbstractRestfulExceptionHandler;

import java.util.Locale;

@RestControllerAdvice(assignableTypes = RestController.class)
public class GlobalRestfulExceptionHandler extends AbstractRestfulExceptionHandler {

    public GlobalRestfulExceptionHandler(MessageSource messageSource) {
        super(messageSource);
    }

    @Override
    @ExceptionHandler({RestfulException.class})
    public ResponseEntity<?> handleClientException(ClientException ex, Locale locale) {
        return super.handleClientException(ex, locale);
    }
}