package funcInterface;

import org.junit.Test;

import java.util.function.*;

public class FuncInterface {

    @Test
    public void testSupplier() {
        /**
         * Supplier
         *  - 매개변수가 없고 리턴값만 있는 추상메서드가 있는 함수형 인터페이스
         *  - T get()
         */
        Supplier<Double> randomSup = Math::random;
        System.out.println(randomSup.get());
        System.out.println(randomSup.get());
        System.out.println(randomSup.get());
    }

    @Test
    public void testConsumer() {
        /**
         * Consumer
         *  - 매개변수가 한 개 있고 리턴값이 없는 추상메서드가 있는 함수형 인터페이스
         *  - void accept (T t)
         */
        Consumer<String> consumer = System.out::println;
        // s -> System.out.println(s);

        consumer.accept("ruby");
    }

    @Test
    public void testBiConsumer() {
        /**
         * BiConsumer
         *  - 매개변수가 두 개 있고 리턴값이 없는 추상메서드가 있는 함수형 인터페이스
         *  - void accept (T t, U u)
         */
        BiConsumer<Integer, Integer> biConsumer = (x, y) -> System.out.println(x + y);
        biConsumer.accept(10, 35);
    }

    @Test
    public void testPredicate() {
        /**
         * Predicate
         *  - 매개변수가 하나 있고 리턴값이 boolean 인 추상메서드가 있는 함수형 인터페이스
         *  - 메서드
         *      negate()
         *          - 기존 조건에 false 일 때 true 를 반환하는 새로운 Predicate 객체를 생성해 반환한다.
         *      and(Predicate<? super T> other)
         *          - 기존 조건에 새로운 조건까지 모두 만족할 때 true 를 반환하는 새로운 Predicate 객체를 생성해 반환한다.
         *      or (Predicate<? super T> other)
         *          - 기존 조건 또는 새로운 조건을 만족할 때 true 를 반환하는 새로운 Predicate 객체를 생성해 반환한다.
         *
         */
        Predicate<Integer> isPositive = x -> x > 0;                             // 0 보다 큰 수
        Predicate<Integer> negatePositive = isPositive.negate();                // 0 또는 0보다 작은 수
        Predicate<Integer> andPositive = isPositive.and(x -> x % 2 == 0);       // 0 보다 크고 짝수인 수
        Predicate<Integer> orPositive = isPositive.or(x -> x == -8);            // 0 보다 크거나 -8인 수

        System.out.println("isPositive test: " + isPositive.test(10));
        System.out.println("isPositive test: " + isPositive.test(-10));
        System.out.println("negatePositive test: " + negatePositive.test(10));
        System.out.println("negatePositive test: " + negatePositive.test(-10));
        System.out.println("andPositive test: " + andPositive.test(10));
        System.out.println("andPositive test: " + andPositive.test(11));
        System.out.println("orPositive test: " + orPositive.test(11));
        System.out.println("orPositive test: " + orPositive.test(-8));
    }

    @Test
    public void testBiPredicate() {
//        BiPredicate<String, String> stringBiPredicate = (x, y) -> x.equals(y);
        BiPredicate<String, String> stringBiPredicate = String::equals;
        System.out.println(stringBiPredicate.test("ruby", "ruvy"));
    }

}
