package fucnApplications;

import org.junit.Test;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Lazy Evaluation
 *  - 람다의 계산은 그 결과값이 필요할 때가 되어서야 계산된다.
 *
 */
public class LazyEvaluation {

    public boolean getTrue () {
        System.out.println("LazyEvaluation.getTrue");
        return true;
    }

    public boolean getFalse() {
        System.out.println("LazyEvaluation.getFalse");
        return false;
    }

    public boolean or (boolean x, boolean y) {
        return x || y;
    }

    public boolean lazyOr(Supplier<Boolean> x, Supplier<Boolean> y) {
        return x.get() || y.get();
    }

    @Test
    public void testNoneLazyEvaluation() {
        if (or(getTrue(), getFalse())) {
            System.out.println("LazyEvaluation.testNoneLazyEvaluation");
        }
    }
    @Test
    public void testLazyEvaluation() {
        if (lazyOr(this::getTrue, this::getFalse)) {
            System.out.println("LazyEvaluation.testNoneLazyEvaluation");
        }
    }

    @Test
    public void testLazyEvaluationStream() {
        Stream<Integer> integerStream = Stream.of(5, 12, 3, 5, 234, 2, 6)
                .filter(x -> x % 2 == 0)
                .peek(x -> System.out.println("peeking " + x))
                .filter(x -> x % 3 == 0);
        System.out.println("before collect");

        // 스트림의 중간 연산은 종결처리가 되는 시점까지 미루어지다가 한 번에 이루어진다.
        List<Integer> nums = integerStream.collect(Collectors.toList());

        System.out.println("after collect");
        System.out.println(nums);
    }
}
