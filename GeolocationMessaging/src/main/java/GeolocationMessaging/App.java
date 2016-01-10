package GeolocationMessaging;

import GeolocationMessaging.config.DatabaseConfig;
import GeolocationMessaging.repositories.MessageRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static ApplicationContext context;

    public static void main( String[] args ) {

        context = new AnnotationConfigApplicationContext(DatabaseConfig.class);
        final MessageRepository messageRepository = context.getBean(MessageRepository.class);

    }
}
