package GeolocationMessaging.messages;

import GeolocationMessaging.App;
import GeolocationMessaging.repositories.MessageRepository;
import org.elasticsearch.common.inject.Inject;
import org.springframework.context.ApplicationContext;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.util.List;

public class MessageDao {

    private final ApplicationContext context;
    private final MessageRepository messageRepository;

    public MessageDao(){
        context = App.context;
        messageRepository = context.getBean(MessageRepository.class);
    }

    public List<Message> getMessageByMessageId(Integer messageId) {
        return messageRepository.findByMessageId(messageId);
    }

    public List<Message> getMessageByUserId(Integer userId) {
        return messageRepository.findByUserId(userId);
    }

    public List<Message> getMessageByMessageContents(String messageContents) {
        return messageRepository.findByMessageContentsLike(messageContents);
    }

}
