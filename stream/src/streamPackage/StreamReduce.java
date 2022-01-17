package streamPackage;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Reduce
 *  - 주어진 함수를 반복 적용해 스트림 안의 데이터를 하나의 값으로 합치는 작업
 *  - max / min / count 도 reduce 의 일종
 */
public class StreamReduce {
    @Test
    public void testReduce() {
        List<Integer> nums = Arrays.asList(23, 12, 42, 53, 3, 52, 7);

        // 매개변수의 accumulator 를 이용해 데이터를 반복 연산해서 최종 데이터를 반환. 스트림이 비어있을 경우 빈 Optional 을 반환
        Optional<Integer> reduceMax = nums.stream()
                .reduce(Integer::max);

        // 매개변수로 주어진 초기값과 accumulator 를 이용해 데이터를 반복연산. 초기값이 있기 때문에 항상 반환값이 존재함
        Integer reduce2 = nums.stream()
                .reduce(77, Integer::max);

        System.out.println("StreamReduce.testReduce");
        System.out.println("reduceMax: " + reduceMax.orElse(0));
        System.out.println("reduceIdentity: " + reduce2);
    }
}
