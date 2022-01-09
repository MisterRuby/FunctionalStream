package funcInterface;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Lambda {

    @Test
    public void testFunction() {

        /**
         * Function
         *  - util 에서 제공하는 매개변수 하나를 받는 함수형 인터페이스
         *  - default method
         *      compose
         *          - before.apply 의 리턴값을 매개변수로 사용하는 새로운 Function 객체를 반환
         *      andThen
         *          - 기존 객체의 apply 의 리턴 값을 after 의 매개변수로 사용하는 새로운 Function 객체를 반환
         */
        Function<Integer, Integer> adder = x -> x + 10;
        Function<Integer, Integer> composeMulti = adder.compose(x -> x * 100);
        Function<Integer, Integer> andThenMulti = adder.andThen(x -> x * 100);

        System.out.println("adder: " + adder.apply(10));
        System.out.println("composeMulti: " + composeMulti.apply(9));
        System.out.println("andThenMulti: " + andThenMulti.apply(9));
    }

    @Test
    public void testBi() {

        /**
         * BiFunction
         *  - util 에서 제공하는 매개변수 두 개를 받는 함수형 인터페이스
         *  - default method
         *      andThen
         *          - 기존 객체의 apply 의 리턴 값을 after 의 매개변수로 사용하는 새로운 BiFunction 객체를 반환
         */
        BiFunction<Integer, Integer, Integer> adder = Integer::sum;
        BiFunction<Integer, Integer, Integer> andThenMulti = adder.andThen(x -> x * 10);

        System.out.println("adder: " + adder.apply(13, 17));
        System.out.println("andThenMulti: " + andThenMulti.apply(13, 17));
    }

    @Test
    public void testTri() {
        TriFunction<Integer, Integer, Integer, Integer> adder = (x, y, z) -> x + y + z;

        System.out.println(adder.apply(3, 52, 132));
    }

}


/**
 * util 에서 제공하는 기본 함수형 인터페이스는 BiFunction 까지 매개변수를 2개까지만 지원한다.
 * 때문에 그 이상의 매개변수를 받고 싶다면 직접 작성
 * @param <T>
 * @param <U>
 * @param <V>
 * @param <R>
 */
@FunctionalInterface
interface TriFunction<T, U, V, R> {
    R apply (T t, U u, V v);
}