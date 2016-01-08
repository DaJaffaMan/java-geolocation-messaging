package GeolocationMessaging;

import GeolocationMessaging.config.DatabaseConfig;
import GeolocationMessaging.message.Message;
import GeolocationMessaging.repositories.MessageRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

public class MessageRepositoryIntegrationTest {

    ApplicationContext context;
    MessageRepository messageRepository;

    @Before
    public void setup() {
        context = new AnnotationConfigApplicationContext(DatabaseConfig.class);
        messageRepository = context.getBean(MessageRepository.class);
    }

    // TODO This test is broke and does nothing
    @Test
    public void test() {
        messageRepository.save(new Message(1, 1, "foo", new Date()));

        messageRepository.findByMessageContentsLike("foo");
    }
}
