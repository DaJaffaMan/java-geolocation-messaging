package GeolocationMessaging.config;

import GeolocationMessaging.handlers.HandlerConfig;
import GeolocationMessaging.repositories.RepositoryConfig;
import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

@Configuration
// Tells spring where to find repositories
@EnableElasticsearchRepositories(basePackages = "GeolocationMessaging.repositories")
// Tells spring to search for components in this package and then specifies other packages to search
@Import({HandlerConfig.class, RepositoryConfig.class})
public class DatabaseConfig {

    @Bean
    public Node node() {
        return nodeBuilder().local(true).node();
    }

    // calls close method in node class
    @Bean(destroyMethod = "close")
    public Client client(Node node) {
        return node.client();
    }

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate(Client client) {
        return new ElasticsearchTemplate(client);
    }

}
