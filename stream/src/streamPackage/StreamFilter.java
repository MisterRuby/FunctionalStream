package streamPackage;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Filter
 *  - Stream 으로부터 만족하는 데이터만 걸러내는데 사용하는 메서드
 *  - 매개변수의 Predicate 에 true를 반환하는 데이터만 존재하는 stream을 리턴
 */
public class StreamFilter {

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        User user1 = new User().setId(1).setName("ruby").setEmail("ruby@naver.com").setVerified(true);
        User user2 = new User().setId(2).setName("eun").setEmail("eun@naver.com").setVerified(true);
        User user3 = new User().setId(3).setName("bob").setEmail("bob@naver.com").setVerified(false);
        User user4 = new User().setId(4).setName("teemo").setEmail("teemo@naver.com").setVerified(false);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);

        return users;
    }

    @Test
    public void testNonStream() {
        List<User> users = getUsers();

        List<User> result = new ArrayList<>();
        for (User user : users) {
            if (user.isVerified) result.add(user);
        }

        for (User user : result) {
            System.out.println(user.name);
        }
    }

    /**
     * Filter 등의 과정이 늘어나더라도 기존 for 문 내부의 if 처리를 간결하게 작성할 수 있다.
     */
    @Test
    public void testFilter() {
        List<User> users = getUsers();

        List<User> result = users.stream()                  // 스트림 생성
                .filter(User::getIsVerified)                // 필터(가공). 필터 처리 된 새로운 스트림 반환
                .collect(Collectors.toList());              // 결과

        for (User user : result) {
            System.out.println(user.name);
        }
    }

    @Test
    public void testStreamEquals() {
        List<User> users = getUsers();

        Stream<User> stream = users.stream();
        Stream<User> userStream = stream.filter(User::getIsVerified);

        System.out.println(stream == userStream);
        System.out.println(stream.hashCode());
        System.out.println(userStream.hashCode());

        // 두 개 모두 사용 불가. 같은 스트림이 연결되어 있으므로
        System.out.println(stream.collect(Collectors.toList()));
        System.out.println(userStream.collect(Collectors.toList()));    //userStream 에 stream이 연결되어있음
    }

    @Test
    public void testStreamChain() {
        List<User> users = getUsers();

        List<String> result = users.stream()                // stream 생성
                .filter(user -> !user.getIsVerified())      // filter 가공
                .map(User::getEmail)                        // map 가공
                .collect(Collectors.toList());              // 결과

        System.out.println(result);
    }
}

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

    public String getEmail() {
        return this.email;
    }

    public boolean getIsVerified() {
        return this.isVerified;
    }
}