package GeolocationMessaging.messages;

import GeolocationMessaging.repositories.MessageRepository;
import org.elasticsearch.common.inject.Inject;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageDao {

    private final MessageRepository messageRepository;

    @Inject
    public MessageDao(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
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
