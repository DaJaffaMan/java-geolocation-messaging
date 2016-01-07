package GeolocationMessaging.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

@Configuration
@EnableElasticsearchRepositories(basePackages = "org/springframework/data/elasticsearch/repositories")
public class DatabaseConfig {

    @Bean
    public Node node(){
        return nodeBuilder().local(true).node();
    }

    @Bean
    public Client client(Node node){
        return node.client();
    }

    @Bean
    public ElasticsearchOperations elasticsearchOperations(Client client){
        return new ElasticsearchTemplate(client);
    }

}
