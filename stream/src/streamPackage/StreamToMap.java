package streamPackage;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class StreamToMap {
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
    public void methodName() {
        List<User> users = getUsers();

        Map<Integer, String> userMap = users.stream()
                .collect(Collectors.toMap(x -> x.id, x -> x.name));

        System.out.println("StreamToMap.methodName");
        for (Integer id : userMap.keySet()) {
            System.out.println(id + " / " + userMap.get(id));
        }

        Collectors.toMap(x -> x, v -> v + "1234");
    }

}
