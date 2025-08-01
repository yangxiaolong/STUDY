package jackson.xml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * @auther yangxiaolong
 * @create 2025/8/1
 */
public class SimpleBeanTest {

    @Test
    public void beanToXmlString() throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writeValueAsString(new SimpleBean());
        System.out.println(xml);
    }

    @Test
    public void beanToXmlFile() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(new File("target/simple_bean.xml"), new SimpleBean());
        File file = new File("target/simple_bean.xml");
        System.out.println(file);
    }

    @Test
    public void stringToXmlBean() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        SimpleBean value = xmlMapper.readValue("<SimpleBean><x>1</x><y>2</y></SimpleBean>", SimpleBean.class);
        System.out.println(value);
    }

    @Test
    public void xmlFileBeanTo() throws IOException {
        File file = new File("target/simple_bean.xml");
        XmlMapper xmlMapper = new XmlMapper();
        SimpleBean bean = xmlMapper.readValue(file, SimpleBean.class);
        System.out.println(bean);
    }

}
