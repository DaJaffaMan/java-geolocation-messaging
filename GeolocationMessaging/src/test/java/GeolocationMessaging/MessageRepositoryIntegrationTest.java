package GeolocationMessaging;

import GeolocationMessaging.config.DatabaseConfig;
import GeolocationMessaging.message.Message;
import GeolocationMessaging.repositories.MessageRepository;
import org.junit.After;
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

        messageRepository.save(new Message(1, 2, "foo", new Date()));
    }

    @Test
    public void testGetMessageByMessageId() {
        List<Message> messages = messageRepository.findByMessageId(1);

        assertEquals(1, messages.get(0).getMessageId());
        assertEquals(2, messages.get(0).getUserId());
        assertEquals("foo", messages.get(0).getMessageContents());
    }

    @Test
    public void testGetMessageByUserId() {
        List<Message> messages = messageRepository.findByUserId(2);

        assertEquals(1, messages.get(0).getMessageId());
        assertEquals(2, messages.get(0).getUserId());
        assertEquals("foo", messages.get(0).getMessageContents());
    }

    @Test
    public void testGetMessageByContentsLike() {

        List<Message> messages = messageRepository.findByMessageContentsLike("foo");

        assertEquals(1, messages.get(0).getMessageId());
        assertEquals(2, messages.get(0).getUserId());
        assertEquals("foo", messages.get(0).getMessageContents());
    }

    @After
    public void teardown() {
        messageRepository.delete(0);
    }
}
