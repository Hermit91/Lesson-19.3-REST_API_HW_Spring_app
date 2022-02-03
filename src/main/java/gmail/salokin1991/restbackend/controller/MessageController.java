package gmail.salokin1991.restbackend.controller;

import gmail.salokin1991.restbackend.objects.Message;
import gmail.salokin1991.restbackend.responses.CreateMessage;
import gmail.salokin1991.restbackend.services.MessageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/messages")
    @ApiOperation("Create message")
    public Message addMessage(@RequestBody CreateMessage message) {
        return messageService.addMessage(message);
    }

    @GetMapping("/messages")
    @ApiOperation("Get all messages")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }
}
