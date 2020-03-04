package eventim.teamessen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchOptionExeption extends RuntimeException {

    public NoSuchOptionExeption() {
    }

    public NoSuchOptionExeption(String message) {
        super(message);
    }

    public NoSuchOptionExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
