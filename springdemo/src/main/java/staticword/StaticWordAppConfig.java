package staticword;

/**
 * @auther yangxiaolong
 * @create 2024/12/25
 */

import aop.Parent;
import aop.Son;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StaticWordAppConfig {

    StaticWordAppConfig() {
        System.out.println("StaticWordAppConfig init...");
    }

    @Bean
    BeanPostProcessor postProcessor() {
        return new MyBeanPostProcessor();
    }

    @Bean
    Son son() {
        return new Son();
    }

    @Bean
    Parent parent() {
        return new Parent(son());
    }

    @Bean
    BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor() {
        return new MyBeanDefinitionRegistryPostProcessor();
    }

}

class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    MyBeanDefinitionRegistryPostProcessor() {
        System.out.println("MyBeanDefinitionRegistryPostProcessor init...");
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }

}

class MyBeanPostProcessor implements BeanPostProcessor {
    MyBeanPostProcessor() {
        System.out.println("MyBeanPostProcessor init...");
    }
}
