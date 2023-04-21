package importselector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * @author yangxiaolong
 */
public class StringBeanRegister implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> attributes = importingClassMetadata
                .getAnnotationAttributes(EnableStringBean.class.getName());
        StringBean.StringType type = (StringBean.StringType) attributes.get("type");
        switch (type) {
            case HELLO_WORLD:
                return new String[]{HelloWorldBean.class.getName()};
            case WORLD_HELLO:
                return new String[]{WorldHelloBean.class.getName()};
            default:
                return null;
        }
    }
}
