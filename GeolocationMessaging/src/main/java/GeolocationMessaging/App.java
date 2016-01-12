package GeolocationMessaging;

import GeolocationMessaging.config.DatabaseConfig;
import GeolocationMessaging.handlers.GetMessagesByMessageIdHandler;
import GeolocationMessaging.handlers.GetMessagesByUserIdHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static spark.Spark.get;

public class App {

    public static void main(String[] args) {

        final ApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfig.class);
        final GetMessagesByMessageIdHandler messageIdHandler = context.getBean(GetMessagesByMessageIdHandler.class);
        final GetMessagesByUserIdHandler userIdHandler = context.getBean(GetMessagesByUserIdHandler.class);


        get("/get/message/messageid/:messageId", messageIdHandler);

        get("/get/message/userid/:userId", messageIdHandler);

    }
}
