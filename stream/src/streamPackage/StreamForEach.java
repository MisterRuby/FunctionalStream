package streamPackage;

import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * ForEach
 *  - 매개변수의 action을 스트림의 각 데이터에 적용해주는 종결 처리 메서드
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StreamForEach {

    List<Integer> nums = Arrays.asList(12, 123, 15, 94, 14);

    @Test
    public void testForeach() {
        System.out.println("StreamForEach.testForeach");
        nums.stream()
                .map(x -> x * 3)
                .forEach(System.out::println);
    }

    @Test
    public void testCollectionForEach() {
        System.out.println("StreamForEach.testCollectionForEach");
        // 스트림의 중간처리가 없을 경우 Collection.forEach 로 바로 처리
        nums.forEach(System.out::println);
    }

    @Test
    public void testIntStream() {
        System.out.println("StreamForEach.testIntStream");

        IntStream.range(0, nums.size())
                .forEach(i -> System.out.println(nums.get(i)));
    }
}
