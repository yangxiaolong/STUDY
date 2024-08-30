package lambda.stream1;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * @auther yangxiaolong
 * @create 2024/8/30
 */
public class PersonTest {

    public static void main(String[] args) {
        Person person1 = new Person("zhangsan", 20);
        Person person2 = new Person("lisi", 30);
        Person person3 = new Person("wangwu", 40);
        List<Person> persons = Arrays.asList(person1, person2, person3);

        PersonTest test = new PersonTest();
        List<Person> personResult = test.getPersonsByUsername("zhangsan", persons);
        personResult.forEach(person -> System.out.println(person.getUserName()));

        List<Person> personResult1 = test.getPersonsByAge(30, persons);
        personResult1.forEach(person -> System.out.println(person.getUserName()));


        System.out.println("======================================");
        BiFunction<Integer, List<Person>, List<Person>> biFunction = (ageOfPerson, personlist) -> personlist
                .stream().filter(person -> person.getAge() > ageOfPerson).collect(Collectors.toList());
        List<Person> personResult2 = test.getPersonsByAge2(20, persons, biFunction);
        personResult2.forEach(person -> System.out.println(person.getUserName()));
    }

    public List<Person> getPersonsByUsername(String username, List<Person> persons) {
        return persons.stream().filter(person -> person.getUserName().equals(username)).collect(Collectors.toList());
    }

    public List<Person> getPersonsByAge(int age, List<Person> persons) {
        BiFunction<Integer, List<Person>, List<Person>> biFunction = (ageOfPerson, personlist) -> personlist
                .stream().filter(person -> person.getAge() > ageOfPerson).collect(Collectors.toList());
        return biFunction.apply(age, persons);
    }

    public List<Person> getPersonsByAge2(int age, List<Person> persons, BiFunction<Integer, List<Person>, List<Person>> biFunction) {
        return biFunction.apply(age, persons);
    }

}
