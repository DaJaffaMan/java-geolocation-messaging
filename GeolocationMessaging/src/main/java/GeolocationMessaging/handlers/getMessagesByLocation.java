package GeolocationMessaging.handlers;

import GeolocationMessaging.entities.Message;
import GeolocationMessaging.repositories.GeoMessageRepository;
import com.google.gson.Gson;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Component;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.inject.Inject;
import java.util.List;

@Component
public class GetMessagesByLocation implements Route {

    private final GeoMessageRepository geoMessageRepository;
    private final Gson gson;

    @Inject
    public GetMessagesByLocation(GeoMessageRepository geoMessageRepository) {
        this.geoMessageRepository = geoMessageRepository;
        gson = new Gson();
    }

    public Object handle(Request request, Response response) throws Exception {
        final double lat = Double.parseDouble(request.params(":lat"));
        final double lon = Double.parseDouble(request.params(":lon"));
        final GeoPoint geoPoint = new GeoPoint(lat, lon);

        final List<Message> messages = geoMessageRepository.getGeoMessage(geoPoint);

        return gson.toJson(messages);
    }
}
