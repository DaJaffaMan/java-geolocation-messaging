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
public class GetMessagesByUserIdHandler implements Route {

    private final MessageRepository messageRepository;
    private final Gson gson;

    @Inject
    public GetMessagesByUserIdHandler(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
        gson = new Gson();

    }

    public Object handle(Request request, Response response) throws Exception {
        Integer userId = Integer.parseInt(request.params(":userId"));
        List<Message> messageList = messageRepository.findByUserId(userId);

        return gson.toJson(messageList);
    }
}
