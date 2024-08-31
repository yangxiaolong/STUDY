package lambda.comparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @auther yangxiaolong
 * @create 2024/8/31
 */
public class MyComparatorTest {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("nihao", "Hello", "world", "hillo", "welcome");
        Collections.sort(list);
        System.out.println(list);

        System.out.println("------------1");
        list.sort((o1, o2) -> o1.length() - o2.length());
        System.out.println(list);

        System.out.println("------------2");
        list.sort(Comparator.comparingInt(String::length).reversed());
        System.out.println(list);

        System.out.println("------------3");
        list.sort(Comparator.comparingInt(String::length).reversed().thenComparing(String::compareTo));
        System.out.println(list);

        System.out.println("------------4");
        list.sort(Comparator.comparingInt(String::length).thenComparing(String.CASE_INSENSITIVE_ORDER));
        System.out.println(list);

        System.out.println("------------5");
        List<String> list2 = Arrays.asList(null, null, "nihao", "Hello", "world", "hillo", "welcome");
        list2.sort(Comparator.nullsLast(Comparator.comparingInt(String::length)));
        System.out.println(list2);

        System.out.println("------------6");
        list.sort(
                Comparator.comparingInt(String::length)
                        .thenComparing(String::toLowerCase, Comparator.reverseOrder())
        );
        System.out.println(list);

        System.out.println("------------7");
        list.sort(
                Comparator.comparingInt(String::length)
                        .reversed()
                        .thenComparing(String::toLowerCase, Comparator.reverseOrder())
        );
        System.out.println(list);
    }

}
