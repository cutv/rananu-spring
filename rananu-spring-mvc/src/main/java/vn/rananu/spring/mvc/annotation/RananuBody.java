package vn.rananu.spring.mvc.annotation;

import vn.rananu.spring.shared.Result;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RananuBody {
    String value() default "";

    /**
     * The message to use for the @{@link Result} when request success.
     */
    String message() default "";

}
