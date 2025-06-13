package jackson.jsongenerate;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;

/**
 * @author Dragon
 */
public class FeatureTest {

    //自动关闭：AUTO_CLOSE_TARGET
    //com.fasterxml.jackson.core.JsonGenerator.Feature.AUTO_CLOSE_TARGET
    @Test
    public void test1() throws IOException {
        JsonFactory factory = new JsonFactory();
        try (JsonGenerator jg = factory.createGenerator(System.err, JsonEncoding.UTF8)) {
            // doSomething
        }
    }

    @Test
    public void test2() throws IOException {
        JsonFactory factory = new JsonFactory();
        try (PrintStream err = System.err; JsonGenerator jg = factory
                .createGenerator(err, JsonEncoding.UTF8)) {
            // 特征置为false 采用手动关流的方式
            jg.disable(JsonGenerator.Feature.AUTO_CLOSE_TARGET);

            // doSomething
        }
    }

    //AUTO_CLOSE_JSON_CONTENT
    //{"names":["A哥","YourBatman"]}
    //wow，竟然输出一切正常。细心的你会发现，我的代码是缺胳膊少腿的：
    // 「不管是Object还是Array都只start了，并没有显示调用end进行闭合」。
    // 但是呢，结果却正常得很，这便是此Feature的作用了
    @Test
    public void test3() throws IOException {
        JsonFactory factory = new JsonFactory();
        try (JsonGenerator jg = factory.createGenerator(System.err, JsonEncoding.UTF8)) {
            jg.writeStartObject();
            jg.writeFieldName("names");

            // 写数组
            jg.writeStartArray();
            jg.writeString("A哥");
            jg.writeString("YourBatman");
        }
    }

    //FLUSH_PASSED_TO_STREAM(true)
    //true：当JsonGenerator调用close()/flush()方法时，自动强刷I/O流里面的数据
    //false：请手动处理
    @Test
    public void test4() throws IOException {
        JsonFactory factory = new JsonFactory();
        JsonGenerator jg = factory.createGenerator(System.err, JsonEncoding.UTF8);

        jg.writeStartObject();
        jg.writeStringField("name", "A哥");
        jg.writeEndObject();

        // jg.flush();
        // jg.close();
    }

    //QUOTE_FIELD_NAMES
    //此属性自2.10版本后已过期，使用JsonWriteFeature#QUOTE_FIELD_NAMES代替，应用在JsonFactory上，后文详解
    //{"name":"A哥"}
    //{name:"A哥"}
    @Test
    public void test5() throws IOException {
        JsonFactory factory = new JsonFactory();
        try (JsonGenerator jg = factory.createGenerator(System.err, JsonEncoding.UTF8)) {
            jg.disable(JsonGenerator.Feature.QUOTE_FIELD_NAMES);

            jg.writeStartObject();
            jg.writeStringField("name", "A哥");
            jg.writeEndObject();
        }
    }


    //QUOTE_NON_NUMERIC_NUMBERS
    //此属性自2.10版本后已过期，使用JsonWriteFeature#WRITE_NAN_AS_STRINGS代替，应用在JsonFactory上，后文详解
    //0.9 1.9 "NaN" "-Infinity" "Infinity"
    //0.9 1.9 NaN -Infinity Infinity
    @Test
    public void test6() throws IOException {
        JsonFactory factory = new JsonFactory();
        try (JsonGenerator jg = factory.createGenerator(System.err, JsonEncoding.UTF8)) {
            jg.disable(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS);

            jg.writeNumber(0.9);
            jg.writeNumber(1.9);

            jg.writeNumber(Float.NaN);
            jg.writeNumber(Float.NEGATIVE_INFINITY);
            jg.writeNumber(Float.POSITIVE_INFINITY);
        }
    }

    //ESCAPE_NON_ASCII
    //此属性自2.10版本后已过期，使用JsonWriteFeature#ESCAPE_NON_ASCII代替，应用在JsonFactory上，后文详解
    //"A哥"
    //"A\u54E5"
    @Test
    public void test7() throws IOException {
        JsonFactory factory = new JsonFactory();
        try (JsonGenerator jg = factory.createGenerator(System.err, JsonEncoding.UTF8)) {
            jg.enable(JsonGenerator.Feature.ESCAPE_NON_ASCII);
            jg.writeString("A哥");
        }
    }

    //WRITE_NUMBERS_AS_STRINGS(false)
    //此属性自2.10版本后已过期，使用JsonWriteFeature#WRITE_NUMBERS_AS_STRINGS代替，应用在JsonFactory上，后文详解
    //该特性「强制」将「所有」Java数字写成字符串，即使底层数据格式真的是数字。
    //
    //true：所有数字「强制」写为字符串
    //false：不做处理
    //9223372036854775807
    //"9223372036854775807"
    @Test
    public void test8() throws IOException {
        JsonFactory factory = new JsonFactory();
        try (JsonGenerator jg = factory.createGenerator(System.err, JsonEncoding.UTF8)) {
            // jg.enable(WRITE_NUMBERS_AS_STRINGS);

            Long num = Long.MAX_VALUE;
            jg.writeNumber(num);
        }
    }

    //WRITE_BIGDECIMAL_AS_PLAIN
    //控制写java.math.BigDecimal的行为：
    //
    //true：使用BigDecimal#toPlainString()方法输出
    //false： 使用默认输出方式（取决于BigDecimal是如何构造的）
    //1 1.0 1E+11
    //1 1.0 100000000000
    @Test
    public void test9() throws IOException {
        JsonFactory factory = new JsonFactory();
        try (JsonGenerator jg = factory.createGenerator(System.err, JsonEncoding.UTF8)) {
            // jg.enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);

            BigDecimal bigDecimal1 = new BigDecimal(1.0);
            BigDecimal bigDecimal2 = new BigDecimal("1.0");
            BigDecimal bigDecimal3 = new BigDecimal("1E11");
            jg.writeNumber(bigDecimal1);
            jg.writeNumber(bigDecimal2);
            jg.writeNumber(bigDecimal3);
        }
    }

    //STRICT_DUPLICATE_DETECTION
    //是否去严格的检测重复属性名。
    //
    //true：检测是否有重复字段名，若有，则抛出JsonParseException异常
    //false：不检测JSON对象重复的字段名，即：相同字段名都要解析
    //{"name":"YourBatman","name":"A哥"}
    //打开注释报错
    @Test
    public void test10() throws IOException {
        JsonFactory factory = new JsonFactory();
        try (JsonGenerator jg = factory.createGenerator(System.err, JsonEncoding.UTF8)) {
            // jg.enable(JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION);

            jg.writeStartObject();
            jg.writeStringField("name", "YourBatman");
            jg.writeStringField("name", "A哥");
            jg.writeEndObject();
        }
    }

    //IGNORE_UNKNOWN

}