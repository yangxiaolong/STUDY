package aop;

import org.junit.Test;
import org.springframework.format.datetime.standard.DateTimeFormatterFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

/**
 * @auther yangxiaolong
 * @create 2024/12/22
 */
public class DateTimeFormatterFactoryTest {

    @Test
    public void test1() {
        // DateTimeFormatterFactory dateTimeFormatterFactory = new DateTimeFormatterFactory();
        // dateTimeFormatterFactory.setPattern("yyyy-MM-dd HH:mm:ss");
        // 执行格式化动作
        System.out.println(new DateTimeFormatterFactory("yyyy-MM-dd HH:mm:ss")
                .createDateTimeFormatter().format(LocalDateTime.now()));
        System.out.println(new DateTimeFormatterFactory("yyyy-MM-dd")
                .createDateTimeFormatter().format(LocalDate.now()));
        System.out.println(new DateTimeFormatterFactory("HH:mm:ss")
                .createDateTimeFormatter().format(LocalTime.now()));
        System.out.println(new DateTimeFormatterFactory("yyyy-MM-dd HH:mm:ss")
                .createDateTimeFormatter().format(ZonedDateTime.now()));
    }

}
