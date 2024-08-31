package lambda.stream2;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * @auther yangxiaolong
 * @create 2024/8/30
 */
public class StreamTest1 {

    public static void main(String[] args) {
        Student student1 = new Student("zhangsan", 80);
        Student student2 = new Student("lisi", 90);
        Student student3 = new Student("wangwu", 100);
        Student student4 = new Student("zhaoliu", 90);
        Student student5 = new Student("zhaoliu", 90);

        List<Student> students = Arrays.asList(student1, student2, student3, student4, student5);

        List<Student> students1 = students.stream().collect(toList());
        students1.forEach(System.out::println);

        System.out.println("-----------------1");
        System.out.println("count:" + students.stream().collect(counting()));
        System.out.println("count:" + students.stream().count());

        System.out.println("-----------------2");
        students.stream().collect(minBy(Comparator.comparingInt(Student::getScore))).ifPresent(System.out::println);
        students.stream().collect(maxBy(Comparator.comparingInt(Student::getScore))).ifPresent(System.out::println);
        System.out.println(students.stream().collect(averagingDouble(Student::getScore)));
        System.out.println(students.stream().collect(summingInt(Student::getScore)));
        System.out.println(students.stream().collect(summarizingInt(Student::getScore)));

        System.out.println("-----------------3");
        System.out.println(students.stream().map(Student::getName).collect(joining()));
        System.out.println(students.stream().map(Student::getName).collect(joining(", ")));
        System.out.println(students.stream().map(Student::getName).collect(joining(", ", "<begin> ", " <end>")));

        System.out.println("-----------------4");
        Map<Integer, Map<String, List<Student>>> integerMapMap = students.stream()
                .collect(groupingBy(Student::getScore, groupingBy(Student::getName)));
        System.out.println(integerMapMap);

        System.out.println("-----------------5");
        Map<Boolean, Map<Boolean, List<Student>>> mapMap = students.stream()
                .collect(partitioningBy(s -> s.getScore() > 80, partitioningBy(s -> s.getScore() > 90)));
        System.out.println(mapMap);
        Map<Boolean, Long> counting = students.stream().collect(partitioningBy(s -> s.getScore() > 80, counting()));
        System.out.println(counting);

        System.out.println("-----------------6");
        Map<String, Student> map5 = students.stream().collect(
                toMap(Student::getName,
                        Function.identity(),
                        BinaryOperator.minBy(Comparator.comparingInt(Student::getScore))
                )
        );
        System.out.println(map5);

        Map<String, Student> map6 = students.stream().collect(
                groupingBy(
                        Student::getName,
                        collectingAndThen(
                                Collectors.minBy(Comparator.comparingInt(Student::getScore)),
                                Optional::get
                        )
                )
        );
        System.out.println(map6);
    }

}
