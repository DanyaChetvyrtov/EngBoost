package app.first.myEng.engBoost.validation;

import app.first.myEng.engBoost.models.exception.InnerServerError;
import app.first.myEng.engBoost.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Optional;

@Component
public class UniqueUserFieldValidator implements ConstraintValidator<UniqueUserField, String> {
    private final UserRepository userRepository;

    private String fieldName;


    public UniqueUserFieldValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UniqueUserField constraintAnnotation) {
        fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;

        try {
            Method method = userRepository.getClass().getMethod("findBy" + capitalize(fieldName), String.class);
            Object res = method.invoke(userRepository, value);
            return ((Optional<?>) res).isEmpty();
        } catch (Exception e) {
            throw new InnerServerError("Error while checking if field " + fieldName + " is unique");
        }
    }

    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
