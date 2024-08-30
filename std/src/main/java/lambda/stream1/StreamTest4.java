package lambda.stream1;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @auther yangxiaolong
 * @create 2024/8/30
 */
public class StreamTest4 {

    public static void main(String[] args) {
        Stream<String> stream = Stream.of("hello", "world", "helloworld");
//        String[] strArr1 = stream.toArray(length -> new String[length]);
        String[] strArr = stream.toArray(String[]::new);
        List.of(stream).forEach(System.out::println);

        Stream<String> stream2 = Stream.of("hello", "world", "helloworld");
//        List<String> list = stream2.collect(Collectors.toList());
//        List<String> list = stream.collect(()
//                -> new ArrayList<>(), (thisList, item) -> thisList.add(item), (thisList1, thisList2) -> thisList1.addAll(thisList2));
        List<String> list2 = stream2.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        list2.forEach(System.out::println);

        Stream<String> stream4 = Stream.of("hello", "world", "helloworld");
        List<String> list4 = stream4.collect(Collectors.toCollection(LinkedList::new));
        list4.forEach(System.out::println);

        Stream<String> stream5 = Stream.of("hello", "world", "helloworld", "helloworld");
        Set<String> set = stream5.collect(Collectors.toCollection(TreeSet::new));
        set.forEach(System.out::println);

        Stream<String> stream6 = Stream.of("hello", "world", "helloworld");
        String str = stream6.collect(Collectors.joining());
        System.out.println(str);
    }

}
