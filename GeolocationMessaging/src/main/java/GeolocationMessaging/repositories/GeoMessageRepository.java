package GeolocationMessaging.repositories;

import GeolocationMessaging.entities.Message;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component
public class GeoMessageRepository implements Repository<Message, String> {

    private final ElasticsearchTemplate template;

    @Inject
    public GeoMessageRepository(ElasticsearchTemplate template) {
        this.template = template;
    }

    public List<Message> getGeoMessage(GeoPoint geoPoint) {
        final CriteriaQuery query = new CriteriaQuery(new Criteria("location").within(geoPoint, "10km"));
        final List<Message> messageList = template.queryForList(query, Message.class);

        return messageList;
    }


}
