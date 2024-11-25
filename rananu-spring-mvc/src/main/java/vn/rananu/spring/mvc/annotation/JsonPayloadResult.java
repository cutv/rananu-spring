package vn.rananu.spring.mvc.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface JsonPayloadResult {
    String value() default "";

    /**
     * The message to use for the @{@link vn.rananu.spring.shared.Result} when request success.
     */
    String message() default "";

}
