package lambda.stream2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @auther yangxiaolong
 * @create 2024/8/31
 */
public class StreamTest2 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c", "d");
        try (Stream<String> stream = list.stream()) {
            stream.onClose(() -> {
                System.out.println("Stream closed1");
            }).onClose(() -> System.out.println("Stream closed2")).forEach(System.out::println);
        }

//        try (Stream<String> stream = list.stream()) {
//            stream.onClose(() -> {
//                int a = 1 / 0;
//                System.out.println("Stream closed3");
//            }).onClose(() -> System.out.println("Stream closed4")).forEach(System.out::println);
//        }

        try (Stream<String> stream = list.stream()) {
            stream.onClose(() -> {
                int a = 1 / 0;
                System.out.println("Stream closed5");
            }).onClose(() -> {
                int a = 1 / 0;
                System.out.println("Stream closed6");
            }).forEach(System.out::println);
        }
    }

}
