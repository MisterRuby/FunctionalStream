package fucnApplications;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Scope
 *  - 변수에 접근할 수 있는 범위
 *  - 외부에서는 내부에 있는 변수에 접근이 가능하지만 그 반대는 불가능 (Lexical Scope)
 *
 * Closure
 *  - 내부 함수가 존재하는 한 내부 함수가 사용한 외부 함수의 변수들 역시 계속 존재하게 되는데 이렇게 Lexical Scope를 포함하는 함수를 closure 라고 한다.
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
        // 메서드 호출이 끝난 시점에서 변수 name은 제거되어야 하지만 리턴된 supplier 가 내부에서 name을 요구하므로 남아있게된다.
        Supplier<String> supplier = getSupplier();
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

        System.out.println("Closure.testCurry");
        System.out.println("2 + 5 = " + addFive.apply(2));
        System.out.println("11 + 5 = " + addFive.apply(11));
    }
}
