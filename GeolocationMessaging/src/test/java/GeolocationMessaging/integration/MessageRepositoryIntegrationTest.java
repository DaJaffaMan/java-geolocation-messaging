package GeolocationMessaging.integration;

import GeolocationMessaging.App;
import GeolocationMessaging.entities.Message;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static spark.Spark.stop;

public class MessageRepositoryIntegrationTest {

    CloseableHttpClient httpClient;
    Gson gson;

    @Before
    public void setup() {
        httpClient = HttpClientBuilder.create().build();

        gson = new Gson();
        App.main(null);
    }

    @Test
    public void testGetMessageByMessageIdHttpResponse() {

        HttpResponse httpResponse;

        HttpUriRequest getMessageRequest = new HttpGet("http://localhost:4567/get/message/messageid/1");

        try {

            httpResponse = httpClient.execute(getMessageRequest);

            assertEquals(200, httpResponse.getStatusLine().getStatusCode());

        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    @Test
    public void testGetMessageByUserIdHttpResponse() {

        HttpResponse httpResponse;

        HttpUriRequest getMessageRequest = new HttpGet("http://localhost:4567/get/message/userid/2");

        try {

            httpResponse = httpClient.execute(getMessageRequest);

            assertEquals(200, httpResponse.getStatusLine().getStatusCode());

        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    @Test
    public void testGetMessageByLocationHttpResponse() {

        HttpResponse httpResponse;

        HttpUriRequest getMessageRequest = new HttpGet("http://localhost:4567/get/messages/messagelocation/0/0");

        try {

            httpResponse = httpClient.execute(getMessageRequest);

            assertEquals(200, httpResponse.getStatusLine().getStatusCode());

        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    @Test
    public void testPostMessageHttpResponse() {

        HttpResponse httpResponse;

        HttpUriRequest postMessageRequest = new HttpPost("http://localhost:4567/add/message/1/2/foo/0/0");

        try {

            httpResponse = httpClient.execute(postMessageRequest);

            assertEquals(200, httpResponse.getStatusLine().getStatusCode());

        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    @Test
    public void testGetMessageContentsByMessageId() {

        HttpEntity httpEntity;

        HttpUriRequest addMessageRequest = new HttpPost("http://localhost:4567/add/message/1/2/foo/50/50");
        HttpUriRequest getMessageRequest = new HttpGet("http://localhost:4567/get/message/messageid/1");

        try {
            httpClient.execute(addMessageRequest).close();
            httpEntity = httpClient.execute(getMessageRequest).getEntity();

            String entityString = EntityUtils.toString(httpEntity);
            List<Message> messages = gson.fromJson(entityString, new TypeToken<ArrayList<Message>>() {
            }.getType());

            assertEquals("foo", messages.get(0).getMessageContents());

        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    @Test
    public void testGetMessageContentsByUserId() {

        HttpEntity httpEntity;

        HttpUriRequest addMessageRequest = new HttpPost("http://localhost:4567/add/message/1/2/foo/50/50");
        HttpUriRequest getMessageRequest = new HttpGet("http://localhost:4567/get/message/userid/2");

        try {
            httpClient.execute(addMessageRequest).close();
            httpEntity = httpClient.execute(getMessageRequest).getEntity();

            String entityString = EntityUtils.toString(httpEntity);

            List<Message> messages = gson.fromJson(entityString, new TypeToken<ArrayList<Message>>() {
            }.getType());

            assertEquals("foo", messages.get(0).getMessageContents());

        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    @Test
    public void testGetMessageContentsByLocation() {

        HttpEntity httpEntity;

        HttpUriRequest addMessageRequest = new HttpPost("http://localhost:4567/add/message/1/2/foo/50.00000001/50.00000001");
        HttpUriRequest getMessageRequest = new HttpGet("http://localhost:4567/get/messages/messagelocation/50/50");

        try {
            httpClient.execute(addMessageRequest).close();
            httpEntity = httpClient.execute(getMessageRequest).getEntity();

            String entityString = EntityUtils.toString(httpEntity);

            List<Message> message = gson.fromJson(entityString, new TypeToken<ArrayList<Message>>() {
            }.getType());

            assertEquals("foo", message.get(0).getMessageContents());
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    @Test
    public void testDeleteMessageByMessageId() {

        HttpEntity httpEntity;
        List<Message> message;

        HttpUriRequest addMessageRequest = new HttpPost("http://localhost:4567/add/message/1/2/foo/0/0");
        HttpUriRequest deleteMessageRequest = new HttpDelete("http://localhost:4567/delete/message/messageid/1");
        HttpUriRequest getMessageRequest = new HttpGet("http://localhost:4567/get/message/messageid/1");

        try {
            httpClient.execute(addMessageRequest).close();
            httpClient.execute(deleteMessageRequest).close();
            httpEntity = httpClient.execute(getMessageRequest).getEntity();

            String entityString = EntityUtils.toString(httpEntity);
            message = gson.fromJson(entityString, new TypeToken<ArrayList<Message>>() {
            }.getType());

            assertEquals(true, message.isEmpty());

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
