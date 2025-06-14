package configuration;

import org.junit.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

public class AppConfigTest {

    @Test
    public void test1() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig appConfig = context.getBean(AppConfig.class);
        System.out.println();

        System.out.println(appConfig.getClass());
        System.out.println(appConfig.getClass().getSuperclass() == AppConfig.class);
        System.out.println(AopUtils.isCglibProxy(appConfig));
    }

    @Test
    public void test2() throws IllegalAccessException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig appConfig = context.getBean(AppConfig.class);
        Field $$beanFactoryField = ReflectionUtils.findField(appConfig.getClass(), "$$beanFactory");
        assert $$beanFactoryField != null;
        BeanFactory beanFactory = (BeanFactory) $$beanFactoryField.get(appConfig);
        System.out.println();

        System.out.println(beanFactory == context.getAutowireCapableBeanFactory());
        System.out.println(beanFactory == context);
        System.out.println(appConfig instanceof BeanFactoryAware);
        System.out.println(appConfig.getClass().getInterfaces()[0]);
    }

}
