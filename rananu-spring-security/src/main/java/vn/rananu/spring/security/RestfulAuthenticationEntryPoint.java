package vn.rananu.spring.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import vn.rananu.spring.shared.Result;

import java.io.IOException;

@RequiredArgsConstructor
public class RestfulAuthenticationEntryPoint implements AuthenticationEntryPoint {
    protected final MessageSource messageSource;
    protected final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String message = messageSource.getMessage("error.401", new Object[0], LocaleContextHolder.getLocale());
        Result<?> result = Result.fail()
                .message(message)
                .build();
        ServletOutputStream out = response.getOutputStream();
        objectMapper.writeValue(out, result);
        out.flush();
    }
}
