package validation.basic;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.executable.ExecutableValidator;

import java.util.Set;

/**
 * @author Dragon
 */
public class ValidatorUtil {

    public static ValidatorFactory obtainValidatorFactory() {
        return Validation.buildDefaultValidatorFactory();
    }

    public static Validator obtainValidator() {
        return obtainValidatorFactory().getValidator();
    }

    public static ExecutableValidator obtainExecutableValidator() {
        return obtainValidator().forExecutables();
    }

    public static <T> void printViolations(Set<ConstraintViolation<T>> violations) {
        violations.stream()
                .map(v -> v.getPropertyPath() + " " + v.getMessage() + " ---Your value: " + v.getInvalidValue())
                .forEach(System.out::println);
    }

}
