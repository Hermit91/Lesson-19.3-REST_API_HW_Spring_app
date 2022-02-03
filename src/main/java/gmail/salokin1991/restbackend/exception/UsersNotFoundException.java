package gmail.salokin1991.restbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UsersNotFoundException extends RuntimeException {

    public UsersNotFoundException(String message) {
        super(message);
    }
}
