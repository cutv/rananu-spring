package vn.rananu.shared.validation.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import vn.rananu.shared.validation.NullOrNotEmptyValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NullOrNotEmptyValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NullOrNotEmpty {
    String message() default "Must not be blank";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
