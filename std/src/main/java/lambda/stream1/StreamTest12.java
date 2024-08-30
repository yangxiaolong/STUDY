package lambda.stream1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @auther yangxiaolong
 * @create 2024/8/30
 */
public class StreamTest12 {

    public static void main(String[] args) {
        // 交叉打招呼 Hi zhangsan Hi lisi  Hi wangwu Hi zhaoliu
        // Hello zhangsan Hello lisi  Hello wangwu Hello zhaoliu
        // 你好 zhangsan 你好 lisi  你好 wangwu 你好 zhaoliu
        List<String> list1 = Arrays.asList("Hi", "Hello", "你好");
        List<String> list2 = Arrays.asList("zhangsan", "lisi", "wangwu", "zhaoliu");

        List<String> list = list1.stream()
                .flatMap(str1 -> list2.stream().map(str2 -> str1 + " " + str2))
                .collect(Collectors.toList());
        list.forEach(System.out::println);

    }

}
