package GeolocationMessaging.handlers;

import GeolocationMessaging.entities.Message;
import GeolocationMessaging.repositories.GeoMessageRepository;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Component;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.inject.Inject;
import java.util.List;

@Component
public class GetMessagesByLocationHandler implements Route {

    private final GeoMessageRepository geoMessageRepository;

    @Inject
    public GetMessagesByLocationHandler(GeoMessageRepository geoMessageRepository) {
        this.geoMessageRepository = geoMessageRepository;
    }

    public Object handle(Request request, Response response) throws Exception {
        final double lat = Double.parseDouble(request.params(":lat"));
        final double lon = Double.parseDouble(request.params(":lon"));
        final GeoPoint geoPoint = new GeoPoint(lat, lon);

        final List<Message> messages = geoMessageRepository.getGeoMessage(geoPoint);

        return messages;
    }
}
