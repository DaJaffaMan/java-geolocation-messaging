package GeolocationMessaging.integration;

import GeolocationMessaging.App;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static spark.Spark.stop;

public class MessageRepositoryIntegrationTest {

    CloseableHttpClient httpClient;

    @Before
    public void setup() {
        httpClient = HttpClientBuilder.create().build();
        App.main(null);
    }

    @Test
    public void testGetMessageByMessageIdHttpResponse() {

        HttpResponse httpResponse;

        HttpUriRequest getMessageRequest = new HttpGet("http://localhost:4567/get/message/messageid/1");

        try {

            httpResponse = httpClient.execute(getMessageRequest);

            assertEquals(200, httpResponse.getStatusLine().getStatusCode());

        } catch (HTTPException e) {
            e.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    @Test
    public void testGetMessageByMessageId() {

        HttpEntity httpEntity;

        HttpUriRequest addMessageRequest = new HttpPost("http://localhost:4567/add/message/1/2/foo/50/50");
        HttpUriRequest getMessageRequest = new HttpGet("http://localhost:4567/get/message/messageid/1");

        try {
            httpClient.execute(addMessageRequest);
            httpEntity = httpClient.execute(getMessageRequest).getEntity();

            String message = EntityUtils.toString(httpEntity);

            assertEquals("[{\"messageId\":1,\"userId\":2,\"messageContents\":\"foo\",\"messageSentDate\":" + new Date() + ",\"location\":{\"lat\":50.0,\"lon\":50.0}}]", message);

        } catch (HTTPException e) {
            e.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    @Test
    public void testGetMessageByUserId() {

        HttpEntity httpEntity;

        HttpUriRequest addMessageRequest = new HttpPost("http://localhost:4567/add/message/1/2/foo/50/50");
        HttpUriRequest getMessageRequest = new HttpGet("http://localhost:4567/get/message/userid/2");

        try {
            httpClient.execute(addMessageRequest);
            httpEntity = httpClient.execute(getMessageRequest).getEntity();

            String message = EntityUtils.toString(httpEntity);

            assertEquals("[{\"messageId\":1,\"userId\":2,\"messageContents\":\"foo\",\"messageSentDate\":" + new Date() + ",\"location\":{\"lat\":50.0,\"lon\":50.0}}]", message);

        } catch (HTTPException e) {
            e.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    @After
    public void teardown() throws Exception {
        httpClient.close();
        stop();
        App.shutdown();
    }
}
