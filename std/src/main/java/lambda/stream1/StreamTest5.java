package lambda.stream1;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * @auther yangxiaolong
 * @create 2024/8/30
 */
public class StreamTest5 {

    public static void main(String[] args) {
        Stream<String> stream = Stream.of("hello", "world", "helloworld", "test");
        stream.map(String::toUpperCase).forEach(System.out::println);

        Stream<List<Integer>> stream2 = Stream.of(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6));
//        stream2.flatMap(integerList -> integerList.stream()).map(s -> s * s).forEach(System.out::println);
        stream2.flatMap(Collection::stream).map(s -> s * s).forEach(System.out::println);

    }

}
