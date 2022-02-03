package gmail.salokin1991.restbackend.controller;

import gmail.salokin1991.restbackend.objects.User;
import gmail.salokin1991.restbackend.responses.CheckUsers;
import gmail.salokin1991.restbackend.responses.CreateUser;
import gmail.salokin1991.restbackend.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    @ApiOperation("Create user")
    public User addUser(@RequestBody CreateUser user) {
        return userService.addUser(user);
    }

    @GetMapping("/users")
    @ApiOperation("Get all users")
    public List<CheckUsers> getAllUsers() {
        List<User> users = userService.getAllUsers();

        return users
                .stream()
                .map(CheckUsers::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/{userId}")
    @ApiOperation("Get user by id")
    public CheckUsers getUserById(@PathVariable String userId) {
        return new CheckUsers(userService.getById(userId));
    }
}
