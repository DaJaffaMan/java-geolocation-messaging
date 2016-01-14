package GeolocationMessaging;

import GeolocationMessaging.config.DatabaseConfig;
import GeolocationMessaging.handlers.GetMessagesByMessageIdHandler;
import GeolocationMessaging.handlers.GetMessagesByUserIdHandler;
import GeolocationMessaging.handlers.PostMessageHandler;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static spark.Spark.*;

public class App {

    public static AnnotationConfigApplicationContext context;

    public static void main(String[] args) {

        context = new AnnotationConfigApplicationContext(DatabaseConfig.class);
        final GetMessagesByMessageIdHandler messageIdHandler = context.getBean(GetMessagesByMessageIdHandler.class);
        final GetMessagesByUserIdHandler userIdHandler = context.getBean(GetMessagesByUserIdHandler.class);
        final PostMessageHandler postMessageHandler = context.getBean(PostMessageHandler.class);

        post("/add/message/:messageid/:userid/:messageContents/:lat/:lon", postMessageHandler);

        get("/get/message/messageid/:messageId", messageIdHandler);

        get("/get/message/userid/:userId", userIdHandler);

    }

    public static void shutdown() {
        stop();
        context.close();
    }
}
