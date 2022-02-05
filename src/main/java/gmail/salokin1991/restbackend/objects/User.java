package gmail.salokin1991.restbackend.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String userId;
    private String fullName;
    private List<Message> sentMessages;
    private List<Message> receivedMessages;

    public User(String fullName) {
        this.userId = String.valueOf(System.currentTimeMillis());
        this.fullName = fullName;
        this.sentMessages = new ArrayList<>();
        this.receivedMessages = new ArrayList<>();
    }

    public void sendMessage(Message message) {
        this.sentMessages.add(message);
    }

    public void receiveMessage(Message message) {
        this.receivedMessages.add(message);
    }
}
