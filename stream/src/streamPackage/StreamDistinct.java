package streamPackage;


import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Distinct
 *  - 중복되는 데이터가 제거된 stream을 리턴
 *  - 객체와 객체간의 비교는 equals 를 통해 비교
 */
public class StreamDistinct {
    class User {
        String name;
        String team;

        public User setName(String name) {
            this.name = name;
            return this;
        }

        public User setTeam(String team) {
            this.team = team;
            return this;
        }

        public String getTeam() {
            return team;
        }
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        User user1 = new User().setName("ruby").setTeam("humming");
        User user2 = new User().setName("eun").setTeam("humming");
        User user3 = new User().setName("bob").setTeam("crescendo");
        User user4 = new User().setName("teemo").setTeam("crescendo");
        User user5 = new User().setName("ali").setTeam("andante");
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);

        return users;
    }

    @Test
    public void testDistinctStrings() {
        List<String> names = Arrays.asList("ruby", "ruby", "eun", "teemo", "eun");
        List<String> result = names.stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println(result);
    }

    /**
     * 스트림을 사용하지 않은 중복제거
     */
    @Test
    public void testNotStreamDistinctUsers() {
        List<User> users = getUsers();
        HashSet<String> teams = new HashSet<>();
        for (User user : users) {
            String team = user.getTeam();
            teams.add(team);
        }
        System.out.println(teams);
    }

    /**
     * 스트림의 distinct를 사용한 중복제거
     */
    @Test
    public void testDistinctUsers() {
        List<String> result = getUsers().stream()
                            .map(User::getTeam)
                            .distinct()
                            .collect(Collectors.toList());

        System.out.println(result);
    }
}
