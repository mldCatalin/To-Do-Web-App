package mldcatalinprojects.wunderlist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceExistsException extends RuntimeException {
    
    public ResourceExistsException(String exceptionMessage) {
        super(exceptionMessage);
    }
}