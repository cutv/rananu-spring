package vn.rananu.spring.mvc.exception;

import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.rananu.spring.shared.exception.AbstractRestfulExceptionHandler;
import vn.rananu.spring.shared.exception.ClientException;

import java.util.Locale;

@RestControllerAdvice(annotations = {RestController.class})
public class GlobalRestfulExceptionHandler extends AbstractRestfulExceptionHandler {

    public GlobalRestfulExceptionHandler(MessageSource messageSource) {
        super(messageSource);
        if (logger.isDebugEnabled())
            logger.debug(String.format("Initialized %s", GlobalRestfulExceptionHandler.class.getSimpleName()));
    }

    @Override
    @ExceptionHandler({RestfulException.class})
    public ResponseEntity<?> handleClientException(ClientException ex, Locale locale) {
        if (logger.isErrorEnabled())
            logger.error(ex.getMessage());
        return super.handleClientException(ex, locale);
    }
}