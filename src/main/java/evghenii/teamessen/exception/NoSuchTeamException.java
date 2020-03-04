
package eventim.teamessen.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchTeamException extends   RuntimeException {

    public NoSuchTeamException() {
    }

    public NoSuchTeamException(String string) {
        super(string);
    }

    public NoSuchTeamException(String string, Throwable cause) {
        super(string, cause);
    }
    
    
    
}
