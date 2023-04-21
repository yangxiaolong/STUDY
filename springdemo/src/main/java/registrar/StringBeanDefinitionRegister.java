package registrar;

import importselector.StringBeanRegister;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.stream.Stream;

/**
 * @author yangxiaolong
 */
public class StringBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(
            AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        StringBeanRegister register = new StringBeanRegister();
        String[] beanName = register.selectImports(importingClassMetadata);

        Stream.of(beanName)
                .map(BeanDefinitionBuilder::genericBeanDefinition)
                .map(BeanDefinitionBuilder::getBeanDefinition)
                .forEach(s -> BeanDefinitionReaderUtils.registerWithGeneratedName(s, registry));
    }
}
