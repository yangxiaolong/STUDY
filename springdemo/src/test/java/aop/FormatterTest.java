package aop;

import formatter.Person;
import org.junit.Test;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.lang.Nullable;

/**
 * @auther yangxiaolong
 * @create 2024/12/22
 */
public class FormatterTest {
    @Test
    public void test2() {
        FormattingConversionService formattingConversionService = new FormattingConversionService();
        FormatterRegistry formatterRegistry = formattingConversionService;
        // 说明：这里不使用DefaultConversionService是为了避免默认注册的那些转换器对结果的“干扰”，不方便看效果
        // ConversionService conversionService = new DefaultConversionService();
        ConversionService conversionService = formattingConversionService;

        // 注册格式化器
        formatterRegistry.addPrinter(new Person.IntegerPrinter());

        // 最终均使用ConversionService统一提供服务转换
        System.out.println(conversionService.canConvert(Integer.class, String.class));
        System.out.println(conversionService.canConvert(Person.class, String.class));

        System.out.println(conversionService.convert(1, String.class));
        // 报错：No converter found capable of converting from type [cn.yourbatman.bean.Person] to type [java.lang.String]
        // System.out.println(conversionService.convert(new Person(1, "YourBatman"), String.class));
    }

    @Test
    public void test3() {
        FormattingConversionService formattingConversionService = new FormattingConversionService();
        FormatterRegistry formatterRegistry = formattingConversionService;
        ConversionService conversionService = formattingConversionService;

        // 注册格式化器
        formatterRegistry.addParser(new Person.IntegerParser());

        System.out.println(conversionService.canConvert(String.class, Integer.class));
        System.out.println(conversionService.convert("1", Integer.class));
    }

    @Test
    public void test4() {
        FormattingConversionService formattingConversionService = new FormattingConversionService();
        FormatterRegistry formatterRegistry = formattingConversionService;
        ConversionService conversionService = formattingConversionService;

        // 注册格式化器
//        formatterRegistry.addFormatterForFieldType(Person.class, null, new Person.IntegerParser());
        formatterRegistry.addFormatterForFieldType(Person.class, new Person.IntegerPrinter(), new Person.IntegerParser());
        formatterRegistry.addConverter(new Converter<Integer, Person>() {
            @Override
            public Person convert(@Nullable Integer source) {
                return new Person(source, "YourBatman");
            }
        });

        System.out.println(conversionService.canConvert(String.class, Person.class));
        System.out.println(conversionService.convert("1", Person.class));
    }

}
