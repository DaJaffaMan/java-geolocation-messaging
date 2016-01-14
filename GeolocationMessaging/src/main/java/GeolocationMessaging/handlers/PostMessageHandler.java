package GeolocationMessaging.handlers;

import GeolocationMessaging.entities.Message;
import GeolocationMessaging.repositories.MessageRepository;
import org.springframework.stereotype.Component;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.inject.Inject;
import java.util.Date;

@Component
public class PostMessageHandler implements Route {

    private final MessageRepository messageRepository;

    @Inject
    public PostMessageHandler(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Object handle(Request request, Response response) throws Exception {

        int messageId = Integer.parseInt(request.params(":messageId"));
        int userId = Integer.parseInt(request.params(":userId"));
        String messageContents = request.params(":messageContents");
        double lat = Double.parseDouble(request.params(":lat"));
        double lon = Double.parseDouble(request.params(":lon"));

        Message message = new Message(messageId, userId, messageContents, new Date(), lat, lon);

        messageRepository.save(message);

        return "Message added";
    }
}
