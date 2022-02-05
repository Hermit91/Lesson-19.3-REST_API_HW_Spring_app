package gmail.salokin1991.restbackend.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import gmail.salokin1991.restbackend.objects.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CheckUsers {

    private String userId;
    private String fullName;
    private List<CheckMessages> receivedMessages;
    private List<CheckMessages> sentMessages;

    public CheckUsers(User user) {

        this.userId = user.getUserId();
        this.fullName = user.getFullName();
        this.receivedMessages = user.getReceivedMessages().stream().map(n -> new CheckMessages
                (n.getSenderId(), (n.getMessageBody()))).collect(Collectors.toList());
        this.sentMessages = user.getSentMessages().stream().map(n -> new CheckMessages
                (n.getRecipientId(), (n.getMessageBody()))).collect(Collectors.toList());
    }
}
