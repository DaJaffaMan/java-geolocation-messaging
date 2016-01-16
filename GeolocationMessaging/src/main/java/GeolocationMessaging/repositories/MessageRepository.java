package GeolocationMessaging.repositories;

import GeolocationMessaging.entities.Message;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import java.util.List;

public interface MessageRepository extends ElasticsearchCrudRepository<Message, Integer> {

    // Creates a nosql query based on method name for Elasticsearch
    List<Message> findByMessageId(Integer messageId);

    List<Message> findByUserId(Integer userId);

    List<Message> findByMessageContentsLike(String messageContents);

}