package GeolocationMessaging;

import GeolocationMessaging.config.DatabaseConfig;
import GeolocationMessaging.handlers.GetMessagesByMessageIdHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static spark.Spark.get;

public class App {

    public static void main(String[] args) {

        final ApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfig.class);
        final GetMessagesByMessageIdHandler messageIdHandler = context.getBean(GetMessagesByMessageIdHandler.class);

        get("/get/message//messageid/:messageId", messageIdHandler);

    }
}
