package lambda.stream1;

import java.util.Arrays;
import java.util.List;

/**
 * @auther yangxiaolong
 * @create 2024/8/30
 */
public class StreamTest10 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("he", "hello", "world", "helloworld", "test");
        list.stream().mapToInt(String::length).filter(length -> length == 5).findFirst().ifPresent(System.out::println);
        System.out.println("-------------------------");

        // 流是拿到每一个元素去执行, mapToInt是拿到每一个元素去执行,然后返回一个int流, 会执行短路操作findFirst
        // 所以打印    he  hello
        //流是存在短路运算的
        list.stream().mapToInt(item -> {
            int length = item.length();
            System.out.println(item);// he  hello
            return length;
        }).filter(length -> length == 5).findFirst().ifPresent(System.out::println);
    }

}
