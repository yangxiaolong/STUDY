package lambda.stream3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @auther yangxiaolong
 * @create 2024/8/31
 */
public class StreamTest3 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c", "d");
        list.stream().forEach(System.out::println);

        list.stream().map(item -> item + " _abc").forEach(System.out::println);


        Stream<String> stream1 = list.stream();
        System.out.println(111);
        Stream<String> stream2 = stream1.map(item -> item + " _abc");
        System.out.println(222);
        stream2.forEach(System.out::println);
    }

}
