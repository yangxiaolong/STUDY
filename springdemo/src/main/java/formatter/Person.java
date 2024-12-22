package formatter;

import org.springframework.format.Parser;
import org.springframework.format.Printer;
import org.springframework.util.NumberUtils;

import java.text.ParseException;
import java.util.Locale;

/**
 * @auther yangxiaolong
 * @create 2024/12/22
 */
public class Person {

    private Integer id;
    private String name;

    public Person(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static class IntegerPrinter implements Printer<Integer> {

        @Override
        public String print(Integer object, Locale locale) {
            object += 10;
            return object.toString();
        }

    }

    public static class IntegerParser implements Parser<Integer> {

        @Override
        public Integer parse(String text, Locale locale) throws ParseException {
            return NumberUtils.parseNumber(text, Integer.class);
        }
    }

}
