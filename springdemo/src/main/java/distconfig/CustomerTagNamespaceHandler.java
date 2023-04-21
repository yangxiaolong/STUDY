package distconfig;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author yangxiaolong
 */
public class CustomerTagNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("user", new CustomerTagParser());
    }

}