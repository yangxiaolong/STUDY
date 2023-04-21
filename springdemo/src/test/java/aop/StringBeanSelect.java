package aop;

import importselector.EnableStringBean;
import importselector.StringBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangxiaolong
 */
@Configuration
@EnableStringBean(type = StringBean.StringType.HELLO_WORLD)
public class StringBeanSelect {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(StringBeanSelect.class);
        StringBean bean = context.getBean(StringBean.class);
        System.out.println(bean.getString());
    }

}
