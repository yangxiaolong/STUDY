package jackson.jsonparser;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author Dragon
 */
public class JacksonParserTest {

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


    //com.fasterxml.jackson.core.JsonParser.Feature

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

    @Test
    public void test6_() throws IOException {
        String jsonStr = "{\"names\" : [\"YourBatman\",,\"A哥\",,] }";

        JsonFactory factory = new JsonFactory();
        try (JsonParser jsonParser = factory.createParser(jsonStr)) {
            jsonParser.enable(JsonParser.Feature.ALLOW_MISSING_VALUES);

            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String fieldname = jsonParser.getCurrentName();
                if ("names".equals(fieldname)) {
                    jsonParser.nextToken();

                    while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                        System.out.println(jsonParser.getText());
                    }
                }
            }
        }
    }

    //ALLOW_TRAILING_COMMA(false)
    //是否允许最后一个多余的逗号（一定是最后一个）。这个特征是「非常重要」的，若开关打开，有如下效果：
    //
    //[true,true,]等价于[true, true]
    //{"a": true,}等价于{"a": true}
    //
    //举个例子：当然这两个特征开关都打开时，[true,true,]等价于[true, true]好理解；
    // 「并且呢，[true,true,,]是等价于[true, true, null]的哦，可千万别忽略最后的这个null」。

    @Test
    public void test7() throws IOException {
        String jsonStr = "{\"results\" : [true,true,,] }";

        JsonFactory factory = new JsonFactory();
        try (JsonParser jsonParser = factory.createParser(jsonStr)) {
//            jsonParser.enable(JsonReadFeature.ALLOW_MISSING_VALUES.mappedFeature());
            jsonParser.enable(JsonParser.Feature.ALLOW_MISSING_VALUES);
            jsonParser.enable(JsonParser.Feature.ALLOW_TRAILING_COMMA);

            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String fieldname = jsonParser.getCurrentName();
                if ("results".equals(fieldname)) {
                    jsonParser.nextToken();

                    while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                        System.out.println(jsonParser.getBooleanValue());
                    }
                }
            }
        }
    }

    //STRICT_DUPLICATE_DETECTION(false)
    //❝自2.10版本后，使用StreamReadFeature#STRICT_DUPLICATE_DETECTION代替❞
    //是否允许JSON串有两个相同的属性key，默认是「允许的」
    @Test
    public void test8() throws IOException {
        String jsonStr = "{\"age\":18, \"age\": 28 }";

        JsonFactory factory = new JsonFactory();
        try (JsonParser jsonParser = factory.createParser(jsonStr)) {
            //打开报错
            //jsonParser.enable(JsonParser.Feature.STRICT_DUPLICATE_DETECTION);

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

}