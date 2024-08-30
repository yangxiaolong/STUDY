package lambda.stream1;

import java.util.Comparator;
import java.util.function.BinaryOperator;

/**
 * @auther yangxiaolong
 * @create 2024/8/30
 */
public class BinaryOperatorTest {

    public static void main(String[] args) {
        BinaryOperatorTest test = new BinaryOperatorTest();

        System.out.println(test.compute(1, 2, (a, b) -> a + b));// 3
        System.out.println(test.compute(1, 2, (a, b) -> a - b));// -1
        System.out.println(test.compute(1, 2, (a, b) -> a * b));// 2
        System.out.println(test.compute(1, 2, (a, b) -> a / b));// 0
        System.out.println(test.getShort("abc", "abcd", (a, b) -> a.length() - b.length()));// abc
        System.out.println(test.getShort("abc", "cd", (a, b) -> a.charAt(0) - b.charAt(0)));// abc
    }

    public int compute(int a, int b, BinaryOperator<Integer> binaryOperator) {
        return binaryOperator.apply(a, b);
    }

    public String getShort(String a, String b, Comparator<String> comparator) {
        return BinaryOperator.minBy(comparator).apply(a, b);
    }

}
