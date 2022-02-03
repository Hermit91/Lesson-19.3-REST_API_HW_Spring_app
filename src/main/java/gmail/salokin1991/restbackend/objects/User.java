package gmail.salokin1991.restbackend.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class User {

    private String id;
    private String fullName;
    private List<Message> sentMessages;
    private List<Message> receivedMessages;

    public User(String fullName) {
        this.id = String.valueOf(System.currentTimeMillis());
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
