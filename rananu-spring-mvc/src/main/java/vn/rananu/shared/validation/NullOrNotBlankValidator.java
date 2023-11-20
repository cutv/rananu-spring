package vn.rananu.shared.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.rananu.shared.validation.constraints.NullOrNotBlank;


public class NullOrNotBlankValidator implements ConstraintValidator<NullOrNotBlank, String> {

    public void initialize(NullOrNotBlank parameters) {
        // Nothing to do here
    }

    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || !value.trim().isEmpty();
    }
}