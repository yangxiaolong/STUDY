package lambda.baeldung;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * @auther yangxiaolong
 * @create 2024/9/16
 */
public class ConcatTest {

    @Test
    public void whenMergingStreams_thenResultStreamContainsElementsFromBoth() {
        Stream<Integer> stream1 = Stream.of(1, 3, 5);
        Stream<Integer> stream2 = Stream.of(2, 4, 6);

        Stream<Integer> resultingStream = Stream.concat(stream1, stream2);

        assertEquals(
                Arrays.asList(1, 3, 5, 2, 4, 6),
                resultingStream.collect(Collectors.toList())
        );
    }

    @Test
    public void given4Streams_whenMerged_thenResultStreamContainsAllElements() {
        Stream<Integer> stream1 = Stream.of(1, 3, 5);
        Stream<Integer> stream2 = Stream.of(2, 4, 6);
        Stream<Integer> stream3 = Stream.of(18, 15, 36);
        Stream<Integer> stream4 = Stream.of(99);

        Stream<Integer> resultingStream = Stream.of(stream1, stream2, stream3, stream4)
                .flatMap(i -> i);

        assertEquals(
                Arrays.asList(1, 3, 5, 2, 4, 6, 18, 15, 36, 99),
                resultingStream.collect(Collectors.toList())
        );
    }

}
