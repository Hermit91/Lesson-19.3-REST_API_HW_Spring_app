package gmail.salokin1991.restbackend.services;

import gmail.salokin1991.restbackend.exception.UsersNotFoundException;
import gmail.salokin1991.restbackend.objects.User;
import gmail.salokin1991.restbackend.responses.CreateUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private List<User> users = new ArrayList<>();

    public User addUser(CreateUser newUser) {
        User user = new User(newUser.getFullName());
        users.add(user);

        return user;
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getById(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        throw new UsersNotFoundException("Sorry. This app currently does not allow you to" +
                " communicate with characters from other universes.");
    }
}
