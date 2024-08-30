package lambda.stream1;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @auther yangxiaolong
 * @create 2024/8/30
 */
public class StreamTest13 {

    public static void main(String[] args) {
        Student student1 = new Student("zhangsan", 100, 20);
        Student student2 = new Student("lisi", 90, 20);
        Student student3 = new Student("wangwu", 90, 30);
        Student student4 = new Student("zhangsan", 80, 40);

        List<Student> students = Arrays.asList(student1, student2, student3, student4);

        Map<String, List<Student>> nameMap = students.stream().collect(Collectors.groupingBy(Student::getName));
        nameMap.forEach((k, v) -> System.out.println(k + ":" + v));

        System.out.println("------------------1");
        Map<String, Long> countMap = students.stream().collect(Collectors.groupingBy(Student::getName, Collectors.counting()));
        countMap.forEach((k, v) -> System.out.println(k + ":" + v));

        System.out.println("------------------2");
        Map<String, Double> averaging = students.stream()
                .collect(Collectors.groupingBy(Student::getName, Collectors.averagingDouble(Student::getScore)));
        averaging.forEach((k, v) -> System.out.println(k + ":" + v));

        System.out.println("------------------3");
        Map<Boolean, List<Student>> partitioningBy = students.stream()
                .collect(Collectors.partitioningBy(s -> s.getScore() >= 90));
        partitioningBy.forEach((k, v) -> System.out.println(k + ":" + v));
    }

}
