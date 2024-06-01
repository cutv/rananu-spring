package vn.rananu.spring.mvc.validation;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import vn.rananu.spring.mvc.exception.ValidationException;

import java.util.Map;
import java.util.stream.Collectors;

public class ValidationExtension {
    public static void validate(Validator validator, Object object) {
        BindingResult binding = new BeanPropertyBindingResult(object, "parameter");
        validator.validate(object, binding);
        if (binding.hasErrors()) {
            Map<String, String> errors = binding.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            throw new ValidationException(errors);
        }
    }
}
