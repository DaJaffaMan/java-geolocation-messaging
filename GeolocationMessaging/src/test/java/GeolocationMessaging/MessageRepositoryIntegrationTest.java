package GeolocationMessaging;

import GeolocationMessaging.config.DatabaseConfig;
import GeolocationMessaging.message.Message;
import GeolocationMessaging.repository.MessageRepository;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

public class MessageRepositoryIntegrationTest {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfig.class);
    MessageRepository messageRepository = context.getBean(MessageRepository.class);

    @Test
    public void test() {
        messageRepository.save(new Message(1, 1, "foo", new Date()));

        messageRepository.findByMessageContentsLike("foo");
    }
}
