package GeolocationMessaging.unit;

import GeolocationMessaging.config.DatabaseConfig;
import GeolocationMessaging.handlers.PostMessageHandler;
import GeolocationMessaging.repositories.MessageRepository;
import org.elasticsearch.client.Client;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spark.Request;
import spark.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostMessageHandlerTest {
    AnnotationConfigApplicationContext context;
    PostMessageHandler postMessageHandler;
    MessageRepository messageRepository;
    Client client;

    @Mock
    Request request;
    @Mock
    Response response;

    @Before
    public void setup() {
        context = new AnnotationConfigApplicationContext(DatabaseConfig.class);
        postMessageHandler = context.getBean(PostMessageHandler.class);
        messageRepository = context.getBean(MessageRepository.class);
        client = context.getBean(Client.class);
    }

    @Test
    public void testAddNewMessage() throws Exception {
        when(request.params(":messageId")).thenReturn("1");
        when(request.params(":userId")).thenReturn("2");
        when(request.params(":messageContents")).thenReturn("foo");
        when(request.params(":lat")).thenReturn("0");
        when(request.params(":lon")).thenReturn("0");

        String messageResponse = postMessageHandler.handle(request, response).toString();

        assertEquals("Message added", messageResponse);
    }

    @After
    public void teardown() {
        client.close();
        context.close();
    }
}
