package org.springframework.core.convert.support;

import org.junit.Test;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.ConverterFactory;
import propertyeditor.Customer;
import propertyeditor.Person;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzI0MTUwOTgyOQ==&action=getalbum&album_id=1727592481828978689&scene=21#wechat_redirect
 *
 * @author Dragon
 */
public class ConverterTest {

    /**
     * Converter：1:1
     */
    @Test
    public void test() {
        System.out.println("----------------StringToBooleanConverter---------------");
        StringToBooleanConverter converter = new StringToBooleanConverter();

        // trueValues.add("true");
        // trueValues.add("on");
        // trueValues.add("yes");
        // trueValues.add("1");
        System.out.println(converter.convert("true"));
        System.out.println(converter.convert("1"));

        // falseValues.add("false");
        // falseValues.add("off");
        // falseValues.add("no");
        // falseValues.add("0");
        System.out.println(converter.convert("FalSe"));
        System.out.println(converter.convert("off"));
        // 注意：空串返回的是null
        System.out.println(converter.convert(""));


        System.out.println("----------------StringToCharsetConverter---------------");
        StringToCharsetConverter converter2 = new StringToCharsetConverter();
        // 中间横杠非必须，但强烈建议写上   不区分大小写
        System.out.println(converter2.convert("uTf-8"));
        System.out.println(converter2.convert("utF8"));
    }

    /**
     * ConverterFactory：1:N
     */
    @Test
    public void test2() {
        System.out.println("----------------StringToNumberConverterFactory---------------");
        ConverterFactory<String, Number> converterFactory = new StringToNumberConverterFactory();
        // 注意：这里不能写基本数据类型。如int.class将抛错
        Integer convert = converterFactory.getConverter(Integer.class).convert("1");
        assert convert != null;
        System.out.println(convert.getClass());
        Double convert1 = converterFactory.getConverter(Double.class).convert("1.1");
        assert convert1 != null;
        System.out.println(convert1.getClass());
        Byte convert2 = converterFactory.getConverter(Byte.class).convert("0x11");
        assert convert2 != null;
        System.out.println(convert2.getClass());
    }

    /*
    以CollectionToCollectionConverter做示范：List<String> -> Set<Integer>
     */
    @Test
    public void test3() {
        System.out.println("----------------CollectionToCollectionConverter---------------");
        ConditionalGenericConverter conditionalGenericConverter = new CollectionToCollectionConverter(new DefaultConversionService());
        // 将Collection转为Collection（注意：没有指定泛型类型哦）
        System.out.println(conditionalGenericConverter.getConvertibleTypes());

        List<String> sourceList = Arrays.asList("1", "2", "2", "3", "4");
        TypeDescriptor sourceTypeDesp = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(String.class));
        TypeDescriptor targetTypeDesp = TypeDescriptor.collection(Set.class, TypeDescriptor.valueOf(Integer.class));

        System.out.println(conditionalGenericConverter.matches(sourceTypeDesp, targetTypeDesp));
        Object convert = conditionalGenericConverter.convert(sourceList, sourceTypeDesp, targetTypeDesp);
        assert convert != null;
        System.out.println(convert.getClass());
        System.out.println(convert);
    }

    @Test
    public void test4() {
        System.out.println("----------------StreamConverter---------------");
        ConditionalGenericConverter converter = new StreamConverter(new DefaultConversionService());

        TypeDescriptor sourceTypeDesp = TypeDescriptor.valueOf(Set.class);
        TypeDescriptor targetTypeDesp = TypeDescriptor.valueOf(Stream.class);
        boolean matches = converter.matches(sourceTypeDesp, targetTypeDesp);
        System.out.println("是否能够转换：" + matches);

        // 执行转换
        Object convert = converter.convert(Collections.singleton(1), sourceTypeDesp, targetTypeDesp);
        System.out.println(convert);
        assert convert != null;
        System.out.println(Stream.class.isAssignableFrom(convert.getClass()));
    }

    @Test
    public void test5() {
        System.out.println("----------------StreamConverter使用场景---------------");
        ConversionService conversionService = new DefaultConversionService();
        Stream<Integer> result = conversionService.convert(Collections.singleton(1), Stream.class);

        // 消费
        assert result != null;
        result.forEach(System.out::println);
        // result.forEach(System.out::println); //stream has already been operated upon or closed
    }

    @Test
    public void test6() {
        System.out.println("----------------ObjectToObjectConverter---------------");
        ConditionalGenericConverter converter = new ObjectToObjectConverter();

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setAddress("Peking");

        TypeDescriptor sourceType = TypeDescriptor.forObject(customer);
        assert sourceType != null;
        Object convert = converter.convert(customer, sourceType, TypeDescriptor.valueOf(Person.class));
        System.out.println(convert);

        // ConversionService方式（实际使用方式）
        ConversionService conversionService = new DefaultConversionService();
        Person person = conversionService.convert(customer, Person.class);
        System.out.println(person);
    }

}
