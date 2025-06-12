package aop.jackson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.junit.Test;

import java.io.IOException;
import java.util.ServiceLoader;

/**
 * @author Dragon
 */
public class JacksonTest {

    @Test
    public void test1() throws IOException {
        String jsonStr = "{\"name\":\"YourBatman\",\"age\":18}";
        Person person = new Person();

        JsonFactory factory = new JsonFactory();
        try (JsonParser jsonParser = factory.createParser(jsonStr)) {
            // 只要还没结束"}"，就一直读
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String fieldname = jsonParser.currentName();
                if ("name".equals(fieldname)) {
                    jsonParser.nextToken();
                    person.setName(jsonParser.getText());
                } else if ("age".equals(fieldname)) {
                    jsonParser.nextToken();
                    person.setAge(jsonParser.getIntValue());
                }
            }

            System.out.println(person);
        }
    }

    @Test
    public void test3() throws IOException {
        String jsonStr = "{\"name\":\"YourBatman\",\"age\":18, \"pickName\":null}";

        JsonFactory factory = new JsonFactory();
        try (JsonParser jsonParser = factory.createParser(jsonStr)) {
            jsonParser.setCodec(new PersonObjectCodec());

            System.out.println(jsonParser.readValueAs(Person.class));
        }
    }

    @Test
    public void test2() throws IOException {
        String jsonStr = "{\"name\":\"YourBatman\",\"age\":18, \"pickName\":null}";
        System.out.println(jsonStr);
        JsonFactory factory = new JsonFactory();
        try (JsonParser jsonParser = factory.createParser(jsonStr)) {
            while (true) {
                JsonToken token = jsonParser.nextToken();
                System.out.println(token + " -> 值为:" + jsonParser.getValueAsString());
                if (token == JsonToken.END_OBJECT) {
                    break;
                }
            }
        }
    }

    //ALLOW_COMMENTS(false) 是否允许/* */或者//这种类型的注释出现。
    //❝自2.10版本后，使用JsonReadFeature#ALLOW_JAVA_COMMENTS代替
    @Test
    public void test4() throws IOException {
        String jsonStr = """
                {
                "name" : "YourBarman", // 名字
                "age" : 18 // 年龄
                }""";

        JsonFactory factory = new JsonFactory();
        try (JsonParser jsonParser = factory.createParser(jsonStr)) {
            // 开启注释支持
            jsonParser.enable(JsonParser.Feature.ALLOW_COMMENTS);

            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String fieldname = jsonParser.currentName();
                if ("name".equals(fieldname)) {
                    jsonParser.nextToken();
                    System.out.println(jsonParser.getText());
                } else if ("age".equals(fieldname)) {
                    jsonParser.nextToken();
                    System.out.println(jsonParser.getIntValue());
                }
            }
        }
    }

    //是否允许**反斜杠**转义任何字符
    @Test
    public void test5() throws IOException {
        String jsonStr = "{\"name\" : \"YourB\\'atman\" }";

        JsonFactory factory = new JsonFactory();
        try (JsonParser jsonParser = factory.createParser(jsonStr)) {
            jsonParser.enable(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER);

            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String fieldname = jsonParser.getCurrentName();
                if ("name".equals(fieldname)) {
                    jsonParser.nextToken();
                    System.out.println(jsonParser.getText());
                }
            }
        }
    }

    //INTERN_FIELD_NAMES(true)
    //这是Jackson所谓的key缓存：对JSON的「字段名」是否调用String#intern方法，放进字符串常量池里，以提高效率，默认是true。
    //小贴士：Jackson在调用String#intern之前使用InternCache（继承自ConcurrentHashMap）挡了一层，以防止高并发条件下intern效果不显著问题
    //「intern()方法的作用」这个老生常谈的话题了，解释为：当调用intern方法时，如果字符串池已经包含一个等于此String对象的字符串(内容相等)，
    // 则返回池中的字符串。否则，将此 String放进池子里。下面写个例子增加感受感受：
    //「值得注意的是：此特征必须是CANONICALIZE_FIELD_NAMES也为true（开启）的情况下才有效，否则是无效的。」
    @Test
    public void testStringIntern() {
        String str1 = "a";
        String str2 = "b";
        String str3 = "ab";
        String str4 = str1 + str2;
        String str5 = new String("ab");

        System.out.println(str5.equals(str3)); // true
        System.out.println(str5 == str3); // false

        // str5.intern()去常量池里找到了ab，所以直接返回常量池里的地址值了，因此是true
        System.out.println(str5.intern() == str3); // true
        System.out.println(str5.intern() == str4); // false
    }

    @Test
    public void test6() throws IOException {
        String jsonStr = "{\"age\":18, \"age\": 28 }";

        JsonFactory factory = new JsonFactory();
        //这里开启才能够生效
        //factory.enable(JsonParser.Feature.STRICT_DUPLICATE_DETECTION);

        try (JsonParser jsonParser = factory.createParser(jsonStr)) {
            // 使用factory定制将不生效, 这里开启是不生效的
            factory.enable(JsonParser.Feature.STRICT_DUPLICATE_DETECTION);

            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String fieldname = jsonParser.getCurrentName();
                if ("age".equals(fieldname)) {
                    jsonParser.nextToken();
                    System.out.println(jsonParser.getIntValue());
                }
            }
        }
    }

    @Test
    public void test7() throws IOException {
        JsonFactory jsonFactory = new JsonFactory();
        // jsonFactory自己的特征
        jsonFactory.enable(JsonFactory.Feature.INTERN_FIELD_NAMES);
        jsonFactory.enable(JsonFactory.Feature.CANONICALIZE_FIELD_NAMES);
        jsonFactory.enable(JsonFactory.Feature.USE_THREAD_LOCAL_FOR_BUFFER_RECYCLING);

        // JsonParser的特征
        jsonFactory.enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
        jsonFactory.enable(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER);

        // JsonGenerator的特征
        jsonFactory.enable(JsonGenerator.Feature.QUOTE_FIELD_NAMES);
        jsonFactory.enable(JsonGenerator.Feature.ESCAPE_NON_ASCII);

        // 创建读/写实例
        // jsonFactory.createParser(...);
        // jsonFactory.createGenerator(...);
    }

    //从源码包里发现，JsonFactory是支持Java SPI方式构建实例的。
    //META-INF/services/com.fasterxml.jackson.core.JsonFactory
    //因此，我可以使用Java SPI的方式得到一个JsonFactory实例：
    @Test
    public void test8() {
        ServiceLoader<JsonFactory> jsonFactories = ServiceLoader.load(JsonFactory.class);
        //遍历打印所有可用的JsonFactory实例
        for (JsonFactory jsonFactory : jsonFactories) {
            System.out.println(jsonFactory);
        }
    }

}
