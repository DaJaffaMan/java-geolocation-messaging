package GeolocationMessaging.handlers;

import GeolocationMessaging.messages.Message;
import GeolocationMessaging.messages.MessageDao;
import com.google.gson.Gson;
import org.elasticsearch.common.inject.Inject;
import org.springframework.stereotype.Component;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

@Component
public class GetMessagesByMessageIdHandler implements Route {

    private final MessageDao messageDao;
    private final Gson gson;

    @Inject
    public GetMessagesByMessageIdHandler(MessageDao messageDao) {
        gson = new Gson();
        this.messageDao = messageDao;
    }

    public Object handle(Request request, Response response) throws Exception {
        Integer messageRequest = Integer.parseInt(request.params(":messageId"));
        List<Message> messages = messageDao.getMessageByMessageId(messageRequest);

        return gson.toJson(messages);
    }
}
