package lambda.stream1;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @auther yangxiaolong
 * @create 2024/8/30
 */
public class FunctionTest2 {

    public static void main(String[] args) {
        FunctionTest2 test = new FunctionTest2();
        System.out.println(test.compute(2, value -> value * 3, value -> value * value));// 12
        System.out.println(test.compute2(2, value -> value * 3, value -> value * value));// 36

        System.out.println(test.compute3(1, 2, (a, b) -> a + b));// 3
        System.out.println(test.compute3(1, 2, (a, b) -> a - b));// -1
        System.out.println(test.compute3(1, 2, (a, b) -> a * b));// 2
        System.out.println(test.compute3(1, 2, (a, b) -> a / b));// 0

        System.out.println(test.compute4(2, 3, (a, b) -> a + b, x -> x * x));// 25

    }

    public int compute(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        return function1.compose(function2).apply(a);
    }

    public int compute2(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        return function1.andThen(function2).apply(a);
    }

    public int compute3(int a, int b, BiFunction<Integer, Integer, Integer> function) {
        return function.apply(a, b);
    }

    public int compute4(int a, int b, BiFunction<Integer, Integer, Integer> function1, Function<Integer, Integer> function2) {
        return function1.andThen(function2).apply(a, b);
    }

}