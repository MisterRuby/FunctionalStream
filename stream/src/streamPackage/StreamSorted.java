package streamPackage;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Sorted
 *  - Stream 의 데이터를 순서대로 정렬하는데 사용
 *  - 데이터가 순서대로 정렬된 stream 을 리턴
 *  - 데이터의 종류에 따라 Comparator 가 필요할 수 있음
 */
public class StreamSorted {
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

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", isVerified=" + isVerified +
                    '}';
        }
    }

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
    public void testSortedInteger() {
        List<Integer> numbers = Arrays.asList(3, 10, 32 , -5, 23);

        List<Integer> result = numbers.stream()
                .sorted()
                .collect(Collectors.toList());

        System.out.println(result);
    }

    @Test
    public void testSortedUser() {
        List<User> users = getUsers();

        System.out.println("before");
        for (User user : users) {
            System.out.println(user);
        }

        List<User> result = users.stream()
//                .sorted((u1, u2) -> u1.name.compareTo(u2.name))
                .sorted(Comparator.comparing(u -> u.name))
                .collect(Collectors.toList());

        System.out.println("after");
        for (User user : result) {
            System.out.println(user);
        }
    }
}