package Optional;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Optional<T>
 *  - T 타입의 객체를 포장해 주는 래퍼 클래스
 *  - null일 수도, 아닐 수도 있는 오브젝트를 담을 때 사용
 */
public class OptionalWhyHow {
    
    class User {
        int id;
        String name;
        String email;
        boolean isVerified;

        public User setId(int id) {
            this.id = id;
            return this;
        }

        public User setName(String name) {
            this.name = name;
            return this;
        }

        public User setEmail(String email) {
            this.email = email;
            return this;
        }

        public User setVerified(boolean verified) {
            isVerified = verified;
            return this;
        }
    }

    public boolean userEquals(User u1, User u2) {
        return u1.id == u2.id
                && u1.name.equals(u2.name)
                && u1.email.equals(u2.email)
                && u1.isVerified == u2.isVerified;
    }

    @Test
    public void testNullPointerException() {
        User user1 = new User().setId(1).setName("ruby").setEmail("ruby@naver.com").setVerified(true);
        User user2 = new User().setId(1).setName("ruby").setVerified(true);

        Assert.assertFalse(userEquals(user1, user2));
        Assert.assertThrows(NullPointerException.class, () -> userEquals(user2, user1));
    }

    @Test
    public void testOptional() {
        String notNullObj = "ruby";
        String nullObj = null;

        // of - null이 아닌 오브젝트를 이용해 Optional을 생성. null 오브젝트를 매개변수로 넣으면 NullPointerException 발생
        Optional<String> notNullOptional = Optional.of(notNullObj);
        // empty - 빈 Optional 을 생성
        Optional<String> emptyOptional = Optional.empty();
        // ofNullable - null 인지 아닌지 알지 못하는 오브젝트로 Optional 을 생성. 매개변수에 null 오브젝트가 들어가면 빈 Optional을 생성
        Optional<String> nullableOptional = Optional.ofNullable(nullObj);

        // isPresent - Optional 안의 오브젝트가 null 인지 아닌지 체크. null이 아닐 시 true를 반환
        Assert.assertTrue(notNullOptional.isPresent());
        Assert.assertFalse(emptyOptional.isPresent());
        Assert.assertFalse(nullableOptional.isPresent());


        // get - Optional 안의 값을 추출. null 이라면 에러
        Assert.assertNotNull(notNullOptional.get());
        Assert.assertThrows(NoSuchElementException.class, () -> emptyOptional.get());

        // orElse - Optional 안의 값이 null 이 아니라면 Optional 안의 값을, null 이라면 매개변수로 공급된 값을 리턴
        Assert.assertTrue(notNullOptional.orElse("eun").equals("ruby"));
        Assert.assertTrue(emptyOptional.orElse("eun").equals("eun"));

        // orElseGet - Optional 안의 값이 null 이 아니라면 Optional 안의 값을, null 이라면 매개변수의 supplier 로 공급되는 값을 리턴
        Assert.assertTrue(notNullOptional.orElseGet(String::new).equals("ruby"));
        Assert.assertTrue(nullableOptional.orElseGet(String::new).isEmpty());

        // orElseThrow - Optional 안의 값이 null 이 아니라면 Optional 안의 값을, null 이라면 NoSuchElementException 발생
        //             - 매개변수로 supplier 를 넣어주면 supplier 로 공급되는 exception을 발생
        Assert.assertThrows(NoSuchElementException.class, () -> emptyOptional.orElseThrow());
        Assert.assertThrows(ArithmeticException.class, () -> emptyOptional.orElseThrow(ArithmeticException::new));

        // ifPresent - Optional 안의 값이 null 이 아니라면 매개변수의 Consumer 를 실행
        notNullOptional.ifPresent(System.out::println);

        // map - Optional 이 null 이 아닐 때 매개변수의 mapper가 적용된 Optional 을 반환.
        Optional<Integer> mapOptional = notNullOptional.map(String::length);
        Assert.assertTrue(mapOptional.orElse(0) instanceof Integer);

        // flatMap - 매개변수의 구현 메서드의 리턴값이 Optional 이어서 최종 반환 타입이 Optional<Optional<Object>> 일 때
        //          Optional<Object> 형태의 Optional 을 반환.
        Optional<Optional<String>> notFlatOptional = notNullOptional.map(x -> Optional.of(x + "123"));
        Optional<String> flatOptional = notNullOptional.flatMap(x -> Optional.of(x + "123"));
        Assert.assertTrue(flatOptional.get().equals("ruby123"));
    }
}
