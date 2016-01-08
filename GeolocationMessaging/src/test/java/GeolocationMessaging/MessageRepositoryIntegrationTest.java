package GeolocationMessaging;

import GeolocationMessaging.config.DatabaseConfig;
import GeolocationMessaging.message.Message;
import GeolocationMessaging.repositories.MessageRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MessageRepositoryIntegrationTest {

    ApplicationContext context;
    MessageRepository messageRepository;

    @Before
    public void setup() {
        context = new AnnotationConfigApplicationContext(DatabaseConfig.class);
        messageRepository = context.getBean(MessageRepository.class);
    }

    @Test
    public void getMessageContents() {
        messageRepository.save(new Message(1, 1, "foo", new Date()));

        List<Message> foo = messageRepository.findByMessageContentsLike("foo");


        assertEquals("foo",foo.get(0).getMessageContents());
    }
}
