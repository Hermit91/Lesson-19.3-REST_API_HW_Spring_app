package gmail.salokin1991.restbackend.services;

import gmail.salokin1991.restbackend.objects.Message;
import gmail.salokin1991.restbackend.objects.User;
import gmail.salokin1991.restbackend.responses.CreateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private UserService userService;
    private List<Message> messages = new ArrayList<>();

    public Message addMessage(CreateMessage newMessage) {
        Message message = new Message(newMessage.getSenderId(),
                newMessage.getRecipientId(), newMessage.getMessageBody());
        messages.add(message);

        User sender = userService.getById(newMessage.getSenderId());
        sender.sendMessage(message);

        User recipient = userService.getById(newMessage.getRecipientId());
        recipient.receiveMessage(message);

        return message;
    }

    public List<Message> getAllMessages() {
        return messages;
    }
}

