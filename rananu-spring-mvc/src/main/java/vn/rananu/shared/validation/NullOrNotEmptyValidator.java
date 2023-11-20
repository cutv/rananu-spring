package vn.rananu.shared.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.rananu.shared.validation.constraints.NullOrNotEmpty;

import java.util.List;

public class NullOrNotEmptyValidator implements ConstraintValidator<NullOrNotEmpty, List<Object>> {
    @Override
    public boolean isValid(List<Object> value, ConstraintValidatorContext context) {
        return !value.isEmpty();
    }
}
