package streamPackage;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * GroupingBy
 *  - 스트림 안의 데이터에 매개변수로 넘어온  classifier 를 적용했을 때 결과값이 같은 값끼리 List 로 모아서 Map 형태로 반환해주는 collector
 *      map 의 key 는 classifier 의 결과값, value 는 그 결과값을 갖는 데이터들
 */
public class StreamGroupingBy {

    List<Integer> nums = Arrays.asList(1,2,3,4,4,5,5,6,7,7,8,9,10,11);


    @Test
    public void testGroupingBy() {
        Map<Integer, List<Integer>> divNums = nums.stream()
                .collect(Collectors.groupingBy(x -> x % 3));

        System.out.println("StreamGroupingBy.testGroupingBy");
        System.out.println("list1: " + divNums.get(1));
        System.out.println("list2: " + divNums.get(2));
        System.out.println("list3: " + divNums.get(0));
    }

    @Test
    public void testCollectorsToSet() {
        Map<Integer, Set<Integer>> divNums = nums.stream()
                .collect(Collectors.groupingBy(x -> x % 3, Collectors.toSet()));

        System.out.println("StreamGroupingBy.testCollectorsToSet");
        System.out.println("set1: " + divNums.get(1));
        System.out.println("set2: " + divNums.get(2));
        System.out.println("set3: " + divNums.get(0));
    }

    @Test
    public void testCollectorsMapping() {
        Map<Integer, List<Integer>> divNums = nums.stream()
                .collect(Collectors.groupingBy(
                        x -> x % 3,
                        Collectors.mapping(x -> x * 10, Collectors.toList())));

        System.out.println("StreamGroupingBy.testCollectorsMapping");
        System.out.println("list1: " + divNums.get(1));
        System.out.println("list2: " + divNums.get(2));
        System.out.println("list3: " + divNums.get(0));
    }
}
