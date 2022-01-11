package streamPackage;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Map
 *  - Stream 의 데이터를 변형하는데 사용
 *  - 데이터에 해당 함수가 적용된 결과물을 제공하는 stream을 리턴
 */
public class StreamMap {

    @Test
    public void testMap() {
        List<Integer> numbers = Arrays.asList(3, 10, 23, 52);

        List<Double> result = numbers.stream()
                .map(x -> x * 2.5)                          // map 에따라 스트림의 데이터 타입이 변경
                .collect(Collectors.toList());

        System.out.println(result);
    }
}
