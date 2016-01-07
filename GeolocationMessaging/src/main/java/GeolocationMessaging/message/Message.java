package GeolocationMessaging.message;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Document(indexName = "messenger", type = "message")
public class Message {

    @Id
    private int messageId;
    private int userId;
    private String messageContents;
    private Date messageSentDate;

    public Message() {

    }

    public Message(int messageId, int userId, String messageContents, Date messageSentDate) {
        this.messageId = messageId;
        this.userId = userId;
        this.messageContents = messageContents;
        this.messageSentDate = messageSentDate;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessageContents() {
        return messageContents;
    }

    public void setMessageContents(String messageContents) {
        this.messageContents = messageContents;
    }

    public Date getMessageSentDate() {
        return messageSentDate;
    }

    public void setMessageSentDate(Date messageSentDate) {
        this.messageSentDate = messageSentDate;
    }
}
