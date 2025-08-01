package jackson.annotation;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class Annotation {

    @Test
    public void testJsonAnyGetter() throws JsonProcessingException {
        ExtendableBean bean = new ExtendableBean("My Bean",
                Map.of("attr1", "val1", "attr2", "val2"));
        String s = new ObjectMapper().writeValueAsString(bean);
        System.out.println(s);
    }

    @Test
    public void testJsonValue() throws JsonProcessingException {
        String s = new ObjectMapper().writeValueAsString(TypeEnumWithValue.TYPE1);
        System.out.println(s);
    }

}

class ExtendableBean {

    public ExtendableBean() {
    }

    public ExtendableBean(String name, Map<String, String> properties) {
        this.name = name;
        this.properties = properties;
    }

    public String name;

    //    @JsonIgnore
    public Map<String, String> properties;

    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return properties;
    }

    @JsonAnySetter
    public void add(String key, String value) {
        properties.put(key, value);
    }
}

@AllArgsConstructor
enum TypeEnumWithValue {
    TYPE1(1, "Type A"), TYPE2(2, "Type B");

    private Integer id;
    private String name;

    @JsonValue
    public String getName() {
        return name;
    }

}
