package funcDesign;

import org.junit.Test;

import java.util.function.Predicate;

/**
 * Chain of Responsibility Pattern (책임 연쇄 패턴)
 *  - 행동 패턴 중 하나
 *  - 명령과 명령을 각각의 방법으로 처리할 수 있는 처리 담당 객체들이 있을 때 처리 객체들을 체인으로 엮는다.
 *  - 명령을 처리 객체들이 체인의 앞에서부터 하나씩 처리를 시도하며 각 처리 객체는 자신이 처리할 수 없을 때 다음 처리 객체로 명령을 넘긴다.
 *  - 새로운 처리 객체를 추가하는 것으로 처리 방법을 간단하게 추가할 수 있다.
 */
public class ChainResponsibilityPattern {

    class User {
        String name;
        String password;
        Authority authority;

        public User(String name, String password, Authority authority) {
            this.name = name;
            this.password = password;
            this.authority = authority;
        }

        public String getPassword() {
            return password;
        }

        public Authority getAuthority() {
            return authority;
        }
    }

    enum Authority {
        USER,
        ADMIN
    }

    class UserSaveChain {
        private final Predicate<User> saveUser;
        private UserSaveChain nextChain;

        public UserSaveChain(Predicate<User> saveUser) {
            this.saveUser = saveUser;
        }

        public UserSaveChain addNextChain(UserSaveChain nextChain) {
            if (this.nextChain == null) {
                this.nextChain = nextChain;
            } else {
                nextChain.addNextChain(nextChain);
            }
            return this;
        }

        public boolean save(User user) {
            boolean test = saveUser.test(user);

            if (test) return true;

            return nextChain != null && nextChain.save(user);
        }
    }

    @Test
    public void testChain() {
        UserSaveChain saveChain = new UserSaveChain(user -> user.getAuthority() == Authority.ADMIN);
        UserSaveChain ruleChain = new UserSaveChain(user ->
                user.getPassword().length() <= 10);
        saveChain.addNextChain(ruleChain);

        User user1 = new User("ruby", "aads1234", Authority.ADMIN);
        User user2 = new User("ruby", "aads123", Authority.USER);

        boolean save1 = saveChain.save(user1);
        boolean save2 = saveChain.save(user2);

        System.out.println("ChainResponsibilityPattern.testChain");
        System.out.println(save1);
        System.out.println(save2);
    }
}
