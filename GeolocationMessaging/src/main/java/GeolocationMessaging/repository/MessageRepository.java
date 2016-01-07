package GeolocationMessaging.repository;

import GeolocationMessaging.message.Message;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import java.util.List;

public interface MessageRepository extends ElasticsearchCrudRepository {

    int findByMessageId(int messageId);

    List<Message> findByUserId(int userId);

    List<Message> findByMessageContentsLike(String messageContents);

}