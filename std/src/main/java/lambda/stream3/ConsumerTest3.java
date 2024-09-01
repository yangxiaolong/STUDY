package lambda.stream3;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

/**
 * @auther yangxiaolong
 * @create 2024/9/1
 */
public class ConsumerTest3 {

    public void test(Consumer<Integer> consumer) {
        consumer.accept(100);
    }

    public static void main(String[] args) {
        ConsumerTest3 test3 = new ConsumerTest3();

        Consumer<Integer> consumer = i -> System.out.println(i);
        IntConsumer intConsumer = i -> System.out.println(i);

        System.out.println(consumer instanceof Consumer);
        System.out.println(intConsumer instanceof IntConsumer);

        test3.test(consumer);//面向对象方式
        test3.test(consumer::accept);//函数式方式 传递的是行为
        test3.test(intConsumer::accept);//函数式方式 传递的是行为
    }

}
