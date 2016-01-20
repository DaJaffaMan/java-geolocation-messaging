package GeolocationMessaging.unit;

import GeolocationMessaging.config.DatabaseConfig;
import GeolocationMessaging.entities.Message;
import GeolocationMessaging.handlers.DeleteMessageByMessageIdHandler;
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

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeleteMessageHandlerTest {

    AnnotationConfigApplicationContext context;
    DeleteMessageByMessageIdHandler deleteMessageByMessageIdHandler;
    MessageRepository messageRepository;
    Client client;

    @Mock
    Request request;
    @Mock
    Response response;

    @Before
    public void setup() {
        context = new AnnotationConfigApplicationContext(DatabaseConfig.class);
        deleteMessageByMessageIdHandler = context.getBean(DeleteMessageByMessageIdHandler.class);
        messageRepository = context.getBean(MessageRepository.class);
        client = context.getBean(Client.class);

        messageRepository.save(new Message(1, 2, "foo", new Date(), 0, 0));
    }

    @Test
    public void testDeleteExistingMessageByMessageId() throws Exception {
        when(request.params(":messageid")).thenReturn("1");

        String messageResponse = deleteMessageByMessageIdHandler.handle(request, response).toString();

        assertEquals("message removed", messageResponse);
    }

    @Test
    public void testDeleteNonExistantMessageByMessageId() throws Exception {
        when(request.params(":messageid")).thenReturn("2");

        String messageResponse = deleteMessageByMessageIdHandler.handle(request, response).toString();

        assertEquals("message does not exist", messageResponse);
    }

    @After
    public void teardown() {
        client.close();
        context.close();
    }
}
