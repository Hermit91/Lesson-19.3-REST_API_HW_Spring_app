package gmail.salokin1991.restbackend;

import gmail.salokin1991.restbackend.objects.Message;
import gmail.salokin1991.restbackend.responses.CheckUsers;
import gmail.salokin1991.restbackend.responses.CreateMessage;
import gmail.salokin1991.restbackend.responses.CreateUser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleChatAPITests extends Specs {

    private CheckUsers createNewUser(String fullName) {

        CreateUser createUserRequest = new CreateUser(fullName);

        return requestSpec
                .body(createUserRequest)
                .when()
                .post("/users")
                .then()
                .log().body()
                .spec(responseSpec)
                .extract()
                .as(CheckUsers.class);
    }

    private Message createNewMessage(String senderId, String recipientId, String messageBody) {

        CreateMessage newMessage = new CreateMessage(senderId, recipientId, messageBody);

        return requestSpec
                .body(newMessage)
                .when()
                .post("/messages")
                .then()
                .spec(responseSpec)
                .extract()
                .as(Message.class);
    }

    private void createBadNewMessage(String senderId, String recipientId, String messageBody) {

        CreateMessage createNewMessage = new CreateMessage(senderId, recipientId, messageBody);

        requestSpec
                .body(createNewMessage)
                .when().post("/messages")
                .then()
                .assertThat().statusCode(400);
    }


    @Test
    void createUser() {
        CheckUsers newUser = createNewUser("Slowpoke");

        assertThat(newUser.getUserId()).isNotEmpty();
        assertThat(newUser.getFullName()).isEqualTo("Slowpoke");
    }

    @Test
    void checkAllUsers() {
        createNewUser("Biba");
        createNewUser("Boba");

        List<CheckUsers> users = requestSpec
                .when()
                .get("/users")
                .then()
                .spec(responseSpec)
                .extract()
                .jsonPath()
                .getList(".", CheckUsers.class);

        assertThat(users.size()).isGreaterThan(1);
    }

    @Test
    void checkUserById() {
        CheckUsers newUser = createNewUser("Psyduck");

        CheckUsers response = requestSpec
                .pathParam("userId", newUser.getUserId())
                .when()
                .get("/users/{userId}")
                .then()
                .log().body()
                .spec(responseSpec)
                .extract()
                .as(CheckUsers.class);

        assertThat(response.getUserId()).isEqualTo(newUser.getUserId());
        assertThat(response.getFullName()).isEqualTo(newUser.getFullName());
    }

    @Test
    void createMessage() {
        CheckUsers newUser1 = createNewUser("Zita");
        CheckUsers newUser2 = createNewUser("Gita");

        CreateMessage newMessage = new CreateMessage(newUser2.getUserId(), newUser1.getUserId(),
                "They look like a skinny rooster and a fat chicken.");

        Message response = requestSpec
                .body(newMessage)
                .when()
                .post("/messages")
                .then()
                .spec(responseSpec)
                .extract()
                .as(Message.class);

        assertThat(response.getMessageBody()).isEqualTo(newMessage.getMessageBody());
        assertThat(newUser2.getUserId()).isEqualTo(response.getSenderId());
        assertThat(newUser1.getUserId()).isEqualTo(response.getRecipientId());

    }

    @Test
    void checkAllMessages() {
        CheckUsers newUser1 = createNewUser("Pupa");
        CheckUsers newUser2 = createNewUser("Lupa");

        Message newMessage1 = createNewMessage(newUser1.getUserId(), newUser2.getUserId(),
                "Pupa hides, behind Lupa");
        Message newMessage2 = createNewMessage(newUser2.getUserId(), newUser1.getUserId(),
                "And behind Lupa, nobody hides");

        List<Message> messages = requestSpec
                .when()
                .get("/messages")
                .then()
                .spec(responseSpec)
                .extract()
                .jsonPath()
                .getList(".", Message.class);


        assertThat(messages.size()).isGreaterThan(1);
        assertThat(messages.contains(newMessage1.getDate()));
        assertThat(messages.contains(newMessage2.getMessageBody()));
        assertThat(messages.contains((newUser1.getUserId())));
        assertThat(messages.contains((newUser2.getUserId())));
    }

    @Test
    void checkStatusCode400WhenSenderDoesNotExists() {
        CheckUsers newUser = createNewUser("Pikachu");

        createBadNewMessage(newUser.getUserId(), "Deadpool", "Hi! Bro.");
    }

    @Test
    void checkStatusCode400WhenRecipientDoesNotExists() {
        CheckUsers newUser = createNewUser("Batman");

        createBadNewMessage("Star-Lord", newUser.getUserId(), "I'm Batman");
    }
}
