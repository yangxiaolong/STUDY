package lambda.stream2;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @auther yangxiaolong
 * @create 2024/8/31
 */
public class MySetToMapCollector<T> implements Collector<T, Set<T>, Map<T, T>> {

    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("supplier invoked!");
//        return HashSet::new;
        return () -> {
            System.out.println("--------");
            return new HashSet<>();
        };
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("accumulator invoked!");
        return (set, item) -> {
            System.out.println("accumulator:" + set.hashCode() + ":" + set + " " + Thread.currentThread().getName());
            set.add(item);
        };

//        return Set::add;
    }

    @Override
    public BinaryOperator<Set<T>> combiner() {
        System.out.println("combiner invoked!");
        // 合并两个Set
        return (set1, set2) -> {
            System.out.println("set1:" + set1.hashCode() + ":" + set1 + " " + Thread.currentThread().getName());
            System.out.println("set1:" + set1.hashCode() + ":" + set1 + " " + Thread.currentThread().getName());
            set1.addAll(set2);
            return set1;
        };
    }

    @Override
    public Function<Set<T>, Map<T, T>> finisher() {
        System.out.println("finisher invoked!");
        // 将 Set<T>转化为 Map<T, T>
        return set -> {
            Map<T, T> map = new HashMap<>();
//            Map<T, T> map = new TreeMap<>();
            for (T t : set) {
                map.put(t, t);
            }
            return map;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED));

//        return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED, Characteristics.CONCURRENT));

//        这样写会抛出异常, 因为我们需要做finisher转化
//        return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED, Characteristics.IDENTITY_FINISH));
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            List<String> list = Arrays.asList("apple", "banana", "cherry", "111", "222", "2", "333", "11", "3334", "5", "6", "7");
//        Map<String, String> result = list.stream().collect(new MySetToMapCollector<>());
            Map<String, String> result = list.parallelStream().collect(new MySetToMapCollector<>());
            System.out.println(result); // 输出可能是{apple=apple, banana=banana, cherry=cherry}
        }
    }

}