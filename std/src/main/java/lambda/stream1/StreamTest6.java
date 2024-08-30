package lambda.stream1;

import java.util.stream.Stream;

/**
 * @auther yangxiaolong
 * @create 2024/8/30
 */
public class StreamTest6 {

    public static void main(String[] args) {
        Stream.iterate(1, n -> n + 2).limit(6).forEach(System.out::println);

        System.out.println("---------------------------1");
        Stream<Integer> stream = Stream.iterate(1, n -> n + 2).limit(6);
        //找出该流中大于2的元素，然后将每个元素乘以2，然后忽略掉流中的前两个元素，然后再取流中的前两个元素，最后求出流中元素的总和。
        System.out.println(stream.filter(s -> s > 2).mapToInt(s -> s * 2).skip(2).limit(2).sum());//32

        System.out.println("---------------------------2");
        Stream<Integer> stream1 = Stream.iterate(1, n -> n + 2).limit(6);
        stream1.filter(s -> s > 2).mapToInt(s -> s * 2).skip(2).limit(2).min().ifPresent(System.out::println);//14

        System.out.println("---------------------------3");
        Stream<Integer> stream_ = Stream.iterate(1, n -> n + 2).limit(6);
        System.out.println(stream_);
        Stream<Integer> stream2 = stream_.filter(item -> item > 2);
        System.out.println(stream2);
        Stream<Integer> stream3 = stream2.distinct();
        System.out.println(stream3);
    }

}
