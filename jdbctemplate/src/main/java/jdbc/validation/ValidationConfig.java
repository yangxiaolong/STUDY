package jdbc.validation;

import org.springframework.boot.autoconfigure.validation.ValidationConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dragon
 */
@Configuration
public class ValidationConfig {

    @Bean
    public ValidationConfigurationCustomizer validationConfigurationCustomizer() {
        return configuration -> configuration.addValueExtractor(new ResultValueExtractor());
    }

}
