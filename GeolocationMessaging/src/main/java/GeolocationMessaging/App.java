package GeolocationMessaging;

import GeolocationMessaging.config.DatabaseConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static spark.Spark.*;

public class App 
{
    public static void main( String[] args ) {

        ApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfig.class);

        

    }
}
