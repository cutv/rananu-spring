package vn.rananu.shared.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RananuBody {
    String value() default "";

    /**
     * The message to use for the @{@link vn.rananu.shared.Result} when request success.
     */
    String message() default "";

}
