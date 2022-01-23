package fucnApplications;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Scope
 *  - 변수에 접근할 수 있는 범위
 *  - 외부에서는 내부에 있는 변수에 접근이 가능하지만 그 반대는 불가능 (Lexical Scope)
 *
 * Closure
 *  - 외부함수 안에서 정의된 내부 함수가 외부 함수의 context에 접근할 수 있는 개념. 함수는 선언된 위치에 따라 상위 scope를 결정하며 이를 Lexical Scope 라고 한다.
 *  - 내부 함수가 사용한 외부 함수들의 변수들은 내부 함수 선언 당시로부터 변할 수 없기 때문에 final 로 선언되지 않더라도 암묵적으로 final로 취급된다.
 *
 */
public class Closure {
    public Supplier<String> getSupplier () {
        String name = "ruby";

        return () -> {
            String suffix = " is programmer";
            return name + suffix;
        };
    }
    @Test
    public void testClosure() {
        System.out.println("Closure.testClosure");

        Supplier<String> supplier = getSupplier();
        System.out.println(supplier.get());
    }


    /**
     * Curry
     *  - 여러 개의 매개변수를 받는 함수를 중첩된 여러 개의 함수로 쪼개어 매개변수를 한 번에 받지 않고 여러 단계에 걸쳐 나눠 받는 방법
     *  - Closure 의 응용
     */
    @Test
    public void testCurry() {
        Function<Integer, Function<Integer, Integer>> curriedAdd = x -> (y -> x + y);

        Function<Integer, Integer> addFive = curriedAdd.apply(5);
        Integer addFiveAddTwo = addFive.apply(2);
        Integer addFiveAddEleven = addFive.apply(11);

        System.out.println("Closure.testCurry");
        System.out.println("5 + 2 = " + addFiveAddTwo);
        System.out.println("5 + 11 = " + addFiveAddEleven);
    }
}
