package mldcatalinprojects.wunderlist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InputValidationException extends RuntimeException {
    
    public InputValidationException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
