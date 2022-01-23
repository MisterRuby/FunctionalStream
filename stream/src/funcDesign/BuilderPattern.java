package funcDesign;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.function.Consumer;


/**
 * Builder Pattern (빌더 패턴)
 *  - 대표적인 생성패턴
 *  - 객체의 생성에 대한 로직과 표현에 대한 로직을 분리
 *      - 객체 생성과 필드 값을 정하는 부분을 분리
 *      - 필드가 많아 생성자 매개변수 정리가 복잡해질 때 유용
 *  - 객체의 생성 과정을 유연하게 해준다.
 *
 */
class User {
    private int id;
    private String name;
    private String email;
    private LocalDateTime createAt;

    public User(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.createAt = builder.createAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", createAt=" + createAt +
                '}';
    }

    public static Builder builder(int id, String name) {
        return new Builder(id, name);
    }

    /**
     * 함수형 프로그래밍을 이용한 빌더 패턴
     */
    public static class Builder {
        private int id;
        private String name;
        public String password;
        public String email;
        public LocalDateTime createAt;

        public Builder (int id, String name) {
            this.id = id;
            this.name = name;
            this.createAt = LocalDateTime.now();
        }

        public Builder with(Consumer<Builder> consumer) {
            // 밖에서 람다를 통해 구현한 메서드의 action 이 여기서 호출되어 실행됨
            consumer.accept(this);
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}

public class BuilderPattern {
    @Test
    public void testBuilder() {
        User user = User.builder(1, "ruby")
                .with(builder -> {
                    builder.password = "1234";
                    builder.email = "ruby8700@naver.com";
                })
                .build();

        System.out.println(user);
    }
}