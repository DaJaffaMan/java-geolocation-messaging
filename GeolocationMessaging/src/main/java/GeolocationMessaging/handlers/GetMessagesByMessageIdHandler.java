package GeolocationMessaging.handlers;

import GeolocationMessaging.entities.Message;
import GeolocationMessaging.repositories.MessageRepository;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.inject.Inject;
import java.util.List;

@Component
public class GetMessagesByMessageIdHandler implements Route {

    private final MessageRepository messageRepository;
    private final Gson gson;

    @Inject
    public GetMessagesByMessageIdHandler(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
        this.gson = new Gson();
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Integer messageRequest = Integer.parseInt(request.params(":messageId"));
        final List<Message> messageList = messageRepository.findByMessageId(messageRequest);

        return gson.toJson(messageList);
    }
}
