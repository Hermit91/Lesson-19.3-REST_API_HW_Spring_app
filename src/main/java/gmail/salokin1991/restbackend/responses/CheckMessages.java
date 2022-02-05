package gmail.salokin1991.restbackend.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CheckMessages {

    private String recipientId;
    private String messageBody;

    public CheckMessages(String recipient, String message) {
        this.recipientId = recipient;
        this.messageBody = message;
    }
}
