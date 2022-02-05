package gmail.salokin1991.restbackend.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
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

