import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class JacksonTest {

    @Test
    public void test1() throws IOException {
        JsonFactory factory = new JsonFactory();
        // 本处只需演示，向控制台写（当然你可以向文件等任意地方写都是可以的）

        try (JsonGenerator jsonGenerator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
            jsonGenerator.writeStartObject(); //开始写，也就是这个符号 {

            jsonGenerator.writeStringField("name", "YourBatman");
            jsonGenerator.writeNumberField("age", 18);

            jsonGenerator.writeEndObject(); //结束写，也就是这个符号 }
        }
    }

    @Test
    public void test5() throws IOException {
        JsonFactory factory = new JsonFactory();
        try (JsonGenerator jsonGenerator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
            jsonGenerator.writeStartObject();

            jsonGenerator.writeFieldName("zhName");
            jsonGenerator.writeString("A哥");

            // 写数组（记得先写key 否则无效）
            jsonGenerator.writeFieldName("objects");
            jsonGenerator.writeStartArray();
            // 1、写字符串
            jsonGenerator.writeString("YourBatman");
            // 2、写对象
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("enName", "YourBatman");
            jsonGenerator.writeEndObject();
            // 3、写数字
            jsonGenerator.writeNumber(18);
            jsonGenerator.writeEndArray();

            jsonGenerator.writeEndObject();
        }
    }

    @Test
    public void test8() throws IOException {
        JsonFactory factory = new JsonFactory();
        try (JsonGenerator jsonGenerator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
            jsonGenerator.writeStartObject();

            jsonGenerator.writeStringField("zhName", "A哥");
            jsonGenerator.writeBooleanField("success", true);
            jsonGenerator.writeNullField("myName");
            // jsonGenerator.writeObjectFieldStart();
            // jsonGenerator.writeArrayFieldStart();

            jsonGenerator.writeEndObject();
        }
    }

}
