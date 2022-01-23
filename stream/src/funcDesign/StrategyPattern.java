package funcDesign;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Strategy Pattern (전략 패턴)
 *  - 대표적인 행동 패턴으로 런타임에 어떤 전략(알고리즘)을 사용할 지 선택할 수 있게 하는 패턴
 *  - 전략들을 캡슐화하여 간단하게 교체
 */
public class StrategyPattern {
    class User {
        private int id;
        private String name;
        private String email;
        private LocalDateTime createAt;

        public User(int id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.createAt = LocalDateTime.now();
        }

        public String getName() {
            return this.name;
        }
    }

    interface EmailProvider {
        String getEmail(User user);
    }

    /**
     * Email 을 보내는 클래스
     */
    class EmailSender {
        // Email 보내기 전략
        private EmailProvider emailProvider;

        // 전략 설정
        public EmailSender setEmailProvider (EmailProvider emailProvider) {
            this.emailProvider = emailProvider;
            return this;
        }

        // 메일 보내기
        public void sendEmail(User user) {
            String email = emailProvider.getEmail(user);
            System.out.println("Sending " + email);
        }
    }

    class BasicEmailProvider implements EmailProvider {
        @Override
        public String getEmail(User user) {
            return "Basic Email " + user.getName();
        }
    }

    class VerifyEmailProvider implements EmailProvider {
        @Override
        public String getEmail(User user) {
            return "Verify Email " + user.getName();
        }
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        User user1 = new User(1, "ruby", "ruby@naver.com");
        User user2 = new User(2, "eun", "eun@naver.com");
        User user3 = new User(3, "bob", "bob@naver.com");
        User user4 = new User(4, "teemo", "teemo@naver.com");
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);

        return users;
    }

    /**
     * 미리 만들어둔 클래스로 전략 교체
     */
    @Test
    public void testEmailSender() {
        EmailSender emailSender = new EmailSender();
        EmailProvider basicEmailProvider = new BasicEmailProvider();
        VerifyEmailProvider verifyEmailProvider = new VerifyEmailProvider();

        System.out.println("StrategyPattern.testEmailSender");
        List<User> users = getUsers();

        emailSender.setEmailProvider(basicEmailProvider);
        users.forEach(emailSender::sendEmail);

        System.out.println("provider change");
        emailSender.setEmailProvider(verifyEmailProvider);
        users.forEach(emailSender::sendEmail);
    }

    /**
     * 람다를 활용해 사용하는 시점에 전략 구현
     */
    @Test
    public void testEmailSenderLambda() {
        EmailSender emailSender = new EmailSender();

        System.out.println("StrategyPattern.testEmailSenderLambda");
        List<User> users = getUsers();

        emailSender.setEmailProvider(user -> "Basic Email " + user.getName());
        users.forEach(emailSender::sendEmail);

        System.out.println("provider change");
        emailSender.setEmailProvider(user -> "Verify Email " + user.getName());
        users.forEach(emailSender::sendEmail);
    }
}
