package vn.rananu.spring.mvc.advice;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import vn.rananu.spring.shared.Result;
import vn.rananu.spring.mvc.annotation.RananuBody;

import java.util.Locale;

@org.springframework.web.bind.annotation.RestControllerAdvice
@RequiredArgsConstructor
public class RestControllerAdvice implements ResponseBodyAdvice<Object> {
    private final MessageSource messageSource;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        RananuBody annotation = returnType.getMethodAnnotation(RananuBody.class);
        return annotation != null;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        RananuBody annotation = returnType.getMethodAnnotation(RananuBody.class);
        if (annotation != null) {
            Result.SuccessBuilder builder = Result.success();
            String message = annotation.message();
            if (message != null && !message.isEmpty()) {
                //            List<Locale.LanguageRange> languageRanges = request.getHeaders().getAcceptLanguage();
//            languageRanges.isEmpty()
//                    ? Locale.getDefault()
//                    : Locale.lookup(languageRanges, LOCALES);
                Locale locale = LocaleContextHolder.getLocale();
                message = messageSource.getMessage(message, new Object[0], locale);
                builder.message(message);
            }

            return builder.data(body)
                    .build();
        }
        return body;
    }
}
