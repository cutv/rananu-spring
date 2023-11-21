package vn.rananu.spring.mvc.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.rananu.spring.mvc.validation.constraints.NullOrNotEmpty;

import java.util.List;

public class NullOrNotEmptyValidator implements ConstraintValidator<NullOrNotEmpty, List<?>> {
    @Override
    public boolean isValid(List<?> value, ConstraintValidatorContext context) {
        return value != null && !value.isEmpty();
    }
}
