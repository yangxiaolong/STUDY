package jackson.jsonparser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author Dragon
 */
public class JsonNodeTest {

    @Test
    public void test2() {
        JsonNodeFactory factory = JsonNodeFactory.instance;

        System.out.println("------构建一个JSON结构数据------");
        ObjectNode rootNode = factory.objectNode();

        // 添加普通值节点
        rootNode.put("zhName", "A哥"); // 效果完全同：rootNode.set("zhName", factory.textNode("A哥"))
        rootNode.put("enName", "YourBatman");
        rootNode.put("age", 18);

        // 添加数组容器节点
        ArrayNode arrayNode = factory.arrayNode();
        arrayNode.add("java")
                .add("javascript")
                .add("python");
        rootNode.set("languages", arrayNode);

        // 添加对象节点
        ObjectNode dogNode = factory.objectNode();
        dogNode.put("name", "大黄")
                .put("age", 3);
        rootNode.set("dog", dogNode);

        System.out.println(rootNode);
        System.out.println(rootNode.get("dog").get("name"));
    }

    @Test
    public void test1() {
        ObjectMapper mapper = new ObjectMapper();

        Person person = new Person();
        person.setName("YourBatman");
        person.setAge(18);

        person.setDog(new Dog("旺财", 3));

        JsonNode node = mapper.valueToTree(person);

        System.out.println(person);
        // 遍历打印所有属性
        for (JsonNode nextNode : node) {
            if (nextNode.isContainerNode()) {
                if (nextNode.isObject()) {
                    System.out.println("狗的属性：：：");

                    System.out.println(nextNode.get("name"));
                    System.out.println(nextNode.get("age"));
                }
            } else {
                System.out.println(nextNode.asText());
            }
        }

        // 直接获取
        System.out.println("---------------------------------------");
        System.out.println(node.get("dog").get("name"));
        System.out.println(node.get("dog").get("age"));
    }

    //1、偌大JSON串中仅需1个值
    //我仅关心狗的颜色
    @Test
    public void test4() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String jsonStr = "{\"name\":\"YourBatman\",\"age\":18,\"dog\":{\"name\":\"旺财\",\"color\":\"WHITE\"},\"hobbies\":[\"篮球\",\"football\"]}";
        JsonNode node = mapper.readTree(jsonStr);

        System.out.println(node.get("dog").get("color").asText());
    }

    //2.数据结构高度动态化
    //当数据结构高度动态化（随时可能新增、删除节点）时，使用树模型去处理是一个较好的方案（稳定之后再转为Java Bean即可
    @Test
    public void test5() throws JsonProcessingException {
        String jsonStr = "{\"name\":\"YourBatman\",\"age\":18}";

        JsonNode node = new ObjectMapper().readTree(jsonStr);

        System.out.println("-------------向结构里动态添加节点------------");
        // 动态添加一个myDiy节点，并且该节点还是ObjectNode节点
        node.withObject("myDiy").put("contry", "China");

        System.out.println(node);
    }

}