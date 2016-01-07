package GeolocationMessaging;

import GeolocationMessaging.config.DatabaseConfig;
import GeolocationMessaging.repository.MessageRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App 
{
    public static void main( String[] args ) {

        ApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfig.class);

        MessageRepository messageRepository = context.getBean(MessageRepository.class);

    }
}
