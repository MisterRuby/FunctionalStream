package funcDesign;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Template Method Pattern
 *  - 행동 패턴 중 하나
 *  - 상위 클래스에는 알고리즘의 뼈대만을 정의하고 알고리즘의 각 단계는 하위 클래스에게 정의를 위임
 *  - 알고리즘의 큰 구조를 변경하지 않고 세부 단계들을 유연하게 변경
 */
public class TemplateMethodPattern {

    class User {
        int id;
        String name;
        String email;
        boolean isVerified;

        public User(int id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.isVerified = false;
        }

        public String getName() {
            return name;
        }
        public String getEmail() {
            return email;
        }

        public void setVerified(boolean verified) {
            isVerified = verified;
        }
    }

    /**
     * 알고리즘의 하위 단계를 해당 클래스를 상속받는 클래스에서 구현
     */
    abstract class AbstractUserService {
        protected abstract boolean validate(User user);
        protected abstract void save(User user);

        public void createUser(User user) {
            if (!validate(user)) {
                System.out.println("Fail save user");
                return;
            }

            save(user);
        }
    }

    class UserService extends AbstractUserService {
        @Override
        protected boolean validate(User user) {
            return user.getName() != null && user.getEmail() != null;
        }

        @Override
        protected void save(User user) {
            user.setVerified(true);
            System.out.println("Save user");
        }
    }

    /**
     * 해당 클래스를 만들 때 알고리즘의 하위 단계를 구현
     */
    class FuncUserService {
        private final Predicate<User> validate;
        private final Consumer<User> save;
        
        public FuncUserService(Predicate<User> validate, Consumer<User> save) {
            this.validate = validate;
            this.save = save;
        }

        public void createUser(User user) {
            if (!validate.test(user)) {
                System.out.println("Fail save user");
                return;
            }

            save.accept(user);
        }
    }
}
