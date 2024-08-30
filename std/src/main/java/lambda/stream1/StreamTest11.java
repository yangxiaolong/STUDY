package lambda.stream1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @auther yangxiaolong
 * @create 2024/8/30
 */
public class StreamTest11 {

    public static void main(String[] args) {
        // 找出所有单词,并且去重
        List<String> list = Arrays.asList("hello welcome", "world hello", "hello world hello", "hello welcome");
        // 错误写法
        // list.stream().map(str -> str.split(" ")).distinct().forEach(System.out::println);

        list.stream().map(str -> str.split(" ")).flatMap(Arrays::stream).distinct().collect(Collectors.toList()).forEach(System.out::println);

        list.stream().flatMap(str -> Arrays.stream(str.split(" "))).distinct().collect(Collectors.toList()).forEach(System.out::println);
    }

}
