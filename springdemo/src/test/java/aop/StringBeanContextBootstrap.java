package aop;

import enable.EnableStringBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangxiaolong
 */
@Configuration
@EnableStringBean
public class StringBeanContextBootstrap {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(StringBeanContextBootstrap.class);
        String stringBean = context.getBean("stringBean", String.class);
        System.out.println(stringBean);
    }

}
