package GeolocationMessaging.handlers;

import GeolocationMessaging.entities.Message;
import GeolocationMessaging.repositories.MessageRepository;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.inject.Inject;
import java.util.List;

public class DeleteMessageHandlerByMessageId implements Route {

    MessageRepository messageRepository;

    @Inject
    public DeleteMessageHandlerByMessageId(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Object handle(Request request, Response response) throws Exception {

        int messageId = Integer.parseInt(request.params("messageid"));
        List<Message> message = messageRepository.findByMessageId(messageId);

        messageRepository.delete(message);

        return null;
    }
}
