package GeolocationMessaging.unit;

import GeolocationMessaging.config.DatabaseConfig;
import GeolocationMessaging.entities.Message;
import GeolocationMessaging.handlers.GetMessagesByLocationHandler;
import GeolocationMessaging.handlers.GetMessagesByMessageIdHandler;
import GeolocationMessaging.handlers.GetMessagesByUserIdHandler;
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

import javax.inject.Inject;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetMessageHandlerTest {

    AnnotationConfigApplicationContext context;
    MessageRepository messageRepository;
    GetMessagesByMessageIdHandler getMessagesByMessageIdHandler;
    GetMessagesByUserIdHandler getMessagesByUserIdHandler;
    GetMessagesByLocationHandler getMessagesByLocationHandler;
    Client client;

    @Mock
    Request request;
    @Mock
    Response response;

    @Before
    @Inject
    public void setup() {
        context = new AnnotationConfigApplicationContext(DatabaseConfig.class);
        messageRepository = context.getBean(MessageRepository.class);
        getMessagesByMessageIdHandler = context.getBean(GetMessagesByMessageIdHandler.class);
        getMessagesByUserIdHandler = context.getBean(GetMessagesByUserIdHandler.class);
        getMessagesByLocationHandler = context.getBean(GetMessagesByLocationHandler.class);
        client = context.getBean(Client.class);
        Date date = new Calendar.Builder().setDate(2000, 0, 1).setTimeOfDay(12, 0, 0).build().getTime();

        messageRepository.save(new Message(1, 2, "foo", date, 0, 0));
    }

    @Test
    public void testGetMessageByMessageId() throws Exception {
        when(request.params(":messageId")).thenReturn("1");

        String messageResponse = getMessagesByMessageIdHandler.handle(request, response).toString();

        assertEquals("[{\"messageId\":1,\"userId\":2,\"messageContents\":\"foo\",\"messageSentDate\":\"Jan 1, 2000 12:00:00 PM\",\"location\":{\"lat\":0.0,\"lon\":0.0}}]", messageResponse);
    }

    @Test
    public void testGetMessageByUserId() throws Exception {
        when(request.params(":userId")).thenReturn("2");

        String messageResponse = getMessagesByUserIdHandler.handle(request, response).toString();

        assertEquals("[{\"messageId\":1,\"userId\":2,\"messageContents\":\"foo\",\"messageSentDate\":\"Jan 1, 2000 12:00:00 PM\",\"location\":{\"lat\":0.0,\"lon\":0.0}}]", messageResponse);
    }

    @Test
    public void testGetMessageByLocation() throws Exception {
        when(request.params(":lat")).thenReturn("0");
        when(request.params(":lon")).thenReturn("0");

        String messageResponse = getMessagesByLocationHandler.handle(request, response).toString();

        assertEquals("[{\"messageId\":1,\"userId\":2,\"messageContents\":\"foo\",\"messageSentDate\":\"Jan 1, 2000 12:00:00 PM\",\"location\":{\"lat\":0.0,\"lon\":0.0}}]", messageResponse);
    }

    @After
    public void teardown() {
        client.close();
        context.close();
    }
}
