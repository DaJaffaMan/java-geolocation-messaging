package GeolocationMessaging.handlers;

import GeolocationMessaging.entities.Message;
import GeolocationMessaging.repositories.MessageRepository;
import org.springframework.stereotype.Component;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.inject.Inject;
import java.util.List;

@Component
public class GetMessagesByMessageIdHandler implements Route {

    private final MessageRepository messageRepository;

    @Inject
    public GetMessagesByMessageIdHandler(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Integer messageRequest = Integer.parseInt(request.params(":messageId"));
        final List<Message> messageList = messageRepository.findByMessageId(messageRequest);

        return messageList;
    }
}
