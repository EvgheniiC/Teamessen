package eventim.teamessen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchEventExeption extends RuntimeException{

    public NoSuchEventExeption() {
    }

    public NoSuchEventExeption(String message) {
        super(message);
    }

    public NoSuchEventExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
