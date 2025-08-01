package jackson.xml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * @auther yangxiaolong
 * @create 2025/8/1
 */
public class SimpleBeanForCapitalizedFieldsTest {

    @Test
    public void toBean() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        SimpleBeanForCapitalizedFields value = xmlMapper.readValue
                ("<SimpleBeanForCapitalizedFields><x>1</x><y>2</y></SimpleBeanForCapitalizedFields>",
                        SimpleBeanForCapitalizedFields.class);
        System.out.println(value);
    }

    @Test
    public void toXmlFile() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        File file = new File("target/simple_bean_capitalized.xml");
        xmlMapper.writeValue(file, new SimpleBeanForCapitalizedFields());
    }
}
