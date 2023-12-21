package vn.rananu.spring.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import vn.rananu.spring.shared.Result;

import java.io.IOException;

@RequiredArgsConstructor
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    protected final MessageSource messageSource;
    protected final ObjectMapper objectMapper;
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String message = messageSource.getMessage("error.403", new Object[0], LocaleContextHolder.getLocale());
        if (logger.isDebugEnabled())
            logger.debug(String.format("Request uri:%s - status code:%s - %s", request.getRequestURI(), response.getStatus(), message));
        if (logger.isErrorEnabled())
            logger.error(ex.getMessage(), ex);
        Result<?> result = Result.fail()
                .message(message)
                .build();
        ServletOutputStream out = response.getOutputStream();
        objectMapper.writeValue(out, result);
        out.flush();
    }
}
