package streamPackage;

import org.junit.Test;

import java.util.stream.IntStream;

/**
 * ParallelStream
 *  - 여러개의 스레드를 이용하여 스트림의 처리 과정을 병렬화
 *  - 중간 과정은 병렬처리되지만 순서가 있는 스트림의 경우 결과물이 순차 처리된 것과 일치되도록 종결 처리과정에서 조정
 *  - 장점
 *      - 병렬 처리를 간단하게 사용할 수 있게 해준다.
 *      - 속도가 비약적으로 빨라질 수 있다.
 *  - 단점
 *      - 항상 속도가 빨라지는 것은 아니다.
 *      - 공통으로 사용하는 리소스가 있을 경우 잘못된 결과가 나오거나 오류가 날 수 있다.
 */
public class StreamParallel {

    @Test
    public void testNoneParallel() {
        System.out.println("StreamParallel.testNoneParallel");
        long beforeTime = System.currentTimeMillis();

        IntStream.range(0, 100)
                .forEach(x -> {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

        long afterTime = System.currentTimeMillis();
        System.out.println(afterTime - beforeTime);
    }

    @Test
    public void testParallel() {
        System.out.println("StreamParallel.testParallel");

        long beforeTime = System.currentTimeMillis();
        IntStream.range(0, 100)
                .parallel()
                .forEach(x -> {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

        long afterTime = System.currentTimeMillis();
        System.out.println(afterTime - beforeTime);
    }
}
