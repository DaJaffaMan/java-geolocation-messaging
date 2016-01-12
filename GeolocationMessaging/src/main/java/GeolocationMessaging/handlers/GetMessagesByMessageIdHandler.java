package GeolocationMessaging.handlers;

import GeolocationMessaging.entities.Message;
import GeolocationMessaging.repositories.MessageRepository;
import com.google.gson.Gson;
import org.elasticsearch.common.inject.Inject;
import org.springframework.stereotype.Component;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

@Component
public class GetMessagesByMessageIdHandler implements Route {

    private final MessageRepository messageRepository;
    private final Gson gson;

    @Inject
    public GetMessagesByMessageIdHandler(MessageRepository messageRepository) {
        gson = new Gson();
        this.messageRepository = messageRepository;
    }

    public Object handle(Request request, Response response) throws Exception {
        Integer messageRequest = Integer.parseInt(request.params(":messageId"));
        List<Message> messages = messageRepository.findByMessageId(messageRequest);

        return gson.toJson(messages);
    }
}
