package jackson.jsonfactory;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonFactoryBuilder;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ServiceLoader;

import static com.fasterxml.jackson.core.JsonFactory.Feature.INTERN_FIELD_NAMES;

/**
 * @auther yangxiaolong
 * @create 2025/6/14
 */
public class JacksonFactoryTest {


    //com.fasterxml.jackson.core.JsonFactory.Feature

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

    //定制读/写实例
    //读写行为的控制是通过各自的Feature来控制的，JsonFactory作为一个功能「并非单一」的工厂类，
    // 需要既能够定制化读JsonParser，也能定制化写JsonGenerator
    /*
    public JsonFactory enable(JsonFactory.Feature f);
    public JsonFactory enable(JsonParser.Feature f);
    public JsonFactory enable(JsonGenerator.Feature f);

    public JsonFactory disable(JsonFactory.Feature f);
    public JsonFactory disable(JsonParser.Feature f);
    public JsonFactory disable(JsonGenerator.Feature f);

    // 合二为一的Configure方法
    public JsonFactory configure(JsonFactory.Feature f, boolean state);
    public JsonFactory configure(JsonParser.Feature f, boolean state);
    public JsonFactory configure(JsonGenerator.Feature f, boolean state);
     */


    //JsonFactoryBuilder
    @Test
    public void test4() throws IOException {
        JsonFactory jsonFactory = new JsonFactory();
        // jsonFactory自己的特征
        jsonFactory.enable(INTERN_FIELD_NAMES.INTERN_FIELD_NAMES);
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

    //JsonFactoryBuilder
    @Test
    public void test4_() throws IOException {
        JsonFactory jsonFactory = new JsonFactoryBuilder()
                // jsonFactory自己的特征
                .enable(INTERN_FIELD_NAMES)
                .enable(JsonFactory.Feature.CANONICALIZE_FIELD_NAMES)
                .enable(JsonFactory.Feature.USE_THREAD_LOCAL_FOR_BUFFER_RECYCLING)
                // JsonParser的特征
                .enable(JsonReadFeature.ALLOW_SINGLE_QUOTES, JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER)
                // JsonGenerator的特征
                .enable(JsonWriteFeature.QUOTE_FIELD_NAMES, JsonWriteFeature.ESCAPE_NON_ASCII)

                .build();

        // 创建读/写实例
        //jsonFactory.createParser(...);
        //jsonFactory.createGenerator(...);
    }


    //SPI方式

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
