package importselector;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(StringBeanRegister.class)
//@Import(StringBeanDefinitionRegister.class)
public @interface EnableStringBean {

    StringBean.StringType type();

}
