package jackson.treemodule;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jackson.jsonparser.Dog;
import jackson.jsonparser.Person;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @auther yangxiaolong
 * @create 2025/6/14
 */
public class JsonNodeTest {

    //JsonNode
    //JsonNode是所有JSON节点的基类，它是一个抽象类，它有一个较大的特点：
    // 绝大多数的get方法均放在了此抽象类里（即使它没有实现），
    // 目的是：「在不进行类型强制转换的情况下遍历结构」。
    // 但是，大多数的「修改方法」都必须通过特定的子类类型去调用，这其实是合理的


    //JsonNodeFactory

    //值类型节点（ValueNode）
    @Test
    public void test1() {
        JsonNodeFactory factory = JsonNodeFactory.instance;

        System.out.println("------ValueNode值节点示例------");
        // 数字节点
        JsonNode node = factory.numberNode(1);
        System.out.println(node.isNumber() + ":" + node.intValue());

        // null节点
        node = factory.nullNode();
        System.out.println(node.isNull() + ":" + node.asText());

        // missing节点
        node = factory.missingNode();
        System.out.println(node.isMissingNode() + "_" + node.asText());

        // POJONode节点
        node = factory.pojoNode(new Person("YourBatman", 18, null));
        System.out.println(node.isPojo() + ":" + node.asText());

        System.out.println("---" + node.isValueNode() + "---");
    }

    //容器类型节点（ContainerNode）
    //此类节点均为ContainerNode的子类，特点是：本节点代表一个容器，里面可以装任何其它节点。
    //
    //Java中容器有两种：Map和Collection。对应的Jackson也提供了两种容器节点用于表述此类数据结构：
    //
    //ObjectNode：类比Map，采用K-V结构存储。比如一个JSON结构，「根节点」 就是一个ObjectNode
    //ArrayNode：类比Collection、数组。里面可以放置任何节点
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

    //ObjectMapper中的树模型

    //底层流式API仅定义了接口而并未提供任何实现，甚至半成品都算不上。
    // 所以说要使用Jackson的树模型还得看ObjectMapper，它提供了TreeNode等API的完整实现

    //ObjectMapper中提供了树模型(tree model) API 来生成和解析 json 字符串。
    // 如果你不想为你的 json 结构单独建类与之对应的话，则可以选择该 API

    //写（序列化）
    //将Object写为JsonNode，ObjectMapper给我们提供了三个实用API俩操作它：
    //1、valueToTree(Object)
    @Test
    public void test1_() {
        ObjectMapper mapper = new ObjectMapper();

        Person person = new Person("YourBatman", 18, new Dog("旺财", 3));
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

    //2、writeTree(JsonGenerator, JsonNode)
    @Test
    public void test2_() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        JsonFactory factory = new JsonFactory();
        try (JsonGenerator jsonGenerator = factory.createGenerator(System.err, JsonEncoding.UTF8)) {

            // 1、得到一个jsonNode（为了方便我直接用上面API生成了哈）
            Person person = new Person();
            person.setName("YourBatman");
            person.setAge(18);
            JsonNode jsonNode = mapper.valueToTree(person);

            // 使用JsonGenerator写到输出流
            mapper.writeTree(jsonGenerator, jsonNode);
        }
    }

    //3、writeTree(JsonGenerator,TreeNode)


    //读（反序列化）
    //至于底层_readTreeAndClose(JsonParser)方法的具体实现，就有得捞了。不过鉴于它过于枯燥和稍有些烧脑
    @Test
    public void test3() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String jsonStr = "{\"name\":\"YourBatman\",\"age\":18,\"dog\":null}";
        // 直接映射为一个实体对象
        // mapper.readValue(jsonStr, Person.class);
        // 读取为一个树模型
        JsonNode node = mapper.readTree(jsonStr);
        System.out.println(node);

        // ... 略
    }

    //场景演练
    //1、偌大JSON串中仅需1个值

    //我仅关心狗的颜色
    //当你不想创建一个Java Bean与JSON属性相对应时，树模型的「所见即所得」特性就很好解决了这个问题
    @Test
    public void test4() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String jsonStr = "{\"name\":\"YourBatman\",\"age\":18,\"dog\":{\"name\":\"旺财\"," +
                "\"color\":\"WHITE\"},\"hobbies\":[\"篮球\",\"football\"]}";
        JsonNode node = mapper.readTree(jsonStr);

        System.out.println(node.get("dog").get("color").asText());
    }


    //2、数据结构高度动态化
    //当数据结构高度动态化（随时可能新增、删除节点）时，使用树模型去处理是一个较好的方案（稳定之后再转为Java Bean即可
    @Test
    public void test5() throws JsonProcessingException {
        String jsonStr = "{\"name\":\"YourBatman\",\"age\":18}";

        JsonNode node = new ObjectMapper().readTree(jsonStr);

        System.out.println("-------------向结构里动态添加节点------------");
        // 动态添加一个myDiy节点，并且该节点还是ObjectNode节点
        node.withObject("myDiy").put("country", "China");

        System.out.println(node);
    }

}
