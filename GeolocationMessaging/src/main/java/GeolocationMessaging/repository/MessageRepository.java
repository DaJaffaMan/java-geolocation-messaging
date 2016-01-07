package GeolocationMessaging.repository;

import GeolocationMessaging.message.Message;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface MessageRepository extends ElasticsearchRepository{

    public int findByMessageId(int messageId);

    public List<Message> findByUserId(int userId);

    public List<Message> findByMessageContentsLike(String messageContents);


}