
package eventim.teamessen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchUserException extends RuntimeException {

    public NoSuchUserException() {
    }

    public NoSuchUserException(String string) {
        super(string);
    }

    public NoSuchUserException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
    
}
