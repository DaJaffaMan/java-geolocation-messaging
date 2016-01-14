package GeolocationMessaging.integration;

import GeolocationMessaging.App;
import GeolocationMessaging.entities.Message;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MessageRepositoryIntegrationTest {

    private CloseableHttpClient httpClient;
    private Gson gson;

    @Before
    public void setup() {
        httpClient = HttpClientBuilder.create().build();
        gson = new Gson();
        App.main(null);
    }

    @Test
    public void testGetMessageByMessageId() {

        HttpEntity httpEntity;
        List<Message> messages;

        HttpUriRequest addMessageRequest = new HttpPost("http://localhost:4567/add/message/1/2/foo/50/50");
        HttpUriRequest getMessageRequest = new HttpGet("http://localhost:4567/get/message/messageid/1");

        try {
            httpClient.execute(addMessageRequest);
            httpEntity = httpClient.execute(getMessageRequest).getEntity();

            String message = EntityUtils.toString(httpEntity);

            assertEquals("[{\"messageId\":1,\"userId\":2,\"messageContents\":\"foo\",\"messageSentDate\":" + new Date() + ",\"location\":{\"lat\":50.0,\"lon\":50.0}}]", message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @After
//    public void teardown() throws Exception {
//        httpClient.close();
//        stop();
//        App.shutdown();
//    }
}
