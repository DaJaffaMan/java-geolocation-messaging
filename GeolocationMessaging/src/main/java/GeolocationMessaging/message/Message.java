package GeolocationMessaging.message;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="message", schema="messenger")
public class Message {

    @Column(name="message_id")
    private int messageId;

    @Column(name="user_id")
    private int userId;

    @Column(name="message_contents")
    private String messageContents;

    @Column(name="message_sent_date")
    private Date messageSentDate;

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
