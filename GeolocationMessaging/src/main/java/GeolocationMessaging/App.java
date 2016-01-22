package GeolocationMessaging;

import GeolocationMessaging.config.DatabaseConfig;
import GeolocationMessaging.handlers.*;
import GeolocationMessaging.transformer.JsonTransformer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static spark.Spark.*;

public class App {

    public static AnnotationConfigApplicationContext context;

    public static void main(String[] args) {

        context = new AnnotationConfigApplicationContext(DatabaseConfig.class);
        final GetMessagesByMessageIdHandler getMessageByMessageId = context.getBean(GetMessagesByMessageIdHandler.class);
        final GetMessagesByUserIdHandler getMessageByUserId = context.getBean(GetMessagesByUserIdHandler.class);
        final PostMessageHandler postMessageHandler = context.getBean(PostMessageHandler.class);
        final GetMessagesByLocationHandler getMessagesByLocation = context.getBean(GetMessagesByLocationHandler.class);
        final DeleteMessageByMessageIdHandler deleteMessageByMessageId = context.getBean(DeleteMessageByMessageIdHandler.class);

        post("/add/message/:messageid/:userid/:messageContents/:lat/:lon", postMessageHandler);

        get("/get/message/messageid/:messageId", getMessageByMessageId, new JsonTransformer());

        get("/get/message/userid/:userId", getMessageByUserId, new JsonTransformer());

        get("/get/messages/messagelocation/:lat/:lon", getMessagesByLocation, new JsonTransformer());

        delete("/delete/message/messageid/:messageid", deleteMessageByMessageId);

    }

    public static void shutdown() {
        stop();
        context.close();
    }
}
