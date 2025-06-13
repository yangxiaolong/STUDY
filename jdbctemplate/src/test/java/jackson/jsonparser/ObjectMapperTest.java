package jackson.jsonparser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Dragon
 */
public class ObjectMapperTest {

    @Test
    public void test1() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        // 绑定简单类型  和 Map类型
        Integer age = objectMapper.readValue("1", int.class);
        Map map = objectMapper.readValue("{\"name\":  \"YourBatman\"}", Map.class);
        System.out.println(age);
        System.out.println(map);
    }

    @Test
    public void test2() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        Person person = objectMapper.readValue("{\"name\":  \"YourBatman\", \"age\": 18}", Person.class);
        System.out.println(person);
    }

    @Test
    public void test3() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println("----------写简单类型----------");
        System.out.println(objectMapper.writeValueAsString(18));
        System.out.println(objectMapper.writeValueAsString("YourBatman"));

        System.out.println("----------写集合类型----------");
        System.out.println(objectMapper.writeValueAsString(Arrays.asList(1, 2, 3)));
        System.out.println(objectMapper.writeValueAsString(new HashMap<String, String>() {{
            put("zhName", "A哥");
            put("enName", "YourBatman");
        }}));

        System.out.println("----------写POJO----------");
        System.out.println(objectMapper.writeValueAsString(new Person("A哥", 18, null)));
    }

    @Test
    public void test4() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println("----------读简单类型----------");
        System.out.println(objectMapper.readValue("18", Integer.class));
        // 抛错：JsonParseException  单独的一个串，解析会抛错
        // System.out.println(objectMapper.readValue("YourBatman", String.class));

        System.out.println("----------读集合类型----------");
        System.out.println(objectMapper.readValue("[1,2,3]", List.class));
        System.out.println(objectMapper.readValue("{\"zhName\":\"A哥\",\"enName\":\"YourBatman\"}", Map.class));

        System.out.println("----------读POJO----------");
        System.out.println(objectMapper.readValue("{\"name\":\"A哥\",\"age\":18}", Person.class));
    }

    @Test
    public void test7() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println("----------读集合类型----------");
        List<Long> ids = objectMapper.readValue("[1,2,3]", new TypeReference<>() {
        });

        Long id = ids.get(0);
        System.out.println(id);
    }

    @Test
    public void test8() throws JsonProcessingException {
        JsonMapper jsonMapper = JsonMapper.builder()
                .configure(JsonReadFeature.ALLOW_SINGLE_QUOTES, true)
                .build();

        Person person = jsonMapper.readValue("{'name':  'YourBatman', 'age': 18}", Person.class);
        System.out.println(person);
    }

}