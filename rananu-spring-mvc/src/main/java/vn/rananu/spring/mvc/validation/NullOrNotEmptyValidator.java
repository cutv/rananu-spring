package vn.rananu.spring.mvc.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.rananu.spring.mvc.validation.constraints.NullOrNotEmpty;

import java.util.Collection;
import java.util.List;

public class NullOrNotEmptyValidator implements ConstraintValidator<NullOrNotEmpty, Collection<?>> {
    @Override
    public boolean isValid(Collection<?> value, ConstraintValidatorContext context) {
        return value != null && !value.isEmpty();
    }
}
