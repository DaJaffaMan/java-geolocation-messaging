package GeolocationMessaging.config;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * Created by jack on 06/01/16.
 */
public class DatabaseConfig {

    public EmbeddedDatabase database() {
        return EmbeddedDatabaseBuilder = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("create-schemas")
                .build();
    }

}
