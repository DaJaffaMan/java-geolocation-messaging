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
public class GetMessagesByUserIdHandler implements Route {

    private final MessageRepository messageRepository;

    @Inject
    public GetMessagesByUserIdHandler(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Object handle(Request request, Response response) throws Exception {
        final Integer userId = Integer.parseInt(request.params(":userId"));
        final List<Message> messageList = messageRepository.findByUserId(userId);

        return messageList;
    }
}
