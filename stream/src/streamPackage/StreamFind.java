package streamPackage;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamFind {

    List<Integer> nums = Arrays.asList(3, 5, 21, 7, 4, 20);

    @Test
    public void testFindFirst() {
        // findFirst - 스트림 안의 첫번째 데이터를 반환. 스트림이 비어있다면 빈 Optional 반환
        Optional<Integer> any = nums.stream()
                .filter(x -> x > 5)
                .sorted()
                .findFirst();
        System.out.println("StreamFind.testFindFirst");
        System.out.println(any.get());

        Optional<Object> empty = Stream.empty()
                .findFirst();
        System.out.println(empty.orElse("empty"));
    }

    @Test
    public void testFindAny() {
        // findAny - 스트림 안의 아무 데이터나 리턴. 순서가 중요하지 않고 parallel stream을 사용할 때 최적화. 스트림이 비어있다면 빈 Optional 반환
        Optional<Integer> any1 = nums.stream().parallel()
                .filter(x -> x < 7)
                .findAny();
        Optional<Integer> any2 = nums.stream().parallel()
                .filter(x -> x < 7)
                .findAny();
        Optional<Integer> any3 = nums.stream().parallel()
                .filter(x -> x < 7)
                .findAny();

        System.out.println("StreamFind.testFindAny");
        System.out.println(any1.get());
        System.out.println(any2.get());
        System.out.println(any3.get());
    }
}
