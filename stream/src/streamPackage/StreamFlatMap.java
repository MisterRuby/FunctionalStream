package streamPackage;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * FlatMap
 *  - Map + Flatten
 *  - 스트림의 제네릭 타입이 배열 형태일 때 배열들의 모든 데이터를 가진 단일 스트림을 반환
 */
public class StreamFlatMap {
    String[][] instruments = {
            {"flute", "clarinet"},
            {"viola", "violin"},
            {"guitar", "base"},
    };

    @Test
    public void testForEach() {
        List<String> insList = new ArrayList<>();
        Arrays.stream(instruments)
                .forEach(x -> insList.addAll(Arrays.asList(x)));

        System.out.println(insList);
    }

    @Test
    public void testFlatMap() {
        List<String> insList = Arrays.stream(instruments)       // Stream<String[]>
                .flatMap(Arrays::stream)                        // Stream<String>
                .collect(Collectors.toList());                  // List<String>

        System.out.println(insList);
    }
}
