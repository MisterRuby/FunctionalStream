package streamPackage;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamCalculator {

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

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        User user1 = new User().setId(3).setName("ruby").setEmail("ruby@naver.com").setVerified(true);
        User user2 = new User().setId(2).setName("eun").setEmail("eun@naver.com").setVerified(true);
        User user3 = new User().setId(1).setName("bob").setEmail("bob@naver.com").setVerified(false);
        User user4 = new User().setId(4).setName("teemo").setEmail("teemo@naver.com").setVerified(false);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);

        return users;
    }

    @Test
    public void testMax() {
        Optional<Integer> max = Stream.of(4, 6, 10, 2, 7)
                .max(Integer::compareTo);
        System.out.println("StreamCalculator.testMax");
        System.out.println(max.get());
    }

    @Test
    public void testMin() {
        Optional<User> minIdUser = getUsers().stream()
                                .min(Comparator.comparingInt(user -> user.id));

        System.out.println("StreamCalculator.testMin");
        System.out.println(minIdUser.get().id + " / " + minIdUser.get().name);
    }

    @Test
    public void testCount() {
        long count = getUsers().stream()
                .filter(user -> user.isVerified)
                .count();

        System.out.println("StreamCalculator.testCount");
        System.out.println(count);
    }
}

