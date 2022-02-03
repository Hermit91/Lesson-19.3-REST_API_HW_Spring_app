package gmail.salokin1991.restbackend.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMessage {

    private String senderId;
    private String recipientId;
    private String messageBody;

}
