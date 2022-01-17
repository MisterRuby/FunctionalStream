package streamPackage;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * PartitioningBy
 *  - Predicate를 적용하여 true와 false 두 key가 존재하는 map을 반환하는 collector
 */
public class StreamPartitioningBy {
    List<Integer> nums = Arrays.asList(12, 123, 15, 42, 23, 33, 94, 14);

    @Test
    public void testPartitioningBy() {
        Map<Boolean, List<Integer>> oddAndEven = nums.stream()
                .collect(Collectors.partitioningBy(x -> x % 2 == 1));

        List<Integer> oddList = oddAndEven.get(true);
        List<Integer> evenList = oddAndEven.get(false);

        System.out.println("StreamPartitioningBy.testPartitioningBy");
        System.out.println("odd: " + oddList);
        System.out.println("even: " + evenList);
    }

}
