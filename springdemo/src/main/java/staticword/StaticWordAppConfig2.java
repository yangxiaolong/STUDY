package staticword;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther yangxiaolong
 * @create 2024/12/25
 */
@Configuration
public class StaticWordAppConfig2 {
    StaticWordAppConfig2() {
        System.out.println("StaticWordAppConfig2 init...");
    }

    @Bean
    static BeanPostProcessor postProcessor() {
        return new MyBeanPostProcessor2();
    }
}

class MyBeanPostProcessor2 implements BeanPostProcessor {
    MyBeanPostProcessor2() {
        System.out.println("MyBeanPostProcessor2 init...");
    }
}
