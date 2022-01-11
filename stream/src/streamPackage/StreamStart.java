package streamPackage;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream (스트림)
 *  - 데이터의 흐름
 *  - Collection 형태로 구성된 데이터를 람다를 이용해 간결하고 직관적으로 프로세스하게 해줌
 *  - for, while 등의 기존 loop를 대체
 *  - 손쉽게 병렬 처리를 할 수 있게 해줌
 */
public class StreamStart {

    @Test
    public void testStreamToCollection() {
        String[] nameArr = {"ruby", "eun", "bob"};
        Stream<String> nameStream = Stream.of(nameArr);
        List<String> collectList = nameStream.collect(Collectors.toList());

        Stream<String> nameStream2 = Stream.of(nameArr);
        Set<String> collectSet = nameStream2.collect(Collectors.toSet());
    }

    @Test
    void testCollectionToStream() {
        String[] nameArr = {"ruby", "eun", "bob"};
        List<String> nameList = Arrays.asList(nameArr);

        /**
         * stream()
         *  - Collection 인터페이스의  default method.
         *  - stream 객체를 반환
         */
        Stream<String> stream = nameList.stream();
    }
}
