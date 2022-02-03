package gmail.salokin1991.restbackend.objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private String senderId;
    private String recipientId;
    private String messageBody;
    private String date;

    public Message(String senderId, String recipientId, String messageBody) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.messageBody = messageBody;
        this.date = new Date().toString();
    }
}

