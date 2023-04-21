package distconfig;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @author yangxiaolong
 */
public class CustomerTagParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return User.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        String id = element.getAttribute("id");
        String name = element.getAttribute("name");
        if (id != null) {
            builder.addPropertyValue("id", Integer.valueOf(id));
        }
        if (StringUtils.hasText(name)) {
            builder.addPropertyValue("name", name);
        }
    }

}
