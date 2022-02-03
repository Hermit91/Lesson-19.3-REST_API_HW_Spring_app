package gmail.salokin1991.restbackend.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CheckMessages {

    private String recipientId;
    private String messageBody;

    public CheckMessages(String recipient, String message) {
        this.recipientId = recipient;
        this.messageBody = message;
    }
}
