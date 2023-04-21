package enable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangxiaolong
 */
@Configuration
public class StringBean {

    @Bean
    public String stringBean() {
        return "Hello,world";
    }

}
